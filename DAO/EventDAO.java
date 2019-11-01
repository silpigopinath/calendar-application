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
			+ "location CHARACTER VARYING(150) , description CHARACTER VARYING(400) ," + "startDate Date, startTime Time, endDate date, endTime time) ";
	private static String INSERTSQL = "insert into Event values(?, ? , ?, ?, ?, ?, ?, ?)";

	private static String READSQL = "SELECT *FROM Event WHERE EXTRACT (MONTH FROM date)=?";
	private static String READDAYSQL = "SELECT *FROM Event WHERE EXTRACT (DAY FROM date)=?";
	private static String READWEEKSQL = "SELECT *FROM Event WHERE EXTRACT (WEEK FROM date)=?";

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
			pStmt.setString(1, evnt.getEventId());
			pStmt.setString(2, evnt.getLocation());
			pStmt.setString(3, evnt.getTitle());
			pStmt.setString(4, evnt.getDescription());

			java.util.Date uStartDate = evnt.getStartDate();
			java.sql.Date sStartDate = new java.sql.Date(uStartDate.getTime());
			pStmt.setDate(5, sStartDate);
			
			java.util.Date uEndDate = evnt.getEndDate();
			java.sql.Date sEndDate = new java.sql.Date(uEndDate.getTime());
			pStmt.setDate(7, sEndDate);
			
			java.util.Date uStartTime = evnt.getStartTime();
			java.sql.Time sStartTime = new java.sql.Time(uStartTime.getTime());
			pStmt.setTime(6, sStartTime);
			
			java.util.Date uEndTime = evnt.getEndTime();
			java.sql.Time sEndTime = new java.sql.Time(uEndTime.getTime());
			pStmt.setTime(8, sEndTime);
			
			System.out.println("Start Date" + sStartDate);
			System.out.println("End Date" + sEndDate);
			System.out.println("Start Time" + sStartTime);
			System.out.println("End Time" + sEndTime);
			
			pStmt.executeUpdate();
		} catch (Exception ex) {

		}
	}

	public static List<Event> readByMonth(java.util.Date date) {
		Connection con = null;
		PreparedStatement pStmt = null;
		DbConnector connector = DbConnector.getInstance();
		ArrayList<Event> evntList = new ArrayList<>();
		try {
			con = connector.getConnection();
			Calendar cal = Calendar.getInstance();

			cal.setTime(date);

			int month = cal.get(Calendar.MONTH);

			pStmt = con.prepareStatement(READSQL);
			pStmt.setInt(1, month);

			ResultSet rs = pStmt.executeQuery();

			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Date d = rs.getDate(5);
				Time t = rs.getTime(6);
				cal.setTime(d);
				cal.setTimeInMillis(t.getTime());

				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getTime(6), rs.getDate(7), rs.getTime(8));
				evntList.add(evnt);
			}
			if (!flag) {
				throw new Exception("No Events for the Month");
			}
			return evntList;

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
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

	public static List<Event> readByDay(java.util.Date date) throws Exception {
		Connection con = null;
		PreparedStatement pStmt = null;
		DbConnector connector = DbConnector.getInstance();
		try {
			con = connector.getConnection();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			pStmt = con.prepareStatement(READDAYSQL);
			pStmt.setInt(1, day);
			ResultSet rs = pStmt.executeQuery();
			ArrayList<Event> evntList = new ArrayList<>();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getTime(6), rs.getDate(5), rs.getTime(6));
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

	public static List<Event> readByWeek(java.util.Date date) {
		Connection con = null;
		PreparedStatement pStmt = null;
		DbConnector connector = DbConnector.getInstance();
		ArrayList<Event> evntList = new ArrayList<>();
		try {
			con = connector.getConnection();
			Calendar cal = Calendar.getInstance();

			cal.setTime(date);

			int week = cal.get(Calendar.WEEK_OF_YEAR);
			pStmt = con.prepareStatement(READWEEKSQL);
			pStmt.setInt(1, week);

			ResultSet rs = pStmt.executeQuery();

			boolean flag = false;
			while (rs.next()) {
				flag = true;
				Date d = rs.getDate(5);
				Time t = rs.getTime(6);
				cal.setTime(d);
				cal.setTimeInMillis(t.getTime());

				Event evnt = new Event(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getTime(6), rs.getDate(5), rs.getTime(6));
				evntList.add(evnt);
			}

			if (!flag) {
				throw new Exception("No Events for the week");
			}
			return evntList;

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
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

//	(DATE, TITLE) EVENT
//	(DATE) LIST<EVENT>
// (DATE, TIME, TITLE) EVENT
//(DATE, TIME) LIST<EVENT>
//REMOVE EVENT
	public static void main(String args[]) {
		try {

			// DbConnector.getInstance();
//			create();
			// cal.set(2019,9,13,10,30);
			// Time t = Time.valueOf("2:00:00");
			// long l = t.getTime();
			// insert(new Event("51","blood donation","ground floor","Discussion of progress
			// of work",new Date(2019-1900,11,15),new Date(l)));
			// System.out.println(cal);
			// cal.set(Calendar.DAY_OF_MONTH, 13);
			// Date d=new Date(2019-1900,9,28);
			// List<Event> ls=readByWeek(d);
			// for(int i=0;i<ls.size();i++)
			// {
			// System.out.println(ls.get(i).getDate()+" "+ls.get(i).getTime()+"
			// "+ls.get(i).getTitle());
			// }
			// System.out.println("No results found");
			// Calendar cal=Calendar.getInstance();
			// System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}