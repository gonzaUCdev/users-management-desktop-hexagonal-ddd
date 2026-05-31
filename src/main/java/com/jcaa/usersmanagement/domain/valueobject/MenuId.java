package com.jcaa.usersmanagement.domain.valueobject;

public record MenuId(Integer value) {
    public MenuId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("MenuId debe ser un numero positivo");
        }
    }
}
