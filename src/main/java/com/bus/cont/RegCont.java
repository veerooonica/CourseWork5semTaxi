package com.bus.cont;

import com.bus.cont.main.Attributes;
import com.bus.models.Users;
import com.bus.models.enums.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegCont extends Attributes {

    @GetMapping("/reg")
    public String reg(Model model) {
        AddAttributes(model);
        return "reg";
    }

    @PostMapping("/reg")
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String password_repeat, @RequestParam String passport, @RequestParam String tel) {
        if (repoUsers.findAll().size() == 0) {
            repoUsers.save(new Users(username, password, passport, tel, Roles.Водитель));

            return "redirect:/login";
        }

        if (repoUsers.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributes(model);
            return "reg";
        }

        if (!password.equals(password_repeat)) {
            model.addAttribute("message", "Некорректный ввод паролей");
            AddAttributes(model);
            return "reg";
        }

        repoUsers.save(new Users(username, password, passport, tel, Roles.Клиент));

        return "redirect:/login";
    }
}
