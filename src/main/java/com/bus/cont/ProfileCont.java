package com.bus.cont;

import com.bus.cont.main.Attributes;
import com.bus.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileCont extends Attributes {

    @GetMapping("/profile")
    public String profile(Model model) {
        AddAttributesProfile(model);
        return "profile";
    }

    @PostMapping("/profile_update")
    public String profileUpdate(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String password_repeat, @RequestParam String passport, @RequestParam String tel) {
        if (!getUser().getPassword().equals(password)) {
            model.addAttribute("message", "Некорректный ввод пароля");
            AddAttributesProfile(model);
            return "profile";
        }

        if (password_repeat != null && !password_repeat.equals("")) {
            if (!password.equals(password_repeat)) {
                model.addAttribute("message", "Некорректный ввод паролей");
                AddAttributesProfile(model);
                return "profile";
            }
        }

        Users user = getUser();

        user.setUsername(username);
        user.setPassport(passport);
        user.setTel(tel);

        repoUsers.save(user);

        return "redirect:/profile";
    }

}
