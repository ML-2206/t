package ui.gui;


import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// provide a graphic user interface
class UI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/workroom.json";
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private TimetableGetter getter = new TimetableGetter();
    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //Effect: create a new control panel
    public UI() {
        workRoom = new WorkRoom("Alex's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false,
                false, false, false);
        controlPanel.setLayout(new BorderLayout());
        setContentPane(desktop);
        setTitle("CPSC 210: Timetable Generator");
        setSize(WIDTH, HEIGHT);
        addButtonPanel();
        addImage();
        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        centreOnScreen();
        setVisible(true);

    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            EventLog el = EventLog.getInstance();
            for (Event next : el) {
                System.out.println(next.toString() + "\n");
            }
            System.exit(0);
        }
    }

    //Modify: this.
    //Effect: add button to the control panel
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));
        buttonPanel.add(new JButton(new AddCourseAction()));
        buttonPanel.add(new JButton(new ClearCoursesAction()));
        buttonPanel.add(new JButton(new GetTimetableAction()));
        buttonPanel.add(new JButton(new PrintCoursesAction()));
        buttonPanel.add(new JButton(new GetCreditSumAction()));
        buttonPanel.add(new JButton(new GetNumberOfCourseAction()));
        buttonPanel.add(new JButton(new GetAllAddedCourseDetails()));
        buttonPanel.add(new JButton(new SaveToFileAction()));
        buttonPanel.add(new JButton(new SaveFileAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton(new PrintSavedTimetableAction()));
        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    //Modify: this.
    //Effect: add image to the control panel
    private void addImage() {
        JPanel imagePanel = new JPanel();
        imagePanel.add(new JLabel(new ImageIcon("Image/download.png")));
        controlPanel.add(imagePanel, BorderLayout.EAST);
    }

    //Modify: this
    //Effect: set the height and width of the control panel
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    // Store the implementation of the add course button
    private class AddCourseAction extends AbstractAction {

        //Effect: construct a button with the name below.
        AddCourseAction() {
            super("Add Course");
        }

        //Modify: this
        //Effect: Add courses.
        @Override
        public void actionPerformed(ActionEvent evt) {
            getter.addCourse(getInformation());
        }

        //Effect: return a Course with all parameter needed.
        private Course getInformation() {
            return new Course(getCourseName(), getCourseCredit(), getMonStart(), getMonEnd(),
                    getTueStart(), getTueEnd(), getWedStart(), getWedEnd(), getThuStart(),
                    getThuEnd(), getFriStart(), getFriEnd());
        }

        //Effect: Return the name of the course.
        private String getCourseName() {

            return JOptionPane.showInputDialog(null,
                    "Course name:",
                    "Enter Course name",
                    JOptionPane.QUESTION_MESSAGE);
        }

        //Effect: Return the number of credit of the course.
        private int getCourseCredit() {
            String s = JOptionPane.showInputDialog(null,
                    "Course credit:",
                    "Enter number of credit of the course:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the starting time on monday of the course.
        private int getMonStart() {
            String s = JOptionPane.showInputDialog(null,
                    "Monday Start time:",
                    "Enter Monday Start time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the ending time on monday of the course.
        private int getMonEnd() {
            String s = JOptionPane.showInputDialog(null,
                    "Monday end time:",
                    "Enter Monday end time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the starting time on tuesday of the course.
        private int getTueStart() {
            String s = JOptionPane.showInputDialog(null,
                    "Tuesday Start time:",
                    "Enter Tuesday Start time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the ending time on monday of the course.
        private int getTueEnd() {
            String s = JOptionPane.showInputDialog(null,
                    "Tuesday end time:",
                    "Enter Tuesday end time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the starting time on wedensday of the course.
        private int getWedStart() {
            String s = JOptionPane.showInputDialog(null,
                    "Wednesday Start time:",
                    "Enter Wednesday Start time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the ending time on wedensday of the course.
        private int getWedEnd() {
            String s = JOptionPane.showInputDialog(null,
                    "Wednesday end time:",
                    "Enter Wednesday end time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the starting time on thursday of the course.
        private int getThuStart() {
            String s = JOptionPane.showInputDialog(null,
                    "Thursday Start time:",
                    "Enter Thursday Start time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the ending time on thurday of the course.
        private int getThuEnd() {
            String s = JOptionPane.showInputDialog(null,
                    "Thursday end time:",
                    "Enter Thursday end time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the starting time on friday of the course.
        private int getFriStart() {
            String s = JOptionPane.showInputDialog(null,
                    "Friday start time:",
                    "Enter Friday start time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

        //Effect: Return the ending time on friday of the course.
        private int getFriEnd() {
            String s = JOptionPane.showInputDialog(null,
                    "Friday end time:",
                    "Enter Friday end time:",
                    JOptionPane.QUESTION_MESSAGE);
            return Integer.parseInt(s);
        }

    }

    //Store the implementation of print course.
    private class PrintCoursesAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        PrintCoursesAction() {
            super("Print Course");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: print the name of the courses
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);

            sp.printName(getter.showAllCourse());
        }
    }

    //Store the implementation of clear course.
    private class ClearCoursesAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        ClearCoursesAction() {
            super("Clear All Added Courses");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: reset getter.
        @Override
        public void actionPerformed(ActionEvent evt) {
            getter = new TimetableGetter();
        }
    }

    //Store the implementation of get credit sum .
    private class GetCreditSumAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        GetCreditSumAction() {
            super("Get Credit Sum");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: reset getter.
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);
            sp.printCreditSum(getter.sumOfCredit());
        }
    }

    //Store the implementation of get number of course.
    private class GetNumberOfCourseAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        GetNumberOfCourseAction() {
            super("Get Number Of Courses Added");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: get the number of course added to getter.
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);
            sp.printCreditSum(getter.numberOfCourse());
        }
    }

    //Store the implementation of save to file.
    private class SaveToFileAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        SaveToFileAction() {
            super("Save To File");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: save to the file..
        @Override
        public void actionPerformed(ActionEvent evt) {
            Timetable timetable = new Timetable(getter.getResult());
            int credit = getter.sumOfCredit();
            ArrayList<String> temp = getter.showAllCourse();
            ArrayList<String> names = new ArrayList<>();
            for (String s : temp) {
                if (!names.contains(s)) {
                    names.add(s);
                }
            }
            String name = JOptionPane.showInputDialog(null,
                    "Please enter name of this timetable: ",
                    "Please enter name of this timetable: ",
                    JOptionPane.QUESTION_MESSAGE);
            workRoom.addTimetable(new Candidates(name, timetable, credit, names));
        }
    }

    //Store the implementation of save file .
    private class SaveFileAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        SaveFileAction() {
            super("Save File");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: save the file.
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(workRoom);
                jsonWriter.close();
                System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    //Store the implementation of print saved timetable.
    private class PrintSavedTimetableAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        PrintSavedTimetableAction() {
            super("Print Saved Timetable");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: print the teimtable saved in the file.
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);

            sp.printSaved(workRoom.getCandidates());
        }
    }

    //Store the implementation of load .
    private class LoadAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        LoadAction() {
            super("Load");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: laod the file .
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                workRoom = jsonReader.read();
                System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    //Store the implementation of get timetable .
    private class GetTimetableAction extends AbstractAction {

        //Effect: COnstruct a button as the name.
        GetTimetableAction() {
            super("Get Timetable");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: get th etimetable with course addedd to getter.
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);
            sp.printTimetable(getter.getResult());
        }
    }

    //Store the implementation of get detailed course.
    private class GetAllAddedCourseDetails extends AbstractAction {

        //Effect: COnstruct a button as the name.
        GetAllAddedCourseDetails() {
            super("Get Details of Courses Added");
        }

        //Require: Generate timetable is clicked.
        //Modify: this
        //Effect: get details of the courses added to getter.
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(UI.this);
            desktop.add((ScreenPrinter) sp);
            sp.printCourses(getter.getAllCourse());
        }
    }

    //Set the function needed to use the mouse with the gui.
    private class DesktopFocusAction extends MouseAdapter {

        //Modify: this
        //Effect: allwo the use of the mouse.
        @Override
        public void mouseClicked(MouseEvent e) {
            UI.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        new UI();
    }
}
