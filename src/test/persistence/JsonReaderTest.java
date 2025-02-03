
package persistence;

import model.Candidates;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkRoom wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numCandidates());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Candidates> candidates = wr.getCandidates();
            ArrayList<String> names = new ArrayList<>();
            names.add("NO1");
            assertEquals(2, candidates.size());
            testSetter("NO1",1);
            checkCandidate("A", candidates.get(0).getTimetable(), 1, names, candidates.get(0));
            checkCandidate("B", candidates.get(1).getTimetable(), 1, names, candidates.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
