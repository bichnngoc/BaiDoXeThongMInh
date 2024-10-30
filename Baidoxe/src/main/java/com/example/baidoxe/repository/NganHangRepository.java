package com.example.baidoxe.repository;

import com.example.baidoxe.dto.BaiDoDTO;
import com.example.baidoxe.dto.NganHangDTO;
import com.example.baidoxe.models.BaiDo;
import com.example.baidoxe.models.NganHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NganHangRepository extends JpaRepository<NganHang, Integer> {
    Optional<NganHang> findById(Integer Id);

    @Query("SELECT new com.example.baidoxe.dto.NganHangDTO(" +
            "b.Id,b.TenNganHang,b.Status) " +
            "FROM NganHang b " +
            "WHERE b.Status = :Status")
    List<NganHangDTO> findActiveNganHang(@Param("Status") Integer Status);
}
