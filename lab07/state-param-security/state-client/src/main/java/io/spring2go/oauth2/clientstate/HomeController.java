package io.spring2go.oauth2.clientstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @GetMapping("/")
    public ModelAndView home() {
        User user = (User) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("username", user.getUsername());
        return mv;
    }

    @GetMapping("/resource")
    public ModelAndView resource() {
        String result = restTemplate
                .getForObject("http://localhost:8080/api/username", String.class);

        ModelAndView mv = new ModelAndView("resource");
        mv.addObject("result", result);
        return mv;
    }

}
