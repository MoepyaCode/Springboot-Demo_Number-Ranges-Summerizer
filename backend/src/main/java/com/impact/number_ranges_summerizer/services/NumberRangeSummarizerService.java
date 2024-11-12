// File: src/main/java/com/impact/number_ranges_summarizer/services/NumberRangeSummarizerService.java
package com.impact.number_ranges_summerizer.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.impact.number_ranges_summerizer.models.NumberRange;

@Service
public class NumberRangeSummarizerService implements NumberRangeSummarizer {

    /**
     * Converts a comma-separated string into a collection of integers.
     *
     * @param input the comma-separated string to convert
     * @return a collection of parsed integers
     * @throws RuntimeException if input is invalid
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            try {
                numbers.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {

                throw new RuntimeException("Invalid input! Please enter a list of comma-separated numbers.");
            }
        }
        return numbers;
    }

    /**
     * Summarizes a collection of integers into ranges.
     *
     * @param input the collection of integers
     * @return a string with summarized ranges (e.g., "1-3, 5-6")
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> sortedList = new ArrayList<>(input);
        Collections.sort(sortedList);

        List<NumberRange> ranges = new ArrayList<>();
        int start = sortedList.get(0);
        int end = start;

        for (int i = 1; i < sortedList.size(); i++) {
            int current = sortedList.get(i);
            if (current == end + 1) {
                end = current;
            } else {
                ranges.add(new NumberRange(start, end));
                start = current;
                end = current;
            }
        }

        ranges.add(new NumberRange(start, end));

        List<String> rangeStrings = new ArrayList<>();

        for (NumberRange range : ranges) {
            rangeStrings.add(range.toString());
        }

        return String.join(", ", rangeStrings);
    }
}
