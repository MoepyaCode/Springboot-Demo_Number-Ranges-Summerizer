// File: src/main/java/com/impact/number_ranges_summarizer/controllers/NumberRangeSummarizerController.java
package com.impact.number_ranges_summerizer.controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impact.number_ranges_summerizer.services.NumberRangeSummarizer;

@RestController
@RequestMapping("/api/ranges")
public class NumberRangeSummarizerController {

    private final NumberRangeSummarizer numberRangeSummarizer;

    @Autowired
    public NumberRangeSummarizerController(NumberRangeSummarizer numberRangeSummarizer) {
        this.numberRangeSummarizer = numberRangeSummarizer;
    }

    @PostMapping("/summarize")
    public String summarizeRanges(@RequestBody Map<String, String> body) {
        String input = body.get("input");
        
        try {
            Collection<Integer> numbers = numberRangeSummarizer.collect(input);
            return numberRangeSummarizer.summarizeCollection(numbers);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

    }
}
