package com.example.baidoxe.controller;

import com.example.baidoxe.Provide.QRCodeGenerator;
import com.example.baidoxe.dto.DatChoDTO;
import com.example.baidoxe.dto.ThongTinDoDTO;
import com.example.baidoxe.models.DatCho;
import com.example.baidoxe.repository.DatChoRepository;
import com.example.baidoxe.service.DatChoService;
import com.example.baidoxe.service.ThongTinDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hoadon")
public class ThongTinDoController {

    @Autowired
    private ThongTinDoService thongTinDoService;
    @Autowired
    private DatChoService datChoService;
    @Autowired
    private QRCodeGenerator qrCodeGenerator;
    @Autowired
    private DatChoRepository datChoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ThongTinDoController.class);

    @GetMapping("/chitiet/{id}")
    public String showHoaDon(@PathVariable("id") int id, Model model, HttpSession session) {
        // Retrieve session attributes
        Integer idUser = (Integer) session.getAttribute("IdUser");
        Integer roleId = (Integer) session.getAttribute("RoleId");

        // Redirect to login if session is invalid
        if (idUser == null || roleId == null) {
            return "redirect:/login/auth";
        }

        // Fetch ThongTinDo data for the user
        int status = 1; // Example status value
        List<ThongTinDoDTO> thongTinDoDTOS = thongTinDoService.getThongTinDoByUserAndStatus(idUser, status);

        // Log ThoiGianVao and ThoiGianRa
        thongTinDoDTOS.forEach(dto -> {
            logger.info("thoiGianVao: {}", dto.getThoiGianVao());
            logger.info("thoiGianRa: {}", dto.getThoiGianRa());
            logger.info("MaQR: {}", dto.getMaQR()); // Log MaQR for debugging
        });

        // Add ThongTinDo data to the model
        model.addAttribute("thongTinDo", thongTinDoDTOS);

        // Check if the list is not empty and create QR code URL
        if (!thongTinDoDTOS.isEmpty()) {
            String maQR = thongTinDoDTOS.get(0).getMaQR(); // Get MaQR from the first item in the list

            // Generate QR code URL using the QRCodeGenerator service
            String qrCodeUrl = qrCodeGenerator.generateQRCodeUrl(maQR);

            // Add the generated QR code URL to the model
            model.addAttribute("qrCodeUrl", qrCodeUrl);
        }

        return "map/hoadon";
    }

}
