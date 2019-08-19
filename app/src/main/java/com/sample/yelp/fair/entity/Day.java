package com.sample.yelp.fair.entity;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

    private final int value;

    Day(int value) {
        this.value = value;
    }

    private static Map<Integer, Day> map = new HashMap<Integer, Day>();

    static {
        for (Day day : Day.values()) {
            map.put(day.value, day);
        }
    }

    public static Day valueOf(int day) {
        return map.get(day);
    }

}
