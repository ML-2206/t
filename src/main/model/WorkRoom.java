package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of timetable
public class WorkRoom implements Writable {
    private String name;
    private List<Candidates> candidates;

    // EFFECTS: constructs workroom with a name and empty list of candidates
    public WorkRoom(String name) {
        this.name = name;
        candidates = new ArrayList<>();
    }

    //Effect: Return name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds timetable to this workroom
    public void addTimetable(Candidates task) {
        candidates.add(task);
    }

    // EFFECTS: returns an unmodifiable list of candidates in this workroom
    public List<Candidates> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }

    // EFFECTS: returns number of candidates in this workroom
    public int numCandidates() {
        return candidates.size();
    }

    //Effect: create a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("timetables", timetableToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray timetableToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Candidates candidate : candidates) {
            jsonArray.put(candidate.toJson());
        }
        return jsonArray;
    }
}

