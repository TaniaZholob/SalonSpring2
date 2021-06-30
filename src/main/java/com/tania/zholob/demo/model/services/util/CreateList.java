package com.tania.zholob.demo.model.services.util;

import com.tania.zholob.demo.model.entity.Time_slots;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CreateList {

    private List<LocalDateTime> localDateTimes;

    public CreateList() {
        localDateTimes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                localDateTimes.add(LocalDateTime.now().plusDays(i).withHour(10 + j).withMinute(0).withSecond(0));

            }
        }

    }

    public void setLocalDateTimes(List<LocalDateTime> localDateTimes) {
        this.localDateTimes = localDateTimes;
    }

    public Set<DayOfWeek> getDays() {
        Set<DayOfWeek> days = new TreeSet<>();
        for (LocalDateTime localDateTimeV : localDateTimes) {
            days.add(localDateTimeV.getDayOfWeek());
        }
        return days;
    }


    public Set<String> getHour(DayOfWeek s) {

        Set<String> hours = new TreeSet<>();
        for (LocalDateTime localDateTimeV : localDateTimes) {
            if (s.equals(localDateTimeV.getDayOfWeek())) {
                hours.add(localDateTimeV.getHour() + ":" + localDateTimeV.getMinute() + "0");
            }
        }


        System.out.println("Hours " + hours + " of day " + s.name());
        return hours;
    }


    public LocalDate getDate(DayOfWeek day) {
        LocalDate d = null;
        for (LocalDateTime date : localDateTimes) {
            if (day.equals(date.getDayOfWeek())) {
                d = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
            }
        }

        return d;
    }


    public List<LocalDateTime> getLocalDateTimes() {
        return localDateTimes;
    }
}