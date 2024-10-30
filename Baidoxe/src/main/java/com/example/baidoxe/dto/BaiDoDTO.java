package com.example.baidoxe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaiDoDTO {
    private Integer Id;
    private String TenBaiDo;
    private String DiaChi;
    private String ViTri;
    private Integer Status;

    public BaiDoDTO(Integer Id, String TenBaiDo, String DiaChi, Integer Status, String ViTri) {
        this.Id = Id;
        this.TenBaiDo = TenBaiDo;
        this.DiaChi = DiaChi;
        this.Status = Status;
        this.ViTri = ViTri;
    }
}

