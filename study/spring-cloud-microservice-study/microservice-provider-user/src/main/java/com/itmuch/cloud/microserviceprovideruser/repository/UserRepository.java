package com.itmuch.cloud.microserviceprovideruser.repository;

import com.itmuch.cloud.microserviceprovideruser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
