package com.inventory.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Gender {
    MALE(1),
    FEMALE(2),
    ;

    private final int type;

    Gender(int type) {
        this.type = type;
    }

    public static Gender fromValue(int value) {
        return switch (value) {
            case 1 -> MALE;
            case 2 -> FEMALE;
            default -> null;
        };
    }

    @JsonCreator
    public static Gender fromName(String name) {
        return switch (StringUtils.upperCase(name)) {
            case "MALE" -> MALE;
            case "FEMALE" -> FEMALE;
            default -> null;
        };
    }

    @JsonValue
    public String getName() {
        return toString();
    }

}