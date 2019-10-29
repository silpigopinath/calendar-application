package model;



import java.util.Date;

public class Event {
	public Event(String eventId, String location, String title, String description, Date  date, Date time) {
		super();
		this.eventId = eventId;
		this.location = location;
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
	}
	String eventId,location,title,description;
    Date date,time;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	

}