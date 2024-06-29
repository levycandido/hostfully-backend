package com.hostfully.service.dao;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    CANCELED("Canceled"),
    BOOKED("Booked"),
    PENDING("Pending");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
