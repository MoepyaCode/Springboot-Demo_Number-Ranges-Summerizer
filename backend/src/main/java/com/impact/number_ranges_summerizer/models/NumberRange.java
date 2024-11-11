package com.impact.number_ranges_summerizer.models;

public class NumberRange {

    private final int start;
    private final int end;

    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return start == end ? String.valueOf(start) : start + "-" + end;
    }
}