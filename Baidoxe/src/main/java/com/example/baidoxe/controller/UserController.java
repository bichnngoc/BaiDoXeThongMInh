package com.example.baidoxe.controller;

import com.example.baidoxe.Provide.LuuAnh;
import com.example.baidoxe.dto.NganHangDTO;
import com.example.baidoxe.dto.PhuongTienDTO;
import com.example.baidoxe.dto.RoleDTO;
import com.example.baidoxe.dto.UsersDTO;
import com.example.baidoxe.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private NganHangService nganHangService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LuuAnh luuAnh;
    @GetMapping("/list")
    public String listUsersPage(Model model) {
        List<UsersDTO> listUser = userService.listActiveUser("1"); // Lấy danh sách user với status = 1 (Active)
        model.addAttribute("listUser", listUser);
        return "user-list"; // Tên file HTML (không cần phần mở rộng .html)
    }
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        try {
            UsersDTO usersDTO = new UsersDTO();
            List<NganHangDTO> nganHangDTOList = nganHangService.listActiveNganHang();
            List<RoleDTO> roleDTOList = roleService.listRole();

            // In ra dữ liệu để kiểm tra
            System.out.println("NganHangDTOList: " + nganHangDTOList);
            System.out.println("RoleDTOList: " + roleDTOList);
            System.out.println("usersDTO: " + usersDTO);

            model.addAttribute("usersDTO", usersDTO);
            model.addAttribute("nganHangList", nganHangDTOList);
            model.addAttribute("roleList", roleDTOList);

            return "user-them";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user-them";
        }
    }

    @PostMapping("/add")
    public String addUser(
            @Valid @ModelAttribute("usersDTO") UsersDTO usersDTO,
            BindingResult bindingResult,
            @RequestParam("photo") MultipartFile photo,  // Nhận file từ form
            Model model) {
        if (bindingResult.hasErrors()) {
            return "user-them";  // Nếu có lỗi validation
        }

        try {
            // Xử lý lưu file ảnh
            String photoPath = luuAnh.LuuAnhUser(photo);
            usersDTO.setImage(photoPath);

            // Lưu user vào database
            userService.addUser(usersDTO);

            return "redirect:/user/list";  // Chuyển hướng về danh sách user
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user-them";  // Trả về form thêm user nếu gặp lỗi
        }
    }

    @GetMapping("/sua/{Id}")
    public String showEditUserForm(@PathVariable("Id") Integer id, Model model) {
        try {
            UsersDTO usersDTO = userService.findUserById(id);
            List<NganHangDTO> nganHangDTOList = nganHangService.listActiveNganHang();
            List<RoleDTO> roleDTOList = roleService.listRole();

            model.addAttribute("usersDTO", usersDTO);
            model.addAttribute("nganHangList", nganHangDTOList);
            model.addAttribute("roleList", roleDTOList);

            return "user-sua";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/user/list"; // Redirect back to list if error occurs
        }
    }

    @PostMapping("/sua/{Id}")
    public String updateUser(
            @PathVariable("Id") Integer id,
            @Valid @ModelAttribute("usersDTO") UsersDTO usersDTO,
            BindingResult bindingResult,
            @RequestParam("photo") MultipartFile photo,  // Nhận file từ form
            Model model) {
        if (bindingResult.hasErrors()) {
            return "user-sua";  // Trả về form nếu có lỗi validation
        }

        try {
            // Nếu ảnh mới được upload, xử lý lưu ảnh
            if (!photo.isEmpty()) {
                String photoPath = luuAnh.LuuAnhUser(photo);
                usersDTO.setImage(photoPath);
            }

            // Cập nhật thông tin user
            userService.updateUser(usersDTO);

            return "redirect:/user/list";  // Chuyển hướng về danh sách user
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user-sua";  // Trả về form nếu có lỗi khi cập nhật
        }
    }

}
