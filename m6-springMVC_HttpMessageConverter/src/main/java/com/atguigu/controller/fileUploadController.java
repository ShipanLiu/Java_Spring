package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class fileUploadController {

    @PostMapping("fileUploadController")
    public String fileUpload(String username, MultipartFile uploadFile, HttpSession session) {
        try {
            String fileName = uploadFile.getOriginalFilename();

            // get the real path of the file
            String realPath = session.getServletContext().getRealPath("WEB-INF/upload");  // 保存 在upload 下面， 用户上传的的文件

            // check if the realpath exists, if not then create
            File filePath = new File(realPath);
            System.out.println(filePath);

            if(!filePath.exists()) {
                filePath.mkdirs();
            }

            // create UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");

            //  now upload the file
            // File.separator 就是路径的分隔符 ： 不同操作系统， 分隔符不一样
            File uFile = new File(filePath + File.separator + uuid + fileName);
            uploadFile.transferTo(uFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }

}
