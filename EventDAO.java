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
	private static String CREATESQL = "create table Event (eventId CHARACTER VARYING(150) PRIMARY KEY NOT NULL, title CHARACTER VARYING(150) , "
			+ "location CHARACTER VARYING(150) , description CHARACTER VARYING(400) ,"
			+ "date Date, time Time) ";
	private static String INSERTSQL = "insert into Event values(?, ? , ?, ?, ?, ?)";
	
	private static String READSQL = "SELECT *FROM Event WHERE EXTRACT (MONTH FROM date)=?";
	private static String READDAYSQL="SELECT *FROM Event WHERE EXTRACT (DAY FROM date)=?";
	
	
	public static void create() throws Exception
	{
		 Connection con=null;
		PreparedStatement pStmt=null;
		try
		{
			DbConnector connector=DbConnector.getInstance();
			con=connector.getConnection();
			pStmt=con.prepareStatement(CREATESQL);
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
	public static void insert(Event evnt)
	{
		Connection con=null;
		PreparedStatement pStmt=null;
		try {
			DbConnector connector=DbConnector.getInstance();
			con=connector.getConnection();
			pStmt=con.prepareStatement(INSERTSQL);
			pStmt.setString(1, evnt.getEventId());
			pStmt.setString(2, evnt.getLocation());
			pStmt.setString(3, evnt.getTitle());
			pStmt.setString(4, evnt.getDescription());
			
			java.util.Date uDate = evnt.getDate();           
			java.sql.Date sDate = new java.sql.Date(uDate.getTime());
			pStmt.setDate(5,sDate );
			java.util.Date uTime = evnt.getTime();
			System.out.println("Date"+sDate);
			java.sql.Time sTime = new java.sql.Time(uTime.getTime());
			System.out.println("Time"+sTime);
			pStmt.setTime(6,sTime);
			pStmt.executeUpdate();
		}
		catch(Exception ex)
		{
			
		}
	}
	public static List <Event> readByMonth(Integer date) 
	{
		Connection con=null;
		PreparedStatement pStmt=null;
		DbConnector connector=DbConnector.getInstance();
		ArrayList<Event> evntList = new ArrayList<>();
		try {
			con=connector.getConnection();
			Calendar cal=Calendar.getInstance();

			pStmt=con.prepareStatement(READSQL);
			pStmt.setInt(1, date);

			ResultSet rs = pStmt.executeQuery();
			
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Date d=rs.getDate(5);
				Time t=rs.getTime(6);
				cal.setTime(d);
			    cal.setTimeInMillis(t.getTime());
				
				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5),rs.getTime(6));
				evntList.add(evnt);
			}
			return evntList;
		
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
		return evntList;
		
		
		
	}
	public static List <Event> readByDay (Integer date) throws Exception
	{
		Connection con=null;
		PreparedStatement pStmt=null;
		DbConnector connector=DbConnector.getInstance();
		try {
			con=connector.getConnection();
			
			
			pStmt=con.prepareStatement(READDAYSQL);
			pStmt.setInt(1,date);
			ResultSet rs = pStmt.executeQuery();
			ArrayList<Event> evntList = new ArrayList<>();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getTime(6));
				evntList.add(evnt);
			}
			return evntList;
			
		} catch (Exception e)
		{
			throw e;
		}
		finally
		{
			pStmt.close();
			con.close();
			
		}
	}
public static void main(String args[])
{
	try {

//		 create();
//		 cal.set(2019,9,13,10,30);
//		 Time t = Time.valueOf("2:00:00");
//		 long l = t.getTime();
//		 insert(new Event("51","blood donation","ground floor","Discussion of progress of work",new Date(2019-1900,11,15),new Date(l)));
//		System.out.println(cal);
//		cal.set(Calendar.DAY_OF_MONTH, 13);
		List<Event> ls=readByMonth(10);
		for(int i=0;i<ls.size();i++)
		{
			System.out.println(ls.get(i).getDate()+" "+ls.get(i).getTime()+" "+ls.get(i).getTitle());
		}
//		System.out.println("No results found");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}