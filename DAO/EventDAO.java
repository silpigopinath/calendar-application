package dao;



import java.sql.Connection;

import java.sql.Date;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Time;

import java.util.ArrayList;

import java.util.Calendar;



import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.CalendarController;
import model.Event;
import view.EventList;



public class EventDAO {

	private static String CREATESQL = "create table Event (title CHARACTER VARYING(150) , "+ "location CHARACTER VARYING(150) , description CHARACTER VARYING(400) ," + "startDate Date, startTime Time, endDate date, endTime time) ";

	private static String INSERTSQL = "insert into Event values(?, ? , ?, ?, ?, ?, ?)";

	private static String READDAYSQL = "SELECT *FROM Event WHERE startDate<=? and endDate>=?";

	private static String READMONTHSQL = "SELECT * FROM Event WHERE (startDate BETWEEN ? AND ?) OR (endDate BETWEEN ? AND ?) OR (startDate < ? AND endDate > ?)";
	
	private static String READWEEKSQL =  "SELECT * FROM Event WHERE (startDate BETWEEN ? AND ?) OR (endDate BETWEEN ? AND ?) OR (startDate < ? AND endDate > ?)";
	
	private static String READBYEVENTSQL="SELECT * FROM Event WHERE  title=? and  startDate<=? and endDate>=?";
	
	private static String READBYTIMESQL="SELECT * FROM Event WHERE ((startDate<=?  and endDate>=?)and (startTime<= ? and endTime>= ?) )and title=? ";

	private static String READBYDATETIMESQL="SELECT * FROM Event WHERE  (startDate<= ? and endDate>= ?) and (startTime<= ? and endTime>= ?)";;

	private static String REMOVEEVENTSQL="DELETE FROM  Event WHERE ((startDate<=?  and endDate>=?)and (startTime<= ? and endTime>= ?) )and title=? ";

	
	public static void create() throws Exception {

		Connection con = null;

		PreparedStatement pStmt = null;

		try {

			DbConnector connector = DbConnector.getInstance();

			con = connector.getConnection();

			pStmt = con.prepareStatement(CREATESQL);

			pStmt.executeUpdate();

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			pStmt.close();

			con.close();

		}



	}

	public static void insert(Event evnt) {

		Connection con = null;

		PreparedStatement pStmt = null;

		try {

			DbConnector connector = DbConnector.getInstance();

			con = connector.getConnection();

			pStmt = con.prepareStatement(INSERTSQL);

			

			pStmt.setString(1, evnt.getTitle());

			pStmt.setString(2, evnt.getLocation());

			pStmt.setString(3, evnt.getDescription());



			java.util.Date uStartDate = evnt.getStartDate();

			java.sql.Date sStartDate = new java.sql.Date(uStartDate.getTime());

			pStmt.setDate(4, sStartDate);

			java.util.Date uStartTime = evnt.getStartTime();

			java.sql.Time sStartTime = new java.sql.Time(uStartTime.getTime());

			pStmt.setTime(5, sStartTime);
			

			java.util.Date uEndDate = evnt.getEndDate();

			java.sql.Date sEndDate = new java.sql.Date(uEndDate.getTime());

			pStmt.setDate(6, sEndDate);

			
			java.util.Date uEndTime = evnt.getEndTime();

			java.sql.Time sEndTime = new java.sql.Time(uEndTime.getTime());

			pStmt.setTime(7, sEndTime);

			

			System.out.println("Start Date" + sStartDate);

			System.out.println("End Date" + sEndDate);

			System.out.println("Start Time" + sStartTime);

			System.out.println("End Time" + sEndTime);

			

			pStmt.executeUpdate();

		} catch (Exception ex) {



		}

	}

