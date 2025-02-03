package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//This class create a candidate with a name and timetable
public class Candidates implements Writable {
    private Timetable timetable;
    private String code;
    private int credit;
    private ArrayList<String> names;

    // EFFECTS: constructs a candidate with a name and timetable
    public Candidates(String code, Timetable timetable, int credit, ArrayList<String> names) {
        this.code = code;
        this.timetable = timetable;
        this.credit = credit;
        this.names = names;
    }

    //Effect: Return the name(code) of the timetable
    public String getCode() {
        return code;
    }

    //Effect: Return the timetable
    public Timetable getTimetable() {
        return timetable;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    //Effect: Return a Json Object with the code and timetable in the candidate.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("timetable", timetable.toJson());
        json.put("credit", credit);
        json.put("Courses", names);
        return json;
    }

    //Effect: form a timetable from JsonArray.
    public static ArrayList<String> nameFromJson(JSONArray jsonArray) {
        JSONArray namesInJson = jsonArray;
        ArrayList<String> nameFromJson = new ArrayList<>();
        for (int j = 0; j < namesInJson.length(); j++) {
            nameFromJson.add(namesInJson.getString(j));
        }
        return nameFromJson;
    }
}
