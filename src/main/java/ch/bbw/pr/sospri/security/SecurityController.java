package ch.bbw.pr.sospri.security;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginout")
    public String loginout() {
        return "logout";
    }
}