	public static List<Event> readByDay(java.util.Date date) throws Exception {

		Connection con = null;

		PreparedStatement pStmt = null;

		DbConnector connector = DbConnector.getInstance();

		try {

			con = connector.getConnection();
			java.sql.Date sStartDate = new java.sql.Date(date.getTime());

			pStmt = con.prepareStatement(READDAYSQL);


			pStmt.setDate(1, sStartDate);
			pStmt.setDate(2, sStartDate);
			

			ResultSet rs = pStmt.executeQuery();

			ArrayList<Event> evntList = new ArrayList<>();

			boolean flag = false;

			while (rs.next()) {

				flag = true;

				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),

						rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));

				evntList.add(evnt);

			}

			if (!flag) {

				throw new Exception("No Events for the Day");

			}

			return evntList;



		} catch (Exception e) {

			throw e;

		} finally {

			pStmt.close();

			con.close();



		}

	}

	public static List<Event>  getEvent(String title,java.util.Date date)
{
	Connection con = null;

	PreparedStatement pStmt = null;
	ArrayList<Event>ls=new ArrayList<>();
	DbConnector connector = DbConnector.getInstance();
	try {
		con=connector.getConnection();
		pStmt=con.prepareStatement(READBYEVENTSQL);
		System.out.println(title);
		pStmt.setString(1,title);
		java.sql.Date sStartDate = new java.sql.Date(date.getTime());
		pStmt.setDate(2, sStartDate);
		pStmt.setDate(3, sStartDate);
		ResultSet rs = pStmt.executeQuery();



		boolean flag = false;

		while (rs.next()) {

			flag = true;

		   Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),

					rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));

			ls.add(evnt);

		}
		if(!flag)
		{
			System.out.println(title + date.getDate() + " " + date.getMonth());
			throw new Exception("No such event exists");
		}
		
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return ls;
}

	public static Event getEventByTime(java.util.Date date, java.util.Date time,String title) throws SQLException
{
	Connection con = null;
	Event evnt = null;
	PreparedStatement pStmt = null;
	
	DbConnector connector = DbConnector.getInstance();
	try {
		con=connector.getConnection();
		pStmt=con.prepareStatement(READBYTIMESQL);
		java.sql.Date sStartDate = new java.sql.Date(date.getTime());
		
		pStmt.setDate(1, sStartDate);
		pStmt.setDate(2, sStartDate);
		java.sql.Time sStartTime = new java.sql.Time(time.getTime());
		pStmt.setTime(3, sStartTime);
		pStmt.setTime(4, sStartTime);
		pStmt.setString(5,title);
		
		ResultSet rs = pStmt.executeQuery();



		boolean flag = false;

		if (rs.next()) {

			flag = true;

		   evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3), 

					rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));

			

		}
		if(!flag)
		{
			throw new Exception("No such event exists");
		}
		
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	finally
	{
		pStmt.close();
		con.close();
	}
	return evnt;
}

	public static List<Event> readByDateTime(java.util.Date date, java.util.Date time) throws SQLException
{

	Connection con = null;

	PreparedStatement pStmt = null;
	ArrayList<Event>ls=new ArrayList<>();
	DbConnector connector = DbConnector.getInstance();
	try {
		con=connector.getConnection();
		pStmt=con.prepareStatement(READBYDATETIMESQL);
		java.sql.Date sStartDate = new java.sql.Date(date.getTime());
		pStmt.setDate(1, sStartDate);
		pStmt.setDate(2, sStartDate);
		java.sql.Time sStartTime = new java.sql.Time(time.getTime());
		pStmt.setTime(3, sStartTime);
		pStmt.setTime(4, sStartTime);
		ResultSet rs = pStmt.executeQuery();



		boolean flag = false;

		while (rs.next()) {

			flag = true;

		   Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),

					rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));

			ls.add(evnt);

		}
		if(!flag)
		{
			throw new Exception("No such event exists");                      //////////////throw
		}
		
		
	}
	catch(Exception ex)
	{
//		ex.printStackTrace();         										///////////////catch
	} 
	finally
	{
		pStmt.close();
		con.close();
	}
	return ls;
	
}

	public static  void removeEvent(Event evnt) throws SQLException
{
	Connection con = null;

	PreparedStatement pStmt = null;
	
	DbConnector connector = DbConnector.getInstance();
	try {
		con=connector.getConnection();
		
		pStmt=con.prepareStatement(REMOVEEVENTSQL);
		
		java.sql.Date sStartDate = new java.sql.Date(evnt.getStartDate().getTime());
		pStmt.setDate(1, sStartDate);
		pStmt.setDate(2, sStartDate);
		java.sql.Time sStartTime = new java.sql.Time(evnt.getStartTime().getTime());
		pStmt.setTime(3, sStartTime);
		pStmt.setTime(4, sStartTime);
		pStmt.setString(5, evnt.getTitle());
		pStmt.executeUpdate();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	finally
	{
		pStmt.close();
		con.close();
	}
}

	public static DefaultTableModel readByMonth(java.util.Date date) throws Exception 
	{
		Connection con=null;
		PreparedStatement pStmt=null;
		DbConnector connector=DbConnector.getInstance();
		DefaultTableModel eventTable = new DefaultTableModel();
		eventTable.setColumnCount(2);
		try {
			con=connector.getConnection();
			Calendar cal=Calendar.getInstance();
			
			cal.setTime(date);
			
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			
			pStmt=con.prepareStatement(READMONTHSQL);
			java.sql.Date monthStartDate = new java.sql.Date(date.getTime());
			monthStartDate.setDate(1);
			java.sql.Date monthEndDate = new java.sql.Date(date.getTime());
			
			monthEndDate.setMonth(date.getMonth() + 1);
			monthEndDate.setDate(1);
			monthEndDate.setDate(0);
			
			pStmt.setDate(1, monthStartDate);
			pStmt.setDate(2, monthEndDate);
			pStmt.setDate(3, monthStartDate);
			pStmt.setDate(4, monthEndDate);
			pStmt.setDate(5, monthStartDate);
			pStmt.setDate(6, monthEndDate);
			
			ResultSet rs = pStmt.executeQuery();
			int noEvent = rs.getFetchSize();
			
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Date d=rs.getDate(5);
				Time t=rs.getTime(6);
				cal.setTime(d);
			    cal.setTimeInMillis(t.getTime());
				
			    Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));
			    java.util.Date endDate = evnt.getEndDate();
			    java.util.Date startDate = evnt.getStartDate();
			    java.util.Date monthStartUtilDate = new java.util.Date(monthStartDate.getTime());
			    java.util.Date monthEndUtilDate = new java.util.Date(monthEndDate.getTime());
			    if(monthStartUtilDate.getTime() > startDate.getTime()) {
			    	startDate = monthStartUtilDate;
			    }
			    if(monthEndUtilDate.getTime() < endDate.getTime()) {
			    	endDate = monthEndUtilDate;
			    }
				for (int i = startDate.getDate(); i <= endDate.getDate() ; ++i) {
					Object row[] = {i, evnt};                     
					eventTable.addRow(row);
				}
				
			}
			if(!flag)
			{
				throw new Exception("No Events for the Month");
			}
			return eventTable;
		
		} catch (Exception e)
		{
		
			throw e;
		}
		finally
		{
			try {
				pStmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public static DefaultTableModel readByWeek(java.util.Date date) 
	{
		Connection con=null;
		PreparedStatement pStmt=null;
		DbConnector connector=DbConnector.getInstance();
		DefaultTableModel eventTable = new DefaultTableModel();
		eventTable.setColumnCount(2);
		try {
			con=connector.getConnection();
			Calendar cal=Calendar.getInstance();
			
			cal.setTime(date);
			
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			
			pStmt=con.prepareStatement(READWEEKSQL);
			int weekDay = date.getDay();
			
			java.sql.Date weekStartDate = new java.sql.Date(date.getTime());
			weekStartDate.setDate(weekStartDate.getDate() - weekDay);
			java.sql.Date weekEndDate = new java.sql.Date(date.getTime());
			weekEndDate.setDate(weekEndDate.getDate() + (6 - weekDay));
			
			pStmt.setDate(1, weekStartDate);
			pStmt.setDate(2, weekEndDate);
			pStmt.setDate(3, weekStartDate);
			pStmt.setDate(4, weekEndDate);
			pStmt.setDate(5, weekStartDate);
			pStmt.setDate(6, weekEndDate);
			
			ResultSet rs = pStmt.executeQuery();
			int noEvent = rs.getFetchSize();
			
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Date d=rs.getDate(5);
				Time t=rs.getTime(6);
				cal.setTime(d);
			    cal.setTimeInMillis(t.getTime());
				
			    Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getTime(5), rs.getDate(6), rs.getTime(7));
			    java.util.Date endDate = evnt.getEndDate();
			    java.util.Date startDate = evnt.getStartDate();
			    java.util.Date monthStartUtilDate = new java.util.Date(weekStartDate.getTime());
			    java.util.Date monthEndUtilDate = new java.util.Date(weekEndDate.getTime());
			    if(monthStartUtilDate.getTime() > startDate.getTime()) {
			    	startDate = monthStartUtilDate;
			    }
			    if(monthEndUtilDate.getTime() < endDate.getTime()) {
			    	endDate = monthEndUtilDate;
			    }
			    System.out.println(startDate.getDate());
			    System.out.println(endDate.getDate());
				for (java.util.Date i = startDate; i.compareTo(endDate) <= 0 ; i.setDate(i.getDate() + 1)) {
					Object row[] = {i.getDate(), evnt};                     
					eventTable.addRow(row);
				}
				
			}
			if(!flag)
			{
				throw new Exception("No Events for the Week");
			}
			return eventTable;
		
		} catch (Exception e)
		{
		
			e.printStackTrace();
		}
		finally
		{
			try {
				pStmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return eventTable;
		
		
		
	}
	

	
//REMOVE EVENT

	public static void main(String args[]) {

		List<Event> eventList = null;
		try {
			eventList = readByDateTime(new Date(2019 - 1900, 10, 20), new java.util.Date(2019 - 1900, 10, 20, 4, 0));
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(eventList.size());
//		System.out.println("Row : " + row + "Date = " + date);
//		System.out.println(date + " " + month + " ");
		
		
//		System.out.println(evList.getRowCount());
//		for(int i = 0; i<evList.getRowCount(); ++i) {
//			int x = (int)evList.getValueAt(i, 0);
//			System.out.print(evList.getValueAt(i, 0));
//			System.out.print(x);
//			Event e = (Event)evList.getValueAt(i, 1);
//			System.out.println("   " + e.getTitle());
//		}

//		try {
//
//
//
//			// DbConnector.getInstance();
//
////			create();
//
////			/cal.set(2019,9,13,10,30);
//
//			 Time start = Time.valueOf("4:00:00");
//
//			 long l = start.getTime();
////
//			 Time end=Time.valueOf("5:00:00");
//			 long l1 = end.getTime();
////			 insert(new Event("Seminar","Conference Hall","Seminar on the topic women em-Powerment ",new Date(2019-1900,11,11),new Date(l),new Date(2019-1900,11,15),new Date(l1)));
//			 removeEvent(new Event("Seminar","Conference Hall","Seminar on the topic women em-Powerment ",new Date(2019-1900,11,11),new Date(l),new Date(2019-1900,11,15),new Date(l1)));
//			// System.out.println(cal);
//
//			// cal.set(Calendar.DAY_OF_MONTH, 13);
////
//			Date d=new Date(2019-1900,11,14);
//
////			List<Event> ls=
////			Event evnt=getEventByTime(d,new Date(l),"Seminar");
////			System.out.println("LOC"+evnt.getLocation()+"DES"+evnt.getDescription());
////		    for(int i=0;i<ls.size();i++)
////
////			 {
////
////			System.out.println(ls.get(i).getStartDate()+" "+ls.get(i).getStartTime()+" "+ls.get(i).getTitle());
////
////			 }
//
//			// System.out.println("No results found");
//
//			// Calendar cal=Calendar.getInstance();
//
//			// System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
//
//		} catch (Exception e) {
//
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//
//		}

	}

}