package com.bbm.khodar.repository;

import com.bbm.khodar.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    boolean existsByNameOrEmail(String name, String email);
    Optional<Community> findByEmail(String email);
}
