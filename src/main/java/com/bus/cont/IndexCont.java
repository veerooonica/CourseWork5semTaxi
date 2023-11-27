package com.bus.cont;

import com.bus.cont.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Attributes {
    @GetMapping("/")
    String index1(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/index")
    String index2(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/rules")
    String rules(Model model) {
        AddAttributes(model);
        return "rules";
    }


}
