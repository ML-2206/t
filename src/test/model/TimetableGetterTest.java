package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableGetterTest {
    private Course c1;
    private Course c2;
    private Course c3;
    private Course c4;
    private Course c5;
    private TimetableGetter t1;
    private ArrayList<ArrayList<String>> storage = new ArrayList<ArrayList<String>>();
    private ArrayList<String> day1 = new ArrayList<>();
    private ArrayList<String> day2 = new ArrayList<>();
    private ArrayList<String> day3 = new ArrayList<>();
    private ArrayList<String> day4 = new ArrayList<>();
    private ArrayList<String> day5 = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        c1 = new Course("MATH", 3, 10, 11, 10, 11, 10, 11,
                10, 11, 10, 11);
        c2 = new Course("ENG", 4, 9, 10, 9, 10, 9, 10,
                9, 10, 9, 10);
        c3 = new Course("ART",5, 9,10,9,10,9,10,
                9, 10,9,10);
        c4 = new Course("SCI", 4, 9,10,9,10,9,10,
                9, 10,9,10);
        t1 = new TimetableGetter();
        for (int j = 0; j < 24; j++) {
            day1.add("null");
            day2.add("null");
            day3.add("null");
            day4.add("null");
            day5.add("null");
        }
        storage.add(day1);
        storage.add(day2);
        storage.add(day3);
        storage.add(day4);
        storage.add(day5);
    }

    private void setter(String s, int time) {
        day1.set(time, s);
        day2.set(time, s);
        day3.set(time, s);
        day4.set(time, s);
        day5.set(time, s);
    }

    private void clear() {
        for (int j = 0; j < 24; j++) {
            day1.set(j,null);
            day2.set(j,null);
            day3.set(j,null);
            day4.set(j,null);
            day5.set(j,null);
        }
    }

    @Test
    void getCoursesName() {
        t1.addCourse(c1);
        t1.addCourse(c2);
        t1.addCourse(c3);
        t1.addCourse(c4);
        name.add(c1.getName());
        name.add(c2.getName());
        name.add(c3.getName());
        name.add(c4.getName());
        assertEquals(name, t1.showAllCourse());
    }

    @Test
    void getCourse() {
        t1.addCourse(c1);
        t1.addCourse(c2);
        ArrayList<Course> temp = t1.getAllCourse();
        ArrayList<Course> temp1 = new ArrayList<>();
        temp1.add(c1);
        temp1.add(c2);
        assertEquals(temp1, temp);
    }

    @Test
    void allDayHaveCourse() {
        t1.addCourse(c1);
        t1.addCourse(c2);
        t1.addCourse(c3);
        t1.addCourse(c4);
        setter("ART",9);
        setter("MATH",10);
        assertEquals(storage, t1.getResult());
        assertEquals(2, t1.numberOfCourse());
        assertEquals(8, t1.sumOfCredit());

    }

    @Test
    void TueConflictTest() {
        c5 = new Course("BIO",5, 8,9,9,10,9,10,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        t1.addCourse(c2);
        setter("MATH",10);
        setterForOneDay("BIO", 8, day1);
        setterForMonMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }

    @Test
    void WedConflictTest() {
        c5 = new Course("BIO",5, 8,9,8,9,9,10,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c2);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForOneDay("BIO", 8, day1);
        setterForOneDay("BIO", 8, day2);
        setterForOneDay("BIO", 9, day3);
        setterForOneDay("BIO", 9, day4);
        setterForOneDay("BIO", 9, day5);
        assertEquals(storage, t1.getResult());
    }

    @Test
    void ThuConflictTest() {
        c5 = new Course("BIO",5, 8,9,8,9,8,9,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        t1.addCourse(c2);
        setter("MATH",10);
        setterForOneDay("BIO", 8, day1);
        setterForOneDay("BIO", 8, day2);
        setterForOneDay("BIO", 8, day3);
        setterForOneDay("BIO", 9, day4);
        setterForOneDay("BIO", 9, day5);
        assertEquals(storage, t1.getResult());
    }

    @Test
    void FriConflictTest() {
        c5 = new Course("BIO",5, 8,9,8,9,8,9,
                8, 9,9,10);
        t1.addCourse(c1);
        t1.addCourse(c2);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForOneDay("BIO", 8, day1);
        setterForOneDay("BIO", 8, day2);
        setterForOneDay("BIO", 8, day3);
        setterForOneDay("BIO", 8, day4);
        setterForOneDay("BIO", 9, day5);
        assertEquals(storage, t1.getResult());
    }

    private void setterForOneDay(String s, int time, ArrayList<String> day){
        day.set(time,s);
    }

    private void setterForMonMissingDay(String s, int time) {
        day2.set(time, s);
        day3.set(time, s);
        day4.set(time, s);
        day5.set(time, s);
    }

    @Test
    void MonMissingTest() {
        c5 = new Course("BIO",5, 0,0,9,10,9,10,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForMonMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }

    private void setterForTueMissingDay(String s, int time) {
        day1.set(time, s);
        day3.set(time, s);
        day4.set(time, s);
        day5.set(time, s);
    }

    @Test
    void TueMissingTest() {
        c5 = new Course("BIO",5, 9,10,0,0,9,10,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForTueMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }

    private void setterForWedMissingDay(String s, int time) {
        day1.set(time, s);
        day2.set(time, s);
        day4.set(time, s);
        day5.set(time, s);
    }

    @Test
    void WedMissingTest() {
        c5 = new Course("BIO",5, 9,10,9,10,0,0,
                9, 10,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForWedMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }

    private void setterForThuMissingDay(String s, int time) {
        day1.set(time, s);
        day2.set(time, s);
        day3.set(time, s);
        day5.set(time, s);
    }

    @Test
    void ThuMissingTest() {
        c5 = new Course("BIO",5, 9,10,9,10,9,10,
                0, 0,9,10);
        t1.addCourse(c1);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForThuMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }

    private void setterForFriMissingDay(String s, int time) {
        day1.set(time, s);
        day2.set(time, s);
        day3.set(time, s);
        day4.set(time, s);
    }

    @Test
    void FriMissingTest() {
        c5 = new Course("BIO",5, 9,10,9,10,9,10,
                9, 10,0,0);
        t1.addCourse(c1);
        t1.addCourse(c5);
        setter("MATH",10);
        setterForFriMissingDay("BIO",9);
        assertEquals(storage, t1.getResult());
    }







}
