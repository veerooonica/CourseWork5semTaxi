package com.bus.cont;

import com.bus.cont.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartCont extends Attributes {

    @GetMapping("/cart")
    public String Cart(Model model) {
        AddAttributesCart(model);
        return "cart";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam String search) {
        AddAttributesSearch(model, search);
        return "cart";
    }

}
