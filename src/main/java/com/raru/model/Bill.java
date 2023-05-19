package com.raru.model;

import com.raru.utils.Date;

public record Bill(String id, String order_, Date dueUntil, Date createDate) {
    @SuppressWarnings("unused")
    private Bill() {
        this(null, null, null, null);
    }

    public Bill(String order_, Date dueUntil) {
        this(null, order_, dueUntil, null);
    }
}
