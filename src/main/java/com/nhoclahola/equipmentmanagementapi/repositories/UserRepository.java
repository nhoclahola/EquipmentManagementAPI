package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);


    @Query("SELECT COUNT(u) " +
            "FROM User u " +
            "WHERE u.username LIKE %:query% " +
            "OR u.fullName LIKE %:query%")
    long countSearchUser(@Param("query") String query);

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.username LIKE %:query% " +
            "OR u.fullName LIKE %:query%")
    List<User> searchUser(@Param("query") String query, Pageable pageable);
}
