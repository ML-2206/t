package persistence;


import model.Candidates;
import model.Timetable;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.


    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(new ArrayList<>(), wr.getCandidates());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            testSetter("NO1",1);
            ArrayList<String> names = new ArrayList<>();
            names.add("NO1");
            wr.addTimetable(new Candidates("A", new Timetable(test), 1, names));

            //testSetter("NO2",3);
            wr.addTimetable(new Candidates("B", new Timetable(test), 1,names));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Candidates> candidates = wr.getCandidates();
            assertEquals(2, candidates.size());

            //testSetter("NO1",1);
            checkCandidate("A", new Timetable(test), 1, names, candidates.get(0));

            //testSetter("NO2",3);
            checkCandidate("B", new Timetable(test), 1, names, candidates.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
