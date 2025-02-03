package model;

import java.util.ArrayList;

//This class takes Course and return a list of course name in a timetable.
public class TimetableGetter {

    private String name;
    private final ArrayList<Integer> creditList = new ArrayList<>();
    private final ArrayList<Course> allCourse = new ArrayList<>();
    private ArrayList<ArrayList<Course>> weekCheck = new ArrayList<ArrayList<Course>>();
    private static ArrayList<ArrayList<ArrayList<String>>> candidate = new ArrayList<>();
    private ArrayList<Course> mon = new ArrayList<Course>();
    private ArrayList<Course> tue = new ArrayList<Course>();
    private ArrayList<Course> wed = new ArrayList<Course>();
    private ArrayList<Course> thu = new ArrayList<Course>();
    private ArrayList<Course> fri = new ArrayList<Course>();
    private int positionCount = 0;
    private int maxCredit = 0;
    private ArrayList<String> set = new ArrayList<>();
    private ArrayList<String> nameOfAll = new ArrayList<>();



    //Effect: Create a blank timetable used for looping check.
    public TimetableGetter() {
        construct();
        for (int s = 0; s < 100; s++) {
            candidate.add(s, new ArrayList<ArrayList<String>>());
        }
        for (ArrayList<ArrayList<String>> week : candidate) {
            for (int i = 0; i < 5; i++) {
                week.add(i, new ArrayList<String>());
            }
            for (ArrayList<String> day : week) {
                for (int j = 0; j < 24; j++) {
                    day.add(j, null);
                }
            }
        }
    }

    //Require: allCourse is not empty
    //effect: return all courses in allcourse
    public ArrayList<Course> getAllCourse() {
        EventLog.getInstance().logEvent(new Event("Courses' details are printed"));
        return allCourse;

    }

    //modify: this:
    //effect: modify mon tue wed thu fri
    private void construct() {
        for (int i = 0; i < 24; i++) {
            mon.add(i, null);
            tue.add(i, null);
            wed.add(i, null);
            thu.add(i, null);
            fri.add(i, null);
        }
        weekCheck.add(mon);
        weekCheck.add(tue);
        weekCheck.add(wed);
        weekCheck.add(thu);
        weekCheck.add(fri);
    }

    //REQUIRE:A course
    //MODIFY: this
    //EFFECT allCourse has an extra course.
    public void addCourse(Course course) {
        allCourse.add(course);
        EventLog.getInstance().logEvent(new Event("A Course "  + course.getName() +  " is added."));
    }

    //REQUIRE: allCourse >= 1.
    //MODIFY: this
    //EFFECT: Return a list of all the added courses' name.
    public ArrayList<String> showAllCourse() {
        for (Course course : allCourse) {
            nameOfAll.add(course.getName());
        }
        EventLog.getInstance().logEvent(new Event("Courses' name are printed"));
        return nameOfAll;
    }

    //Require: allcourse.size > 0.
    //MODIFY: this
    //Return a list of list of string that represent the generated timetable by calling a helpet
    // to work on allcourse
    public ArrayList<ArrayList<String>> getResult() {
        helper(allCourse, 0);
        EventLog.getInstance().logEvent(new Event("Timetable is generated."));
        return modify(getTimetable());
    }


    //MODIFY: this
    //Effect: null to "null"
    public ArrayList<ArrayList<String>> modify(ArrayList<ArrayList<String>> temp) {
        for (ArrayList<String> day : temp) {
            for (int i = 0; i < day.size(); i++) {
                if (day.get(i) == null) {
                    day.set(i, "null");
                }
            }
        }
        return temp;
    }

    //Require: creditlist.size() and candidate.size() greater than 0.
    //MODIFY: this
    //Effect: Return the timetable with the most credit by getting the respective credit index in creditlist.
    public ArrayList<ArrayList<String>> getTimetable() {
        for (Integer credit : creditList) {
            if (credit > maxCredit) {
                maxCredit = credit;
            }
        }
        return candidate.get(creditList.indexOf(maxCredit));
    }

