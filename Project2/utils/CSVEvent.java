package Project_2.utils;

import java.util.ArrayList;

/**
 * You should not modify this class in any way. It exists for you to be able to
 * read in the data from the CSV files. You may uncomment lines of code if you
 * like, but it will likely make the program slower.
 *
 * However, you should make use of the public methods available, including: -
 * getBookmarkCount() - isBookmarkEvent() - getTabCount() - isTabEvent()
 *
 * @author Mark Hancock
 *
 */
public class CSVEvent {
    private int userID;
    private String event;
    private int count;

    public CSVEvent(ArrayList<String> csvLine) {
	this.userID = Integer.parseInt(csvLine.get(0));
	this.event = csvLine.get(1);
	this.count = Integer.parseInt(csvLine.get(2));
    }

    public int getUserID() {
	return userID;
    }

    public String getEvent() {
	return event;
    }

    public int getCount() {
	return count;
    }

    public boolean isBookmarkEvent() {
	return event.equals("bookmark");
    }

    public boolean isTabEvent() {
	return event.equals("tab");
    }
}
