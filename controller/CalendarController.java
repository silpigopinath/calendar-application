package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.EventDAO;
import model.Event;

public class CalendarController {


	public static void addEvent(Event evnt) {
		EventDAO.insert(evnt);

	}

	public static List<Event> getEvent(String title, Date date) {
		List<Event> ls = EventDAO.getEvent(title, date);
		return ls;

	}

	public static List<Event> readDay(Date d) throws Exception {
		List<Event> ls = EventDAO.readByDay(d);
		return ls;
	}

	public static DefaultTableModel readByMonth(java.util.Date date) throws Exception {
		DefaultTableModel eventTable = EventDAO.readByMonth(date);
		return eventTable;
	}
	
	public static DefaultTableModel readByWeek(java.util.Date date) throws Exception {
		DefaultTableModel eventTable = EventDAO.readByWeek(date);
		return eventTable;
	}

	public static List<Event> readByDateTime(Date date, Date time) throws Exception {
		List<Event> ls = EventDAO.readByDateTime(date, time);
		return ls;
	}

	public static Event getEventByTime(Date date, Date time, String title) throws SQLException {
		Event evnt = EventDAO.getEventByTime(date, time, title);
		return evnt;
	}

	public static void removeEvent(Event evnt) throws SQLException {
		EventDAO.removeEvent(evnt);
	}

}