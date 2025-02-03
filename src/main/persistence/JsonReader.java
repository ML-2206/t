package persistence;

import model.Timetable;
import model.Candidates;
import model.WorkRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private WorkRoom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkRoom wr = new WorkRoom(name);
        addCandidates(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses candidates from JSON object and adds them to workroom
    private void addCandidates(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("timetables");
        for (Object json : jsonArray) {
            JSONObject nextCandidate = (JSONObject) json;
            addCandidate(wr, nextCandidate);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses candidate from JSON object and adds it to workroom
    private void addCandidate(WorkRoom wr, JSONObject jsonObject) {
        String code = jsonObject.getString("code");
        JSONArray timetableJson = jsonObject.getJSONArray("timetable");
        Timetable timetable = Timetable.fromJson(timetableJson);
        int credit = jsonObject.getInt("credit");
        JSONArray namesJson = jsonObject.getJSONArray("Courses");
        ArrayList<String> names = Candidates.nameFromJson(namesJson);
        Candidates candidate = new Candidates(code, timetable, credit, names);
        wr.addTimetable(candidate);
    }

}
