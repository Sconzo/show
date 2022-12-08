package com.restful.webservices.core.domain.enums;

public enum TypeEnum {

    MULTIPLE_CHOICE(0,"MULTIPLE_CHOICE"),
    TRUE_OR_FALSE(0,"TRUE_OR_FALSE");

    private int key;
    private String value;

    TypeEnum(int key, String value) {
        this.key =key;
        this.value =value;
    }
}
