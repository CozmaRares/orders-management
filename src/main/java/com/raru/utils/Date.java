package com.raru.utils;

import java.time.LocalDateTime;

public class Date {
    public LocalDateTime date;

    public Date(LocalDateTime date) {
        this.date = date;
    }

    public Date(String date) {
        this.date = LocalDateTime.parse(date.substring(0, date.length() - 1));
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + "Z";
    }
}
