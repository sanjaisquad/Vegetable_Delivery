package com.greengroc.v1api.repositories;

import com.greengroc.v1api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}

//@Repository
//public interface UserRepository extends JpaRepository<User,Integer> {
//    Optional<User> findByEmail(String name);
//
//}
