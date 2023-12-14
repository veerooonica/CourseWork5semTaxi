package com.bus.test;

import com.bus.models.Reserves;
import com.bus.models.RouteDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservesTest {

    @Test
    public void testConstructorAndGetters() {
        String description = "Reservation description";
        String passport = "AB123456";
        Reserves reserves = new Reserves(description, passport);

        assertEquals(description, reserves.getDescription());
        assertEquals(passport, reserves.getPassport());
    }



    @Test
    public void testSetAndGetDate() {
        String description = "Reservation description";
        String passport = "AB123456";
        Reserves reserves = new Reserves(description, passport);

        RouteDate routeDate = new RouteDate();
        reserves.setDate(routeDate);

        assertEquals(routeDate, reserves.getDate());
    }
}
