package com.movii.hexagonal.commons.enums;

public enum Level {
    SUCCESS("00"),
    WARN("98"),
    FAIL("99");

    private String codeDefault;

    private Level(String code) {
        this.codeDefault = code;
    }

    public String value() {
        return this.codeDefault;
    }
}
