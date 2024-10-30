package com.example.baidoxe.repository;

import com.example.baidoxe.models.PhuongTien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PhuongTienRepository extends JpaRepository<PhuongTien, Integer> {
    Optional<PhuongTien> findById(Integer Id);

    @Query("SELECT pt FROM PhuongTien pt WHERE pt.Status = 1")
    List<PhuongTien> findActivePhuongTien();
}
