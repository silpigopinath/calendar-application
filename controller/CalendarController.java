package controller;

import java.util.Date;
import java.util.List;

import dao.EventDAO;
import model.Event;

public class CalendarController {
//	private Event evnt;
	
	CalendarController()
	{
//		this.evnt=evnt;
	}
	public static void addEvent(Event evnt)
	{
		EventDAO.insert(evnt);
		
	}
	public static List<Event> readMonth(Date d)
	{
		List<Event> ls=EventDAO.readByMonth(d);
		return ls;
		
	}
	public static List<Event> readDay(Date d) throws Exception
	{
		List<Event> ls=EventDAO.readByDay(d);
		return ls;
	}
	public static List<Event> readWeek(Date d) throws Exception
	{
		List<Event> ls=EventDAO.readByWeek(d);
		return ls;
	}

}