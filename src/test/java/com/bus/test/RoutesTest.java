package com.bus.test;

import com.bus.models.Routes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoutesTest {

    @Test
    public void testGetName() {
        String begin = "Start";
        String end = "Finish";
        Routes routes = new Routes(begin, end);

        String expectedName = "Start - Finish";
        String actualName = routes.getName();

        assertEquals(expectedName, actualName);
    }



    @Test
    public void testGetBegin() {
        String begin = "Start";
        String end = "Finish";
        Routes routes = new Routes(begin, end);

        String expectedBegin = "Start";
        String actualBegin = routes.getBegin();

        assertEquals(expectedBegin, actualBegin);
    }

    @Test
    public void testGetEnd() {
        String begin = "Start";
        String end = "Finish";
        Routes routes = new Routes(begin, end);

        String expectedEnd = "Finish";
        String actualEnd = routes.getEnd();

        assertEquals(expectedEnd, actualEnd);
    }
}
