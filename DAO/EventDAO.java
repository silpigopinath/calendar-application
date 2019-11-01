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



import model.Event;



public class EventDAO {

	private static String CREATESQL = "create table Event (title CHARACTER VARYING(150) , "+ "location CHARACTER VARYING(150) , description CHARACTER VARYING(400) ," + "startDate Date, startTime Time, endDate date, endTime time) ";

	private static String INSERTSQL = "insert into Event values(?, ? , ?, ?, ?, ?, ?)";



	

	private static String READDAYSQL = "SELECT *FROM Event WHERE startDate<=? and endDate>=?";

	
	
	private static String READBYEVENTSQL="SELECT * FROM Event WHERE  title=? and  startDate<=? and endDate>=?";
	private static String READBYTIMESQL="SELECT * FROM Event WHERE ((startDate<=?  and endDate>=?)and (startTime<= ? and endTime>= ?) )and title=? ";

	private static String READBYDATETIMESQL="SELECT * FROM Event WHERE  (startDate<= ? and endDate>= ?) and (startTime<= ? and startTime>= ?)";

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


//REMOVE EVENT

	public static void main(String args[]) {

		try {



			// DbConnector.getInstance();

//			create();

//			/cal.set(2019,9,13,10,30);

			 Time start = Time.valueOf("4:00:00");

			 long l = start.getTime();
//
			 Time end=Time.valueOf("5:00:00");
			 long l1 = end.getTime();
//			 insert(new Event("Seminar","Conference Hall","Seminar on the topic women em-Powerment ",new Date(2019-1900,11,11),new Date(l),new Date(2019-1900,11,15),new Date(l1)));
			 removeEvent(new Event("Seminar","Conference Hall","Seminar on the topic women em-Powerment ",new Date(2019-1900,11,11),new Date(l),new Date(2019-1900,11,15),new Date(l1)));
			// System.out.println(cal);

			// cal.set(Calendar.DAY_OF_MONTH, 13);
//
			Date d=new Date(2019-1900,11,14);

//			List<Event> ls=
//			Event evnt=getEventByTime(d,new Date(l),"Seminar");
//			System.out.println("LOC"+evnt.getLocation()+"DES"+evnt.getDescription());
//		    for(int i=0;i<ls.size();i++)
//
//			 {
//
//			System.out.println(ls.get(i).getStartDate()+" "+ls.get(i).getStartTime()+" "+ls.get(i).getTitle());
//
//			 }

			// System.out.println("No results found");

			// Calendar cal=Calendar.getInstance();

			// System.out.println(cal.get(Calendar.WEEK_OF_YEAR));

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}