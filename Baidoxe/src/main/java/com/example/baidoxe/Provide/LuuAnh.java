package com.example.baidoxe.Provide;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LuuAnh {
    private static final String UPLOAD_DIR = "D:/Baidoxe/Baidoxe/Baidoxe/src/main/resources/static/uploads/";

    public String LuuAnhUser(MultipartFile duongDanAnh) {
        if (duongDanAnh.isEmpty()) {
            return null; // Nếu không có tệp được chọn, trả về null hoặc thông báo lỗi
        }

        try {
            // Tạo tên tệp với dấu thời gian hiện tại để đảm bảo tính duy nhất
            String fileName = System.currentTimeMillis() + "_" + duongDanAnh.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Tạo thư mục nếu chưa tồn tại
            Files.createDirectories(filePath.getParent());

            // Lưu tệp vào hệ thống
            Files.write(filePath, duongDanAnh.getBytes());

            // Trả về đường dẫn tương đối của tệp
//            return UPLOAD_DIR + fileName; // Trả về đường dẫn dạng chuỗi
            return  fileName;
        } catch (IOException e) {
            e.printStackTrace(); // Ghi lại lỗi nếu có
            return null; // Hoặc trả về thông báo lỗi phù hợp
        }
    }
}