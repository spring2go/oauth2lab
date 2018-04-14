package io.spring2go.oauth2.oauth2serverstate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    @GetMapping("/api/username")
    public ResponseEntity<String> getUsername() {
        UserInfo user = (UserInfo) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("success " + user.getUsername());
    }

}
