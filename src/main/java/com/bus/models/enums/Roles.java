package com.bus.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    Водитель,
    Клиент;

    @Override
    public String getAuthority() {
        return name();
    }
}
