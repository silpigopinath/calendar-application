package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CalendarController;
import dao.EventDAO;
import model.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.GridBagLayout;

public class WeekView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public WeekView(GregorianCalendar calendar) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(27, 8, 0, 0);
		contentPane.setLayout(grid);

		JButton[] viewAllEventsButtonGrid = new JButton[7];

		////////////////////////////////
		String[] week = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String[] timeInterval = { "12 AM", "01 AM", "02 AM", "03 AM", "04 AM", "05 AM", "06 AM", "07 AM", "08 AM",
				"09 AM", "10 AM", "11 AM", "12 PM", "01 PM", "02 PM", "03 PM", "04 PM", "05 PM", "06 PM", "07 PM",
				"08 PM", "09 PM", "10 PM", "11 PM" };

		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
		int todayDay = calendar.get(Calendar.DAY_OF_WEEK);
		int sundayIndex = todayDate - todayDay + 1;
		int sundayDate = 0;
		if (sundayIndex > 0) {
			sundayDate = sundayIndex;
		}
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		int date = calendar.get(Calendar.DATE);
		int lastDay = numberOfDaysInMonth(month, year);
		if (sundayIndex <= 0) {

			sundayDate = lastDay + sundayIndex;
		}
		////////////////////////////////
		JLabel blankDay1 = new JLabel("");
		contentPane.add(blankDay1); 

		JLabel[] dateLabelGrid = new JLabel[7];
		int todayFlag = -1;
		for (int i = 0; i < 7; ++i) {
			if (i + sundayDate > lastDay) {
				sundayDate = 1 - i;
			}
			dateLabelGrid[i] = new JLabel(Integer.toString(i + sundayDate));

			if (Calendar.getInstance().get(Calendar.YEAR) == year && Calendar.getInstance().get(Calendar.MONTH) == month
					&& Calendar.getInstance().get(Calendar.DATE) == i + sundayDate) {
				Border border = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.MAGENTA);
				dateLabelGrid[i].setBorder(border);
				todayFlag = i;
			} else {
				Border border = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.YELLOW);
				dateLabelGrid[i].setBorder(border);
			}
			contentPane.add(dateLabelGrid[i]); 
		}

		JLabel blankDay2 = new JLabel(""); 
		contentPane.add(blankDay2);

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			if (todayFlag == i) {
				Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.MAGENTA);
				weekDay.setBorder(border);
			} else {
				Border border = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.YELLOW);
				weekDay.setBorder(border);
			}
			contentPane.add(weekDay); 
		}
		JLabel blankDay3 = new JLabel("");
		contentPane.add(blankDay3); 
		DefaultTableModel eventTable = null;
		JButton[][] eventButtonGrid = new JButton[24][7];
		try {
			eventTable = CalendarController.readByWeek(new Date(year - 1900, month, date));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 7; ++i) {

			Date currentDayDate = new Date(year - 1900, month, Integer.parseInt(dateLabelGrid[i].getText()));
			List<Event> eventList = null;
			int eventCountInDay = 0;
			for (int x = 0; x < eventTable.getRowCount(); ++x) {
				if (currentDayDate.getDate() == (int) eventTable.getValueAt(x, 0)) {
					eventCountInDay++;
				}
			}
			if (eventCountInDay > 0) {
				viewAllEventsButtonGrid[i] = new JButton("View All");
				viewAllEventsButtonGrid[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int row = 0; row < 7; row++) {
							if (viewAllEventsButtonGrid[row] == e.getSource()) {

								int date = Integer.parseInt(dateLabelGrid[row].getText());
								try {
									if (date - row < 1 && calendar.get(Calendar.DATE) > 20) {
										List<Event> eventList = CalendarController
												.readDay(new Date(year - 1900, month + 1, date));
										EventList.getEventList(eventList);
									} else {
										List<Event> eventList = CalendarController
												.readDay(new Date(year - 1900, month, date));
										EventList.getEventList(eventList);
									}
								} catch (Exception e1) {
									// e1.printStackTrace();
								}
							
							}

						}

					}
				});
				contentPane.add(viewAllEventsButtonGrid[i]); 
			} else {
				JLabel blankDay = new JLabel("");
				contentPane.add(blankDay);
			}

		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 24; ++i) {
			for (int j = 0; j < 8; j++) {

				if (j == 0) { /////////////// FOR HOURS
					JLabel hourLabel = new JLabel(timeInterval[i]);
					Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
					hourLabel.setBorder(border);
					contentPane.add(hourLabel);
				} else {

					Date currentDayDate = new Date(year - 1900, month,
							Integer.parseInt(dateLabelGrid[j - 1].getText()));

					String interval = timeInterval[i];
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
					Date currentTime = new Date(currentDayDate.getYear(), currentDayDate.getMonth(),
							currentDayDate.getDate(), startTimeInt, 0);
					int eventCount = 0;
					String eventTitle = null;
					Event event = null;
					for (int x = 0; x < eventTable.getRowCount(); ++x) {
						if ((int) eventTable.getValueAt(x, 0) == currentDayDate.getDate()) {
							event = (Event) eventTable.getValueAt(x, 1);
							int startHour = event.getStartTime().getHours();
							int endHour = event.getEndTime().getHours();
							int currentHour = currentTime.getHours();

							if (startHour <= currentHour && endHour > currentHour) {
								eventCount++;
								eventTitle = event.getTitle();
							}
						}
					}
					if (eventCount == 0) {
						JLabel blankDay = new JLabel("");
						Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY);
						blankDay.setBorder(border);
						contentPane.add(blankDay);
					} else if (eventCount >= 1) {
						if (eventCount == 1) {
							eventButtonGrid[i][j - 1] = new JButton(eventTitle);
						} else {
							eventButtonGrid[i][j - 1] = new JButton("...");
						}
						eventButtonGrid[i][j - 1].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int row = 0; row < 24; row++) {
									for (int col = 0; col < 7; col++) {
										if (eventButtonGrid[row][col] == e.getSource()) {
											int date = Integer.parseInt(dateLabelGrid[col].getText());
											if ("...".equals(eventButtonGrid[row][col].getText())) {
												try {
													List<Event> eventList = CalendarController.readByDateTime(
															new Date(year - 1900, month, date),
															new Date(year - 1900, month, date, row, 0));
													EventList.getEventList(eventList);
												} catch (Exception e1) {
													// e1.printStackTrace();
												}
											} else {
												try {
													List<Event> eventList = CalendarController.getEvent(
															eventButtonGrid[row][col].getText(),
															new Date(year - 1900, month, date)); 
													if (eventList.size() == 1) {
														EventDetails.viewEventDetails(eventList.get(0));
													} else {
														EventList.getEventList(eventList);
													}
												} catch (Exception e1) {
													// e1.printStackTrace();
												}
											}

										}
									}
								}

							}
						});
						contentPane.add(eventButtonGrid[i][j - 1]);
					}
				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	public static int numberOfDaysInMonth(int month, int year) {
		Calendar monthStart = new GregorianCalendar(year, month, 1);
		return monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public JPanel getWeekView() {
		return contentPane;
	}

}
