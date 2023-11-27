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
public class Routes {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String begin;
    private String end;

    public Routes(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getName() {
        return begin + " - " + end;
    }
}
