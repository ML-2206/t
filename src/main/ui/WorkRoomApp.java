package ui;

import model.Candidates;
import model.Timetable;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the workroom application
public class WorkRoomApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner input;
    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private TimeTableAPP app;

    // EFFECTS: constructs workroom and runs application
    public WorkRoomApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        workRoom = new WorkRoom("Alex's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWorkRoom();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runWorkRoom() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add timetable");
        System.out.println("\tp -> print timetables");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTimetable();
        } else if (command.equals("p")) {
            printJsonObjectBuilders();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for name(code) and timetable of candidate and adds to workroom
    private void addTimetable() {
        app = new TimeTableAPP();
        Timetable timetable = app.getTimetable();
        int credit = app.getCredit();
        ArrayList<String> names = app.getNames();
        System.out.println("Please enter name of this timetable: ");
        String name = input.next();
        workRoom.addTimetable(new Candidates(name, timetable, credit, names));
    }


    // EFFECTS: prints all the timetable in workroom to the console
    private void printJsonObjectBuilders() {
        List<Candidates> candidates = workRoom.getCandidates();
        for (Candidates t : candidates) {
            System.out.println("The code is: " + t.getCode());;
            System.out.println("The timetable is: " + t.getTimetable().getTimetable());
            System.out.println("The sum of credit of this timetable is: " + t.getCredit());
            System.out.println("The courses used to get this timetable is: " + t.getNames());
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
