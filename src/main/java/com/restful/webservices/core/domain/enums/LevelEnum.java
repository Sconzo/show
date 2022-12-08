package com.restful.webservices.core.domain.enums;

public enum LevelEnum {
    EASY(0,"EASY"),
    INTERMEDIATE(1,"INTERMEDIATE"),
    HARD(2,"HARD");

    private int key;
    private String value;

    LevelEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
