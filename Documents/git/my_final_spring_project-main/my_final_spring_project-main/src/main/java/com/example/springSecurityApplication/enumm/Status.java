package com.example.springSecurityApplication.enumm;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Status {
    ACCEPTED("Принят"),
    DECORATED("Оформлен"),
    PAID("Оплачен"),
    IN_TRANSIT("В пути"),
    AWAITING("Ожидает"),
    RECEIVED("Получен");
    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status valueByName(String name) {
        return Arrays.stream(values())
                .filter(rt -> rt.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Не найден с именем " + name + " в Status"));
    }
}
