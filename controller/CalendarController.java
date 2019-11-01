package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.EventDAO;
import model.Event;

public class CalendarController {
	private Event evnt;
	
	CalendarController(Event evnt)
	{
		this.evnt=evnt;
	}
	public static void addEvent(Event evnt)
	{
		EventDAO.insert(evnt);
		
	}
	public static List<Event> getEvent(String title,Date date)
	{
		List<Event> ls=EventDAO.getEvent(title, date);
		return ls;
		
	}
	public static List<Event> readDay(Date d) throws Exception
	{
		List<Event> ls=EventDAO.readByDay(d);
		return ls;
	}
	public static List<Event> readByDateTime(Date date,Date time) throws Exception
	{
		List<Event> ls=EventDAO.readByDateTime(date,time);
		return ls;
	}
	public static Event  getEventByTime(Date date, Date time, String title) throws SQLException
	{
		Event evnt=EventDAO.getEventByTime(date, time, title);
		return evnt;
	}
	
	public static void removeEvent(Event evnt) throws SQLException
	{
		EventDAO.removeEvent(evnt);
	}

}
