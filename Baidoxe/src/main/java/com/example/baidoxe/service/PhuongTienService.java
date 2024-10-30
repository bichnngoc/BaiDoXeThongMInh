package com.example.baidoxe.service;

import com.example.baidoxe.dto.PhuongTienDTO;

import java.util.List;

public interface PhuongTienService {
    List<PhuongTienDTO> listActivePhuongTien();
    PhuongTienDTO findPhuongTienId(Integer Id);
    PhuongTienDTO addPhuongTien(PhuongTienDTO phuongTienDTO);
    PhuongTienDTO updatePhuongTien(PhuongTienDTO phuongTienDTO);
    void DetletePhuongTien(Integer Id);
}