    //Require: allCourse.size() >= 1
    //Modify: this
    //Effect: Loop with all possible combination of course, save them to candidate.
    //Calculate the sum of credit of each possible timetable and save it to credit sum.
    private void helper(ArrayList<Course> allCourse, int pos) {
        if (pos >= (allCourse.size() - 1)) {
            int creditSum = 0;
            for (Course course : allCourse) {
                if (conditionCheck(course)) {
                    formatCourse(course, candidate.get(positionCount));
                    creditSum = creditSum + course.getCredit();
                }
            }
            creditList.add(creditSum);
            tempSetter();
            positionCount = positionCount + 1;
            return;
        }

        for (int j = pos; j < allCourse.size(); j++) {
            Course c = allCourse.get(pos);
            allCourse.set(pos, allCourse.get(j));
            allCourse.set(j, c);

            helper(allCourse, pos + 1);

            c = allCourse.get(pos);
            allCourse.set(pos, allCourse.get(j));
            allCourse.set(j, c);
        }
    }

    //Effect: Return the number of credit of the returned timetable.
    public int sumOfCredit() {
        return maxCredit;
    }

    //Require: candidate.size() >= 1
    //modify: this
    //Effect: Return the number of course of the returned timetable.
    //Return size -1 to ignore null in the list.
    public int numberOfCourse() {
        for (ArrayList<String> day: getTimetable()) {
            for (String name : day) {
                if (!set.contains(name)) {
                    set.add(name);
                }
            }
        }
        return (set.size() - 1);
    }

    //Modify: this
    //Effect: Set every index of the above arrayList to null.
    private void tempSetter() {
        for (int i = 0; i < 24; i++) {
            mon.set(i, null);
            tue.set(i, null);
            wed.set(i, null);
            thu.set(i, null);
            fri.set(i, null);
        }
    }

    //Effect: Return true if all helper return true.
    private boolean conditionCheck(Course course) {
        return  dayCheck(course, course.getMonStart(), course.getMonEnd(), mon)
                && dayCheck(course, course.getTueStart(), course.getTueEnd(), tue)
                && dayCheck(course, course.getWedStart(), course.getWedEnd(), wed)
                && dayCheck(course, course.getThuStart(), course.getThuEnd(), thu)
                && dayCheck(course, course.getFriStart(), course.getFriEnd(), fri);
    }

    //Effect: Check if the respective index, starting from start to end, is null in the arrayList day is null.
    //If yes, return true. Otherwise, false.
    private boolean dayCheck(Course course, int start, int end, ArrayList<Course> day) {
        for (int i = start; i < end; i++) {
            if (day.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    //Modify: this
    //Effect: Loop the helper from 0 to 23.
    private void formatCourse(Course course, ArrayList<ArrayList<String>> week) {
        for (int j = 0; j < 24; j++) {
            formatMon(course, week, j);
            formatTue(course, week, j);
            formatWed(course, week, j);
            formatThu(course, week, j);
            formatFri(course, week, j);
        }
    }

    //Modify: this
    //Effect: if there is no course conflict on monday with the given course, add it
    // to the candidate and the conflict daychecker (mon).
    private void formatMon(Course course, ArrayList<ArrayList<String>> week, int j) {
        if (j >= course.getMonStart() && j < course.getMonEnd()) {
            mon.set(j, course);
            week.get(0).set(j, course.getName());

        }
    }

    //Modify:this
    //Effect: if there is no course conflict on tue with the given course, add it
    // to the candidate and the conflict daychecker (tue).
    private void formatTue(Course course, ArrayList<ArrayList<String>> week, int j) {
        if (j >= course.getTueStart() && j < course.getTueEnd()) {
            tue.set(j, course);
            week.get(1).set(j, course.getName());
        }
    }

    //Modify: this
    //Effect: if there is no course conflict on wed with the given course, add it
    // to the candidate and the conflict daychecker (wed).
    private void formatWed(Course course, ArrayList<ArrayList<String>> week, int j) {
        if (j >= course.getWedStart() && j < course.getWedEnd()) {
            wed.set(j, course);
            week.get(2).set(j, course.getName());
        }
    }

    //Modify: this
    //Effect: if there is no course conflict on thu with the given course, add it
    // to the candidate and the conflict daychecker (thu).
    private void formatThu(Course course, ArrayList<ArrayList<String>> week, int j) {
        if (j >= course.getThuStart() && j < course.getThuEnd()) {
            thu.set(j, course);
            week.get(3).set(j, course.getName());
        }
    }

    //Modify: this
    //Effect: if there is no course conflict on fri with the given course, add it
    // to the candidate and the conflict daychecker (fri).
    private void formatFri(Course course, ArrayList<ArrayList<String>> week, int j) {
        if (j >= course.getFriStart() && j < course.getFriEnd()) {
            fri.set(j, course);
            week.get(4).set(j, course.getName());
        }
    }
}
