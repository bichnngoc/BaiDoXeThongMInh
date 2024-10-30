package com.example.baidoxe.service.impl;

import com.example.baidoxe.dto.BaiDoDTO;
import com.example.baidoxe.mapper.BaiDoMapper;
import com.example.baidoxe.models.BaiDo;
import com.example.baidoxe.repository.BaiDoRepository;
import com.example.baidoxe.service.BaiDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaiDoServiceImpl implements BaiDoService {
    @Autowired
    private BaiDoRepository baiDoRepository;

    @Autowired
    private BaiDoMapper baiDoMapper;

    @Override
    public List<BaiDoDTO> listActiveBaiDo() {
        List<BaiDo> activeBaiDo= baiDoRepository.findAll()
                .stream()
                .filter(baiDo -> baiDo.getStatus()==1)
                .collect(Collectors.toList());
        return activeBaiDo.stream()
                .map(baiDoMapper::toBaiDoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BaiDoDTO findBaiDoById(Integer Id) {
        BaiDo baiDo = baiDoRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bãi đỗ với ID: " + Id));

        // Chuyển đổi BaiDo sang BaiDoDTO bằng baiDoMapper hoặc thủ công
        return baiDoMapper.toBaiDoDTO(baiDo);
    }

    @Override
    public BaiDoDTO addBaiDo(BaiDoDTO baiDoDTO) {
        BaiDo baiDo = new BaiDo();
        baiDo.setTenBaiDo(baiDoDTO.getTenBaiDo());
        baiDo.setDiaChi(baiDoDTO.getDiaChi());
        baiDo.setStatus(1);
        baiDo.setViTri(baiDoDTO.getViTri());

// Lưu bãi đỗ vào cơ sở dữ liệu
        BaiDo savedBaiDo = baiDoRepository.save(baiDo);

// Chuyển đối tượng đã lưu thành DTO và trả về
        BaiDoDTO savedBaiDoDTO = new BaiDoDTO();
        savedBaiDoDTO.setId(savedBaiDo.getId());
        savedBaiDoDTO.setTenBaiDo(savedBaiDo.getTenBaiDo());
        savedBaiDoDTO.setDiaChi(savedBaiDo.getDiaChi());
        savedBaiDoDTO.setViTri(savedBaiDo.getViTri());
        savedBaiDoDTO.setStatus(savedBaiDo.getStatus());

        return savedBaiDoDTO;
    }

    @Override
    public BaiDoDTO updateBaiDo(BaiDoDTO baiDoDTO) {
        BaiDo suaBaiDo = baiDoRepository.findById(baiDoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bãi đỗ với ID: " + baiDoDTO.getId()));

        suaBaiDo.setTenBaiDo(baiDoDTO.getTenBaiDo());
        suaBaiDo.setDiaChi(baiDoDTO.getDiaChi());
        suaBaiDo.setViTri(baiDoDTO.getViTri());
        suaBaiDo.setStatus(1);

        BaiDo updatedBaiDo = baiDoRepository.save(suaBaiDo);

        return baiDoMapper.toBaiDoDTO(updatedBaiDo);
    }

    @Override
    public void deleteBaiDo(Integer Id) {
        // Kiểm tra ID có hợp lệ không
        if (Id == null || Id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ: " + Id);
        }

        // Tìm bãi đỗ theo ID
        BaiDo baiDo = baiDoRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bãi đỗ với ID: " + Id));

        // Kiểm tra trạng thái hiện tại
        if (baiDo.getStatus() == 0) {
            throw new IllegalArgumentException("Bãi đỗ đã bị xóa trước đó với ID: " + Id);
        }

        // Cập nhật trạng thái thành 0
        baiDo.setStatus(0);
        baiDoRepository.save(baiDo);
    }
}
