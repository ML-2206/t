package persistence;

import model.Candidates;
import model.Timetable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    protected ArrayList<ArrayList<String>> test = new ArrayList<>();
    protected ArrayList<String> test1 = new ArrayList<>();
    protected ArrayList<String> test2 = new ArrayList<>();
    protected ArrayList<String> test3 = new ArrayList<>();
    protected ArrayList<String> test4 = new ArrayList<>();
    protected ArrayList<String> test5 = new ArrayList<>();

    protected void checkCandidate(String name, Timetable timetable, int credit, ArrayList<String> names,
                                  Candidates candidates) {
        assertEquals(name, candidates.getCode());
        assertEquals(timetable.getTimetable(), candidates.getTimetable().getTimetable());
        assertEquals(credit, candidates.getCredit());
        assertEquals(names, candidates.getNames());

    }

    protected void testSetter(String sec, int pos) {
        test.add(test1);
        test.add(test2);
        test.add(test3);
        test.add(test4);
        test.add(test5);
        for (ArrayList<String> testX : test) {
            for (int i = 0; i < 23; i++) {
                if (i != pos) {
                    testX.add("null");
                } else {
                    testX.add(sec);
                }
            }
        }
    }
}

