package com.bus.cont;

import com.bus.cont.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends Attributes {

    @GetMapping("/login")
    String login(Model model) {
        AddAttributesLogin(model);
        return "login";
    }

}
