package model;







import java.util.Date;



public class Event {

	

	String location,title,description;

    Date startDate,startTime, endDate, endTime;

	

	public Event(String title, String location,  String description, Date  startDate, Date startTime, Date endDate, Date endTime) {

		super();


		this.location = location;

		this.title = title;

		this.description = description;

		this.startDate = startDate;

		this.startTime = startTime;

		this.endDate = endDate;

		this.endTime = endTime;

	}

	

	public Date getStartDate() {

		return startDate;

	}


	public Date getStartTime() {

		return startTime;

	}

	public Date getEndDate() {

		return endDate;

	}


	public Date getEndTime() {

		return endTime;

	}



	public String getLocation() {

		return location;

	}



	public String getTitle() {

		return title;

	}


	public String getDescription() {

		return description;

	}


}