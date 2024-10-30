package com.example.baidoxe.service.impl;

import com.example.baidoxe.dto.PhuongTienDTO;
import com.example.baidoxe.mapper.PhuongTienMapper;
import com.example.baidoxe.models.PhuongTien;
import com.example.baidoxe.repository.PhuongTienRepository;
import com.example.baidoxe.service.PhuongTienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhuongTienServiceImpl implements PhuongTienService {
    @Autowired
    private PhuongTienRepository phuongTienRepository;

    @Autowired
    private PhuongTienMapper phuongTienMapper;

    @Override
    public List<PhuongTienDTO> listActivePhuongTien() {
        List<PhuongTien> phuongTiens = phuongTienRepository.findActivePhuongTien();
                return phuongTiens.stream()
                        .map(phuongTienMapper::TophuongTienDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public PhuongTienDTO findPhuongTienId(Integer Id) {
        Optional<PhuongTien> phuongTienOptional = phuongTienRepository.findById(Id);
        return phuongTienOptional.map(phuongTienMapper::TophuongTienDTO).orElse(null);
    }

    @Override
    public PhuongTienDTO addPhuongTien(PhuongTienDTO phuongTienDTO) {
        PhuongTien phuongTien = phuongTienMapper.toPhuongTien(phuongTienDTO);
        PhuongTien savesPhuongTien = phuongTienRepository.save(phuongTien);
        return phuongTienMapper.TophuongTienDTO(savesPhuongTien);
    }

    @Override
    public PhuongTienDTO updatePhuongTien(PhuongTienDTO phuongTienDTO) {
        return null;
    }

    @Override
    public void DetletePhuongTien(Integer Id) {

    }
}
