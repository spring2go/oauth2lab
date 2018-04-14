package io.spring2go.jwtresourceserver.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUerInfo() {
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = username + "@spring2go.com";

        UserInfo userInfo = new UserInfo(username, email);

        return ResponseEntity.ok(userInfo);
    }

}
