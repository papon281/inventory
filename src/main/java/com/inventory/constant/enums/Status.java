package com.inventory.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Status {
    ACTIVE(1),
    DELIVERED(2),
    INACTIVE(3),
    MOVED(4),
    PAID(5),
    PARTIALLY_PAID(6),
    PENDING(7),
    UNPAID(8),
    WAITING(9),
    ;
    private final int type;

    Status(int type) {
        this.type = type;
    }

    public static Status fromValue(int value) {
        return switch (value) {
            case 1 -> ACTIVE;
            case 2 -> DELIVERED;
            case 3 -> INACTIVE;
            case 4 -> MOVED;
            case 5 -> PAID;
            case 6 -> PARTIALLY_PAID;
            case 7 -> PENDING;
            case 8 -> UNPAID;
            case 9 -> WAITING;
            default -> null;
        };
    }

    @JsonCreator
    public static Status fromName(String name) {
        return switch (StringUtils.upperCase(name)) {
            case "ACTIVE" -> ACTIVE;
            case "DELIVERED" -> DELIVERED;
            case "INACTIVE" -> INACTIVE;
            case "MOVED" -> MOVED;
            case "PAID" -> PAID;
            case "PARTIALLY_PAID" -> PARTIALLY_PAID;
            case "PENDING" -> PENDING;
            case "UNPAID" -> UNPAID;
            case "WAITING" -> WAITING;
            default -> null;
        };
    }

    @JsonValue
    public String getName() {
        return toString();
    }
}