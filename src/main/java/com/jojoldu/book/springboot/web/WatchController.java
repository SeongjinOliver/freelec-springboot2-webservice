package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WatchController {

  @GetMapping("/watch")
  public String watch(Model model, @LoginUser SessionUser user){
    if (user != null) {
      model.addAttribute("userName", user.getName());
    }
    return "watch";
  }
}
