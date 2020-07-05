package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.posts.PostsService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

//  private final HttpSession httpSession;
  private final PostsService postsService;

  @GetMapping("/")
  public String getHome(Model model, @LoginUser SessionUser user){
//    SessionUser user = (SessionUser)httpSession.getAttribute("user");
    model.addAttribute("posts", postsService.findAllDesc());
    if (user != null) {
      model.addAttribute("userName", user.getName());
    }
    return "home";
  }
}
