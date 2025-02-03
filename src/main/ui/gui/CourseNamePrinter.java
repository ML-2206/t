package ui.gui;

import model.Course;
import model.TimetableGetter;

import java.util.ArrayList;

/**
 * Defines behaviours that ScreenPrinter must support.
 */
public interface CourseNamePrinter {

    void printName(ArrayList<String> name);
}
