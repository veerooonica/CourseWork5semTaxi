package com.bus.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Reserves {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    private RouteDate date;
    private String passport;

    public Reserves(String description, String passport) {
        this.description = description;
        this.passport = passport;
    }
}
