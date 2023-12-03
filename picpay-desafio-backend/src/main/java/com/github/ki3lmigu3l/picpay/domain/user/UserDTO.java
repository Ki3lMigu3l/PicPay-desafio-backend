package com.github.ki3lmigu3l.picpay.domain.user;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance
) {
}
