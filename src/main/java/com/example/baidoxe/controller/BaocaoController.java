package com.example.baidoxe.controller;

import com.example.baidoxe.repository.ThanhToanRepository;
import com.example.baidoxe.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/baocao")
public class BaocaoController {

    @Autowired
    private ThanhToanService thanhToanService;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @GetMapping("/thongke")
    public String getThongKe(Model model) {
        List<Object[]> results = thanhToanRepository.countBookingsByYearAndMonth();
        for (Object[] result : results) {
            Integer nam = (Integer) result[0];
            Integer thang = (Integer) result[1];
            Integer soLuongDatCho = (Integer) result[2];
            Long tongDoanhThu = ((Number) result[3]).longValue(); // Chuyển từ Integer hoặc Double sang Long

            System.out.println("Năm: " + nam + ", Tháng: " + thang +
                    ", Số lượng đặt chỗ: " + soLuongDatCho +
                    ", Tổng doanh thu: " + tongDoanhThu);
        }


        // Dữ liệu biểu đồ
        model.addAttribute("bookingsData", results);

        return "bao-cao";
    }

}
