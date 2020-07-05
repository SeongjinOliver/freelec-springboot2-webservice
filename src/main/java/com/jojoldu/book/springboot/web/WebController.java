package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.s3.S3Uploader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class WebController {

  private final S3Uploader s3Uploader;

//  @GetMapping("/")
//  public String home() {
//    return "home";
//  }

  @PostMapping("/upload")
  @ResponseBody
  public String upload(@RequestParam("data")MultipartFile multipartFile) throws IOException {
    return s3Uploader.upload(multipartFile, "static");
  }
}
