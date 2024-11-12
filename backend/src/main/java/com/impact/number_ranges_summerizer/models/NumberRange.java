package com.impact.number_ranges_summerizer.models;

public class NumberRange {

    private final int start;
    private final int end;

    /**
     * Constructs a NumberRange with the specified start and end values.
     * @param start the starting number of the range
     * @param end the ending number of the range
     */
    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start value of the number range.
     *
     * @return the starting number of the range
     */
    public int getStart() {
        return start;
    }

    /**
     * Returns the end value of the number range.
     * @return the ending number of the range
     */
    public int getEnd() {
        return end;
    }

    /**
     * Returns a string representation of the number range. If the start and end
     * values are the same, it returns the start value as a string. Otherwise,
     * it returns the range in the format "start-end".
     *
     * @return a string representation of the number range
     */
    @Override
    public String toString() {
        return start == end ? String.valueOf(start) : start + "-" + end;
    }
}
