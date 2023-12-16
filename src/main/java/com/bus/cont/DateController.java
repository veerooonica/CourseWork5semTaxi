package com.bus.cont;

import com.bus.cont.main.Attributes;
import com.bus.models.RouteDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController extends Attributes {

    @GetMapping("/route/{id}/date_add")
    public String Date(Model model, @PathVariable Long id) {
        AddAttributesDateAdd(model, id);
        return "date_add";
    }

    @PostMapping("/route/{id}/date/add")
    public String AddDate(@PathVariable Long id, @RequestParam String date, @RequestParam String time_begin, @RequestParam String time_end, @RequestParam int quantity) {
        Date begin;
        Date end;
        try {
            begin = new SimpleDateFormat("HH:mm").parse(time_begin);
            end = new SimpleDateFormat("HH:mm").parse(time_end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (end.before(begin)) {
            return "redirect:/route/{id}/date_add";
        }
        repoRouteDate.save(new RouteDate(date, time_begin, time_end, quantity, id));
        return "redirect:/route/{id}/date_add";
    }

    @GetMapping("/route/{id_route}/date/{id_date}/edit")
    public String DateEdit(Model model, @PathVariable Long id_route, @PathVariable Long id_date) {
        AddAttributesDateEdit(model, id_route, id_date);
        return "date_edit";
    }

    @PostMapping("/route/{id_route}/date/{id_date}/edit")
    public String DateEdit1(@PathVariable Long id_route, @PathVariable Long id_date, @RequestParam String date, @RequestParam String time_begin, @RequestParam String time_end, @RequestParam int quantity) {
        Date begin;
        Date end;
        try {
            begin = new SimpleDateFormat("HH:mm").parse(time_begin);
            end = new SimpleDateFormat("HH:mm").parse(time_end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (end.before(begin)) {
            return "redirect:/route/{id_route}/date/{id_date}/edit";
        }
        RouteDate routeDate = repoRouteDate.getById(id_date);
        routeDate.setDate(date);
        routeDate.setTime_begin(time_begin);
        routeDate.setTime_end(time_end);
        routeDate.setQuantity(quantity);
        repoRouteDate.save(routeDate);
        return "redirect:/route/{id_route}";
    }

    @GetMapping("/route/{id_route}/date/{id_date}/delete")
    public String DateDelete(@PathVariable Long id_route, @PathVariable Long id_date) {
        repoRouteDate.deleteById(id_date);
        repoReserves.deleteAll(repoReserves.findByDateId(id_date));
        return "redirect:/route/{id_route}";
    }


}
