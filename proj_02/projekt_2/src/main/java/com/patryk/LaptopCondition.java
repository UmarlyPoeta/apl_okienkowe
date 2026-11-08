package com.patryk;

public enum LaptopCondition {
    WORKING {
    @Override
    public String toString() {
    return "Sprawny";
    }
    },
    DAMAGED,
    IN_REPAIR,
    IN_TESTING
}
