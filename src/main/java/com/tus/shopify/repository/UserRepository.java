package com.tus.shopify.repository;

import com.tus.shopify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
