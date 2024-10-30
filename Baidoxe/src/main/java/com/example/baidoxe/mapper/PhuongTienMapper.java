package com.example.baidoxe.mapper;

import com.example.baidoxe.dto.PhuongTienDTO;
import com.example.baidoxe.models.PhuongTien;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class PhuongTienMapper {
    public PhuongTienDTO TophuongTienDTO(PhuongTien phuongTien){
        if (phuongTien == null){
            return null;
        }
        return PhuongTienDTO.builder()
                .Id(phuongTien.getId())
                .LoaiPhuongTien(phuongTien.getLoaiPhuongTien())
                .BienSo(phuongTien.getBienSo())
                .Hang(phuongTien.getHang())
                .Status(phuongTien.getStatus())
                .build();
    }

    public PhuongTien toPhuongTien(PhuongTienDTO phuongTienDTO){
        if (phuongTienDTO == null){
            return null;
        }
        PhuongTien phuongTien = PhuongTien.builder()
                .Id(phuongTienDTO.getId())
                .BienSo(phuongTienDTO.getBienSo())
                .LoaiPhuongTien(phuongTienDTO.getLoaiPhuongTien())
                .Hang(phuongTienDTO.getHang())
                .build();
        if(phuongTienDTO.getId()==null){
            phuongTien.setStatus(1);
        }else {
            phuongTien.setStatus(phuongTienDTO.getStatus());
        }
        return phuongTien;
    }

    public void updateEntityPhuongTien(PhuongTienDTO phuongTienDTO, PhuongTien phuongTien){
        if (phuongTienDTO != null && phuongTien != null){
            phuongTien.setBienSo(phuongTienDTO.getBienSo());
            phuongTien.setLoaiPhuongTien(phuongTienDTO.getLoaiPhuongTien());
            phuongTien.setHang(phuongTienDTO.getHang());

        }
    }

    public void markAsDetlete(PhuongTien phuongTien){
        if (phuongTien != null){
            phuongTien.setStatus(0);
        }
    }

}
