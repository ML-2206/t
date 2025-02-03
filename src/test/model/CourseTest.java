package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course c1;
    private Course c2;

    @BeforeEach
    void runBefore() {
        c1 = new Course("MATH200", 3, 10, 11, 10, 11, 10,
                11, 10, 11, 10, 11);
        c2 = new Course("ENG100", 4, 9, 10, 9, 10, 9, 10,
                9, 10, 9, 10);
    }

    @Test
    void getNameTest() {
        assertEquals("MATH200", c1.getName());
        assertEquals("ENG100", c2.getName());
    }

    @Test
    void getCreditTest() {
        assertEquals(3, c1.getCredit());
        assertEquals(4, c2.getCredit());
    }

    @Test
    void getMonStartTest() {
        assertEquals(10, c1.getMonStart());
        assertEquals(9, c2.getMonStart());
    }

    @Test
    void getMonEndTest() {
        assertEquals(11, c1.getMonEnd());
        assertEquals(10, c2.getMonEnd());
    }

    @Test
    void getTueStartTest() {
        assertEquals(10, c1.getTueStart());
        assertEquals(9, c2.getTueStart());
    }

    @Test
    void getTueEndTest() {
        assertEquals(11, c1.getTueEnd());
        assertEquals(10, c2.getTueEnd());
    }

    @Test
    void getWedStartTest() {
        assertEquals(10, c1.getWedStart());
        assertEquals(9, c2.getWedStart());
    }

    @Test
    void getWedEndTest() {
        assertEquals(11, c1.getWedEnd());
        assertEquals(10, c2.getWedEnd());
    }

    @Test
    void getThuStartTest() {
        assertEquals(10, c1.getThuStart());
        assertEquals(9, c2.getThuStart());
    }

    @Test
    void getThuEndTest() {
        assertEquals(11, c1.getThuEnd());
        assertEquals(10, c2.getThuEnd());
    }

    @Test
    void getFriStartTest() {
        assertEquals(10, c1.getFriStart());
        assertEquals(9, c2.getFriStart());
    }

    @Test
    void getFriEndTest() {
        assertEquals(11, c1.getFriEnd());
        assertEquals(10, c2.getFriEnd());
    }
}