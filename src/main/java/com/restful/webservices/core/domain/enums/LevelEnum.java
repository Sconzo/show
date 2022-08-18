package com.restful.webservices.core.domain.enums;

public enum LevelEnum {
    EASY(0,"EASY"),
    MEDIUM(1,"MEDIUM"),
    HARD(2,"HARD");

    private int key;
    private String value;

    LevelEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
