package com.impact.number_ranges_summerizer.services;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NumberRangeSummarizerServiceTest {

    @Autowired
    private NumberRangeSummarizerService numberRangeSummarizerService;

    @Test
    public void testCollect_ValidInput() {
        // Valid input
        String input = "1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31";
        Collection<Integer> collectedNumbers = numberRangeSummarizerService.collect(input);

        assertNotNull(collectedNumbers);
        assertEquals(14, collectedNumbers.size());
        assertTrue(collectedNumbers.contains(1));
        assertTrue(collectedNumbers.contains(31));
    }

    @Test
    public void testCollect_InvalidInput() {
        // Invalid input with non-numeric values
        String input = "1, 3, hello, 6, 7, 8, 12";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            numberRangeSummarizerService.collect(input);
        });

        assertEquals("Invalid input! Please enter a list of comma-separated numbers.", exception.getMessage());
    }

    @Test
    public void testSummarizeCollection() {
        // Valid input collection
        Collection<Integer> input = numberRangeSummarizerService.collect("1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31");
        String result = numberRangeSummarizerService.summarizeCollection(input);

        // Expected summarized result
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        assertEquals(expected, result);
    }

    @Test
    public void testSummarizeCollection_SingleNumber() {
        // Single number input
        Collection<Integer> input = numberRangeSummarizerService.collect("5");
        String result = numberRangeSummarizerService.summarizeCollection(input);

        // Expected result should be the same number
        assertEquals("5", result);
    }

    @Test
    public void testSummarizeCollection_EmptyInput() {
        // Empty input collection
        Collection<Integer> input = numberRangeSummarizerService.collect("");
        String result = numberRangeSummarizerService.summarizeCollection(input);

        // Expected empty result
        assertEquals("", result);
    }

    @Test
    public void testCollectAndSummarize_MixedInput() {
        // Mixed input with gaps and ranges
        String input = "5, 7, 8, 10, 11, 15, 16, 18";
        Collection<Integer> collectedNumbers = numberRangeSummarizerService.collect(input);
        String summarizedResult = numberRangeSummarizerService.summarizeCollection(collectedNumbers);

        // Expected output with ranges
        String expected = "5, 7-8, 10-11, 15-16, 18";
        assertEquals(expected, summarizedResult);
    }
}