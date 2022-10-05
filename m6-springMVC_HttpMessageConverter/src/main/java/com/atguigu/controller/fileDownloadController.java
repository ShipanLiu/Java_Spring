package com.atguigu.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class fileDownloadController {

    @RequestMapping("/fileDownloadController")
    public  ResponseEntity<byte[]> fileDownloadController(HttpServletRequest req, String fileName) throws IOException {
        // 获取文件的名称
        // 获取文件真实路径[(request 或 session) -> ServletContext]
        String realPath = req.getServletContext().getRealPath("/WEB-INF/download/" + fileName);
        //  create input stream
        InputStream is = new FileInputStream(realPath);
        // 文件下载
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        HttpHeaders headers = new HttpHeaders();
        // 设置响应头[set the file format as attachment, so that if the user click it, it will not open but directly downloaded ]
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        // if you come up with language Chinese
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"),"ISO-8859-1"));
        // 状态码
        // 需要放 3 个 params 进去
        ResponseEntity<byte[]> resEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);

        is.close();

        return resEntity;
    }
}
