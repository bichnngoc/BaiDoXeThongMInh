package com.example.baidoxe.service;

import com.example.baidoxe.dto.BaiDoDTO;
import com.example.baidoxe.dto.BaiDoDTO;

import java.util.List;

public interface BaiDoService {
    List<BaiDoDTO> listActiveBaiDo();
    BaiDoDTO findBaiDoById(Integer Id);
    BaiDoDTO addBaiDo(BaiDoDTO baiDoDTO);
    BaiDoDTO updateBaiDo(BaiDoDTO baiDoDTO);
    void deleteBaiDo(Integer Id);
}

