package com.excersise.virus.repos;

import com.excersise.virus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByUsernameAndPassword (String username, String password);
    User getByUsername(String username);
}
