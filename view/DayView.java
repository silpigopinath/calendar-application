package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.CalendarController;
import model.Event;

import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DayView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public DayView(GregorianCalendar calendar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(27, 2, 0, 0);
		contentPane.setLayout(grid);

		////////////////////////////////
		String[] week = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String[] timeInterval = { "12 AM", "01 AM", "02 AM", "03 AM", "04 AM", "05 AM", "06 AM", "07 AM", "08 AM",
				"09 AM", "10 AM", "11 AM", "12 PM", "01 PM", "02 PM", "03 PM", "04 PM", "05 PM", "06 PM", "07 PM",
				"08 PM", "09 PM", "10 PM", "11 PM" };

		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
		boolean todayFlag = false;
		////////////////////////////////
		JLabel dateLabel = new JLabel(Integer.toString(todayDate));
		if (Calendar.getInstance().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
				&& Calendar.getInstance().get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& Calendar.getInstance().get(Calendar.DATE) == todayDate) {
			Border border = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.MAGENTA);
			dateLabel.setBorder(border);
			 todayFlag = true;
		} else {
			Border border = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.YELLOW);
			dateLabel.setBorder(border);
		}
		contentPane.add(dateLabel);

		JLabel blankLabel1 = new JLabel();
		contentPane.add(blankLabel1);

		JLabel dayLabel = new JLabel(week[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
		if(todayFlag) {
			Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.MAGENTA);
			dayLabel.setBorder(border);
		}else {
		Border Dayborder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.YELLOW);
		dayLabel.setBorder(Dayborder);}
		contentPane.add(dayLabel);

		List<Event> eventList = null;

		try {
			eventList = CalendarController.readDay(calendar.getTime());
		} catch (Exception e) {
			if (e.getMessage().equals("No Events for the Day")) {
				// KEEP QUIET
			}
		}
		if (eventList != null) {
			if (eventList.size() > 0) {
				JButton viewAllTasksButton = new JButton("View All Tasks");
				viewAllTasksButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							List<Event> eventList = CalendarController.readDay(calendar.getTime());
							EventList.getEventList(eventList);
						} catch (Exception e1) {
							// e1.printStackTrace();
						}
					}
				});
				Border viewBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY);
				viewAllTasksButton.setBorder(viewBorder);
				contentPane.add(viewAllTasksButton);
			}
		} else {
			JLabel blankLabel = new JLabel();
			contentPane.add(blankLabel);
		}
		JLabel blankLabel2 = new JLabel();
		contentPane.add(blankLabel2);
		JLabel blankLabel3 = new JLabel("");
		contentPane.add(blankLabel3);

		JButton eventButtonGrid[] = new JButton[24];

		for (int i = 0; i < 48; ++i) {
			///////////////////////////////////

			String interval = timeInterval[i / 2];
			int startTimeInt;

			if (interval.equals("12 AM")) {
				startTimeInt = 0;
			} else if (interval.equals("12 PM")) {
				startTimeInt = 12;
			} else if (interval.charAt(3) == "A".charAt(0)) {
				startTimeInt = Integer.parseInt(interval.substring(0, 2));
			} else {
				startTimeInt = Integer.parseInt(interval.substring(0, 2));
				startTimeInt = startTimeInt + 12;
			}
			Date currentTime = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DATE), startTimeInt, 0);

			String eventTitle = null;
			if (eventList != null) {
				for (int x = 0; x < eventList.size(); ++x) {
					Event event = eventList.get(x);
					int startHour = event.getStartTime().getHours(), endHour = event.getEndTime().getHours(),
							currentHour = currentTime.getHours();
					if (startHour <= currentHour && endHour > currentHour) {
						if (eventTitle != null) {
							eventTitle = "...";
						} else {
							eventTitle = event.getTitle();
						}

					}
				}
			}

			if (i % 2 == 0) {
				JLabel timeLabel = new JLabel(timeInterval[i / 2]);
				Border Timeborder = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
				timeLabel.setBorder(Timeborder);
				contentPane.add(timeLabel);
			} else if (null == eventTitle) {
				JLabel blankLabel = new JLabel("");
				contentPane.add(blankLabel);

			} else {
				eventButtonGrid[i / 2] = new JButton(eventTitle);
				eventButtonGrid[i / 2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int row = 0; row < 24; row++) {

							if (eventButtonGrid[row] != null && e.getSource() == eventButtonGrid[row]) {
								int hour = row;
								Date time = new Date(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),
										calendar.get(calendar.DATE), hour, 0);
								if ("...".equals(eventButtonGrid[row].getText())) {
									try {
										List<Event> eventList = CalendarController.readByDateTime(calendar.getTime(),
												time);
										EventList.getEventList(eventList);
									} catch (Exception e1) {
										// e1.printStackTrace();
									}
								} else {
									try {
										Event event = CalendarController.getEventByTime(calendar.getTime(), time,
												eventButtonGrid[row].getText()); 

										EventDetails.viewEventDetails(event);
									} catch (Exception e1) {
										// e1.printStackTrace();
									}
								}

							}

						}
					}
				});
				contentPane.add(eventButtonGrid[i / 2]);
			}

			///////////////////////////////////
		}
	}

	public JPanel getDayView() {
		return contentPane;
	}
}
