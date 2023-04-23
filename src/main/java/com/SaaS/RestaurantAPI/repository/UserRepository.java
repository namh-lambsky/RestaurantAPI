package com.SaaS.RestaurantAPI.repository;

import com.SaaS.RestaurantAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select e from User e where e.username= ?1")
    User findAllByUsername(String username);
}
