package com.example.databaseexam.Config;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static List<LocalDate> getAllDatesOfMonth(int month, int year) {
        List<LocalDate> dates = new ArrayList<>();
        int numDaysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int day = 1; day <= numDaysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            dates.add(date);
        }
        return dates;
    }
}
