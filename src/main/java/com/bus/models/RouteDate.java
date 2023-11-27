package com.bus.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RouteDate {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private String time_begin;
    private String time_end;
    private int quantity;
    private Long routeId;

    public RouteDate(String date, String time_begin, String time_end, int quantity, Long routeId) {
        this.date = date;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.quantity = quantity;
        this.routeId = routeId;
    }

    public String getName() {
        return date + " - " + time_begin + " - " + time_end;
    }
}
