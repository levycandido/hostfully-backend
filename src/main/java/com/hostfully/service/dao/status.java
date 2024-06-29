package com.hostfully.service.dao;

import com.fasterxml.jackson.annotation.JsonValue;

public enum status {
    ACTIVE("Active"),
    CANCELED("Canceled"),
    BOOKED("Booked"),
    PENDING("Pending");

    private final String status;

    status(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
