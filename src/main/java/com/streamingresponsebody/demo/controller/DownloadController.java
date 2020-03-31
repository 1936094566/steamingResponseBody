package com.streamingresponsebody.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.function.DoubleToLongFunction;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @RequestMapping("/simple")
    public void downloadFile(HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            File file = new File("D:\\OpenVPN\\icon.ico");
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            response.reset();
            byte[] buffer = new byte[1024];
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            response.setHeader("FileName", file.getName());
            response.setHeader("Access-Control-Expose-Headers", "FileName");
            long fileLength = file.length();
            response.setHeader("Content_Length", String.valueOf(fileLength));
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len); // 每次写入out1024字节
                outputStream.flush();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }

    @RequestMapping("/diff")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> downloadDiff(){
        File file = new File("D:\\OpenVPN\\icon.ico");
        StreamingResponseBody out  = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                InputStream inputStream = null;
                try{
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len); // 每次写入out1024字节
                        outputStream.flush();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    if (outputStream!=null){
                        outputStream.close();
                    }
                }
            }
        };
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename = "+file.getName() )
                .contentLength(file.length())
                .body(out);
    }
    @RequestMapping("/diff2")
    public StreamingResponseBody downloadDiff1(){
        File file = new File("D:\\OpenVPN\\icon.ico");
        StreamingResponseBody out  = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                InputStream inputStream = null;
                try{
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len); // 每次写入out1024字节
                        outputStream.flush();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    if (outputStream!=null){
                        outputStream.close();
                    }
                }
            }
        };
        return out;
    }
}
