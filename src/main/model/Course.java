package model;

//This class creates a course with parameter needed.
public class Course {
    private String name;
    private final int credit;
    private final int monStart;
    private final int monEnd;
    private final int tueStart;
    private final int tueEnd;
    private final int wedStart;
    private final int wedEnd;
    private final int thuStart;
    private final int thuEnd;
    private final int friStart;
    private final int friEnd;


    //Effect: Create a course with the parameter.
    public Course(String section, int credit, int monStart, int monEnd, int tueStart, int tueEnd, int wedStart,
                  int wedEnd, int thuStart, int thuEnd, int friStart, int friEnd) {
        this.name = section;
        this.credit = credit;
        this.monStart = monStart;
        this.monEnd = monEnd;
        this.tueStart = tueStart;
        this.tueEnd = tueEnd;
        this.wedStart = wedStart;
        this.wedEnd = wedEnd;
        this.thuStart = thuStart;
        this.thuEnd = thuEnd;
        this.friStart = friStart;
        this.friEnd = friEnd;
    }

    //effect: return the name of the course.
    public String getName() {
        return name;
    }

    //effect: return the number of credit of the course.
    public int getCredit() {
        return this.credit;
    }

    //effect: return the start time on Monday of the course.
    public int getMonStart() {
        return monStart;
    }

    //effect: return the end time on Monday of the course.
    public int getMonEnd() {
        return monEnd;
    }

    //effect: return the start time on Tuesday of the course.
    public int getTueStart() {
        return tueStart;
    }

    //effect: return the end time on Tuesday of the course.
    public int getTueEnd() {
        return tueEnd;
    }

    //effect: return the start time on Wednesday of the course.
    public int getWedStart() {
        return wedStart;
    }

    //effect: return the end time on Wednesday of the course.
    public int getWedEnd() {
        return wedEnd;
    }

    //effect: return the start time on Thursday of the course.
    public int getThuStart() {
        return thuStart;
    }

    //effect: return the end time on Thursday of the course.
    public int getThuEnd() {
        return thuEnd;
    }

    //effect: return the start time on Friday of the course.
    public int getFriStart() {
        return friStart;
    }

    //effect: return the end time on Friday of the course.
    public int getFriEnd() {
        return friEnd;
    }



}
