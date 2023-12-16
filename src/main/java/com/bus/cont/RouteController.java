package com.bus.cont;

import com.bus.cont.main.Attributes;
import com.bus.models.RouteDate;
import com.bus.models.Routes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RouteController extends Attributes {

    @GetMapping("/route_add")
    public String Route(Model model) {
        AddAttributesRouteAdd(model);
        return "route_add";
    }

    @PostMapping("/route/add")
    public String AddRoute(@RequestParam String begin, @RequestParam String end) {
        repoRouters.save(new Routes(begin, end));
        return "redirect:/route_add";
    }

    @GetMapping("/route/{id}")
    public String route(Model model, @PathVariable Long id) {
        AddAttributesRoute(model, id);
        return "route";
    }

    @GetMapping("/route/{id}/delete")
    public String routeDelete(@PathVariable Long id) {
        repoRouters.deleteById(id);
        List<RouteDate> dateList = repoRouteDate.findByRouteId(id);
        for (RouteDate i : dateList) {
            repoReserves.deleteAll(repoReserves.findByDateId(i.getId()));
        }
        repoRouteDate.deleteAll(dateList);
        return "redirect:/";
    }

    @GetMapping("/route/{id}/edit")
    public String routeEdit(Model model, @PathVariable Long id) {
        AddAttributesRouteEdit(model, id);
        return "route_edit";
    }

    @PostMapping("/route/{id}/edit")
    public String routeEdit1(@PathVariable Long id, @RequestParam String begin, @RequestParam String end) {
        Routes route = repoRouters.getById(id);
        route.setBegin(begin);
        route.setEnd(end);
        repoRouters.save(route);
        return "redirect:/route/{id}";
    }

}
