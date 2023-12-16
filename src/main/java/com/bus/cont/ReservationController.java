package com.bus.cont;

import com.bus.cont.main.Attributes;
import com.bus.models.Reserves;
import com.bus.models.RouteDate;
import com.bus.models.Routes;
import com.bus.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController extends Attributes {

    @GetMapping("/route/{id_route}/date/{id_date}/reserve")
    public String reserve(Model model, @PathVariable Long id_date, @PathVariable Long id_route) {
        if (getRole().equals("NOT")) {
            AddAttributesReservation(model);
            model.addAttribute("date", repoRouteDate.getById(id_date));
            model.addAttribute("route", repoRouters.getById(id_route));
            return "reserve";
        }

        RouteDate date = repoRouteDate.getById(id_date);
        date.setQuantity(date.getQuantity() - 1);
        Users user = getUser();
        Routes route = repoRouters.getById(id_route);

        Reserves reserve = new Reserves(route.getName() + " : " + date.getName() + " : " + user.getUsername() + " - " + user.getTel(), user.getPassport());

        reserve.setDate(date);
        repoReserves.save(reserve);

        return "redirect:/route/{id_route}";
    }

    @PostMapping("/route/{id_route}/date/{id_date}/reserve/no_user")
    public String reserveNoUser(@PathVariable Long id_date, @PathVariable Long id_route, @RequestParam String username, @RequestParam String tel, @RequestParam String passport) {
        RouteDate date = repoRouteDate.getById(id_date);
        date.setQuantity(date.getQuantity() - 1);
        Routes route = repoRouters.getById(id_route);

        Reserves reserve = new Reserves(route.getName() + " : " + date.getName() + " : " + username + " - " + tel, passport);
        reserve.setDate(date);

        repoReserves.save(reserve);

        return "redirect:/route/{id_route}";
    }

    @GetMapping("/reserve/cancel/{id}/{passport}")
    public String reserveCancel(Model model, @PathVariable Long id, @PathVariable String passport) {
        Reserves reserves = repoReserves.getById(id);
        RouteDate date = repoRouteDate.getById(reserves.getDate().getId());
        repoReserves.delete(reserves);
        date.setQuantity(date.getQuantity() + 1);
        repoRouteDate.save(date);
        if (getRole().equals("NOT")) {
            AddAttributesSearch(model, passport);
            return "cart";
        }
        return "redirect:/cart";
    }

}
