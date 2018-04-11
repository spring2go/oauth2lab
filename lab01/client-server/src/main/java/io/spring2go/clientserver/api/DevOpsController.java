package io.spring2go.clientserver.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class DevOpsController {

    @RequestMapping("/userlist")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(getUsers());
    }

    private List<UserInfo> getUsers() {
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo("bobo", "bobo@spring2go.com"));
        users.add(new UserInfo("eric", "eric@spring2go.com"));
        users.add(new UserInfo("franky", "franky@spring2go.com"));

        return users;
    }
}
