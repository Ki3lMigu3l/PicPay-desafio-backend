package com.github.ki3lmigu3l.picpay.repository;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
