package model;

import java.util.ArrayList;
import org.json.*;

//This class create a timetable with a list of list.
public class Timetable {

    private ArrayList<ArrayList<String>> timetable;

    //Effect: Form a timetable
    public Timetable(ArrayList<ArrayList<String>> timetable) {
        this.timetable = timetable;
    }

    //Effect: Return timetable
    public ArrayList<ArrayList<String>> getTimetable() {
        return timetable;
    }


    //Effect: form a timetable from JsonArray.
    public static Timetable fromJson(JSONArray jsonArray) {
        ArrayList<ArrayList<String>> translated = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray dayInJson = jsonArray.getJSONArray(i);
            ArrayList<String> day = new ArrayList<>();
            for (int j = 0; j < dayInJson.length(); j++) {
                day.add(dayInJson.getString(j));
            }
            translated.add(day);
        }
        return new Timetable(translated);
    }


    //Effect: form and return a JsonArray
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (ArrayList<String> day : timetable) {
            jsonArray.put(new JSONArray(day));
        }
        return jsonArray;
    }

}
