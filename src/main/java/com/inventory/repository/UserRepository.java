package com.inventory.repository;

import com.inventory.constant.enums.Status;
import com.inventory.model.db.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmailAndStatus(String email, Status status);
}