package com.salonwebservice.pi.repository;

import com.salonwebservice.pi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
