package com.example.baidoxe.controller;

import com.example.baidoxe.dto.BaiDoDTO;
import com.example.baidoxe.dto.UsersDTO;
import com.example.baidoxe.service.BaiDoService;
import com.example.baidoxe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/baido")
public class BaiDoController {
    @Autowired
    private BaiDoService baiDoService;

    @GetMapping("/list")
    public String listBaiDo(Model model) {
        List<BaiDoDTO> BaiDoListDTO = baiDoService.listActiveBaiDo();
        model.addAttribute("listBaiDo", BaiDoListDTO);
        return "baido-list";
    }

    //Thêm dữ liệu
    @GetMapping("/them")
    public String showAddBaiDoForm(Model model) {
        // Tạo một đối tượng BaiDoDTO trống và đưa vào model để form có thể sử dụng
        BaiDoDTO baiDoDTO = new BaiDoDTO();
        model.addAttribute("baiDoDTO", baiDoDTO);
        return "baido-them";
    }

    @PostMapping("/them")
    public String addBaiDo(@ModelAttribute BaiDoDTO baiDoDTO) {
        // Gọi service để thêm bãi đỗ
        baiDoService.addBaiDo(baiDoDTO);
        return "redirect:/baido/list";
    }
//    Sửa dữ liệu

    @GetMapping("/sua/{id}")
    public String suaBaiDo(@PathVariable Integer id, Model model) {
        BaiDoDTO baiDoDTO = baiDoService.findBaiDoById(id);
        model.addAttribute("baiDoDTO", baiDoDTO);
        return "baido-sua";  // Trả về tên template đúng
    }

    // Lưu thông tin đã chỉnh sửa
    @PostMapping("/sua")
    public String updateBaiDo(@ModelAttribute BaiDoDTO baiDoDTO) {
        baiDoService.updateBaiDo(baiDoDTO);
        return "redirect:/baido/list";
    }

    //    Xóa dữ liệu
    @PostMapping("/xoa/{id}")
    public String deleteBaiDo(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            // Gọi phương thức deleteBaiDo từ service
            baiDoService.deleteBaiDo(id);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("message", "Xóa bãi đỗ thành công!");
        } catch (IllegalArgumentException e) {
            // Thêm thông báo lỗi
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/baido/list"; // Chuyển hướng đến trang danh sách bãi đỗ
    }

}
