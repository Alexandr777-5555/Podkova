package ru.avdey.podkova.model;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority  {

    USER, ADMIN , OPERATOR ;

    @Override
    public String getAuthority() {
        return name();
    }
}
