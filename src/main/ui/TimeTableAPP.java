package ui;

import model.Course;
import model.Timetable;
import model.TimetableGetter;

import java.util.ArrayList;
import java.util.Scanner;

//This class allow user to enter input and give the output.
public class TimeTableAPP {
    private Scanner input;
    private TimetableGetter getter;
    private Timetable timetable;
    private int credit;
    private ArrayList<String> names = new ArrayList<>();

    private String answerForCredit;

    //Effect: Call runTimetable().
    public TimeTableAPP() {
        runTimetable();
    }

    //Effect: Ask the user to enter all the courses, after that, print the timetable with most credits.
    public void runTimetable() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        addCourse();
        System.out.print(getter.getResult());
        timetable = new Timetable(getter.getResult());
        System.out.print("want to know the credit sum?");
        credit = getter.sumOfCredit();
        String command = input.next();
        if (command.equals("YES")) {
            System.out.println(getter.sumOfCredit());
        }
        System.out.print("want to know the number of course?");
        command = input.next();
        if (command.equals("YES")) {
            System.out.println(getter.numberOfCourse());
        }
        showName();
    }

    //Effect:  return timetable
    public Timetable getTimetable() {
        return timetable;
    }

    //Effect:  return credit
    public int getCredit() {
        return credit;
    }

    //Effect:  return names
    public ArrayList<String> getNames() {
        return names;
    }

    //Effect: Add Course to the timetable until "NO" is entered.
    private void addCourse() {
        getter = new TimetableGetter();
        boolean status = true;
        while (status) {
            getter.addCourse(getInformation());
            System.out.print("\nAnymore course?:");
            String command = input.next();
            if (command.equals("NO")) {
                status = false;
            }
        }
    }

    //Effect: print all the courses' name
    private void showName() {
        String command;
        System.out.print("want to get the name of all added course?");
        command = input.next();
        names = getter.showAllCourse();
        if (command.equals("YES")) {
            System.out.println(getter.showAllCourse());
        }
    }

    //Effect: return a Course with all parameter needed.
    private Course getInformation() {
        return new Course(getCourseName(), getCourseCredit(), getMonStart(), getMonEnd(),
                getTueStart(), getTueEnd(), getWedStart(), getWedEnd(), getThuStart(),
                getThuEnd(), getFriStart(), getFriEnd());
    }

    //Effect: Return the name of the course.
    private String getCourseName() {
        System.out.print("Course code:");
        String name = input.next();
        return name;
    }

    //Effect: Return the number of credit of the course.
    private int getCourseCredit() {
        System.out.print("Course credit:");
        int credit = input.nextInt();
        return credit;
    }

    //Effect: Return the starting time on monday of the course.
    private int getMonStart() {
        System.out.print("Monday start time:");
        int monStart = input.nextInt();
        return monStart;
    }

    //Effect: Return the ending time on monday of the course.
    private int getMonEnd() {
        System.out.print("Monday end time:");
        int monEnd = input.nextInt();
        return monEnd;
    }

    //Effect: Return the starting time on tuesday of the course.
    private int getTueStart() {
        System.out.print("Tuesday start time:");
        int tueStart = input.nextInt();
        return tueStart;
    }

    //Effect: Return the ending time on monday of the course.
    private int getTueEnd() {
        System.out.print("Tuesday end time:");
        int tueEnd = input.nextInt();
        return tueEnd;
    }

    //Effect: Return the starting time on wedensday of the course.
    private int getWedStart() {
        System.out.print("Wednesday start time:");
        int wedStart = input.nextInt();
        return wedStart;
    }

    //Effect: Return the ending time on wedensday of the course.
    private int getWedEnd() {
        System.out.print("Wednesday end time:");
        int wedEnd = input.nextInt();
        return wedEnd;
    }

    //Effect: Return the starting time on thursday of the course.
    private int getThuStart() {
        System.out.print("Thursday start time:");
        int thuStart = input.nextInt();
        return thuStart;
    }

    //Effect: Return the ending time on thurday of the course.
    private int getThuEnd() {
        System.out.print("Thursday end time:");
        int thuEnd = input.nextInt();
        return thuEnd;
    }

    //Effect: Return the starting time on friday of the course.
    private int getFriStart() {
        System.out.print("Friday start time:");
        int friStart = input.nextInt();
        return friStart;
    }

    //Effect: Return the ending time on friday of the course.
    private int getFriEnd() {
        System.out.print("Friday end time:");
        int friEnd = input.nextInt();
        return friEnd;
    }

}