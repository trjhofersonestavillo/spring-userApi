package com.exampleuserApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exampleuserApi.entities.User;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);
}
