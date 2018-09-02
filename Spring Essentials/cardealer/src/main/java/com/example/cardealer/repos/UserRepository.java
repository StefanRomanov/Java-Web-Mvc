package com.example.cardealer.repos;

import com.example.cardealer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getFirstByUsernameAndPassword(String username, String password);
}
