package com.springboot.firststep.web;

import com.springboot.firststep.config.auth.LoginUser;
import com.springboot.firststep.config.auth.dto.SessionUser;
import com.springboot.firststep.domain.user.User;
import com.springboot.firststep.service.posts.PostsService;
import com.springboot.firststep.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        // 어노테이션을 통해 Session에 저장된 user의 내용을 가져오기 때문에 아래의 코드는 필요가 없어진다.
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null)
            model.addAttribute("userName", user.getName());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
