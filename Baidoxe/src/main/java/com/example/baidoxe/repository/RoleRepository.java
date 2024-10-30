package com.example.baidoxe.repository;

import com.example.baidoxe.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends  JpaRepository<Role, Integer>{
    Optional<Role> findById(Integer Id);

    @Query("SELECT r FROM Role r WHERE r.Status = 1")
    List<Role> findActive();
}
