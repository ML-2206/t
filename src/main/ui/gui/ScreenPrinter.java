package ui.gui;

import model.Candidates;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a screen printer for printing stuffs to screen.
 */
public class ScreenPrinter extends JInternalFrame implements CourseNamePrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JTextArea area;

    //EFFECT: Construct a screen printer.
    public ScreenPrinter(Component parent) {
        super("Result", false, true, false, false);
        area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    //Modify: This
    //Effect: Print the name in the array list in a text box.
    @Override
    public void printName(ArrayList<String> name) {
        for (String n : name) {
            area.setText(area.getText() + n + "\n\n\n\n\n\n\n\n");
        }

        repaint();
    }

    //Modify: This
    //Effect: Print the integer in a text box.
    public void printCreditSum(int sum) {
        area.setText(area.getText() + sum + "\n\n");
        repaint();
    }

    //Modify: This
    //Effect: Print the string in the array list of array list in a text box.
    public void printTimetable(ArrayList<ArrayList<String>> timetable) {
        area.setText(area.getText() + timetable + "\n\n");
        repaint();
    }

    //Modify: This
    //Effect: Print the information in the list in a text box.
    public void printSaved(List<Candidates> c) {
        for (Candidates t : c) {
            area.setText(area.getText() + "The code is: " + t.getCode() + "\n");
            area.setText(area.getText() + "The timetable is: " + t.getTimetable().getTimetable() + "\n");
            area.setText(area.getText() + "The sum of credit of this timetable is: " + t.getCredit() + "\n");
            area.setText(area.getText() + "The courses used to get this timetable is: " + t.getNames() + "\n");
        }
        repaint();
    }

    //Modify: This
    //Effect: Print the information in the list in a text box.
    public void printCourses(ArrayList<Course> courses) {
        for (Course c : courses) {
            area.setText(area.getText() + "The name is: " + c.getName() + "\n");
            area.setText(area.getText() + "The number of credit is: " + c.getCredit() + "\n");
            area.setText(area.getText() + "The start time on Monday is: " + c.getMonStart() + "\n");
            area.setText(area.getText() + "The end time on Monday is: " + c.getMonEnd() + "\n");
            area.setText(area.getText() + "The start time on Tuesday is: " + c.getTueStart() + "\n");
            area.setText(area.getText() + "The end time on Tuesday is: " + c.getTueEnd() + "\n");
            area.setText(area.getText() + "The start time on Wednesday is: " + c.getWedStart() + "\n");
            area.setText(area.getText() + "The end time on Wednesday is: " + c.getWedEnd() + "\n");
            area.setText(area.getText() + "The start time on Thursday is: " + c.getThuStart() + "\n");
            area.setText(area.getText() + "The end time on Thursday is: " + c.getThuEnd() + "\n");
            area.setText(area.getText() + "The start time on Friday is: " + c.getFriStart() + "\n");
            area.setText(area.getText() + "The end time on Friday is: " + c.getFriEnd() + "\n");
        }
        repaint();
    }


    //Modify: This
    //Effect: Set the position of the text box.
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }
}
