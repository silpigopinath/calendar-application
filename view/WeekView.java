package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GregorianCalendar cal = new GregorianCalendar();
					cal.set(Calendar.DATE, 2);
					WeekView frame = new WeekView(cal);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		contentPane.add(blankDay1); //////////////// BLANC

		JLabel[] dateLabelGrid = new JLabel[7];

		for (int i = 0; i < 7; ++i) {
			if (i + sundayDate > lastDay) {
				sundayDate = 1 - i;
			}
			dateLabelGrid[i] = new JLabel(Integer.toString(i + sundayDate));
			contentPane.add(dateLabelGrid[i]); ////////////////// DATE
		}

		JLabel blankDay2 = new JLabel(""); ////////////////// BLANC
		contentPane.add(blankDay2);

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			contentPane.add(weekDay); ///////////// WEEKDAY
		}
		JLabel blankDay3 = new JLabel("");
		contentPane.add(blankDay3); //////////////////// BLANC
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
			for(int x = 0; x < eventTable.getRowCount(); ++x) {
				if(currentDayDate.getDate() == (int)eventTable.getValueAt(x, 0)) {
					eventCountInDay ++;
				}
			}
//			System.out.println(eventCountInDay);
			if(eventCountInDay > 0) {
				viewAllEventsButtonGrid[i] = new JButton("View All");
				viewAllEventsButtonGrid[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("event");
						for (int row = 0; row < 7; row++) {
//							System.out.println(row);
							if (viewAllEventsButtonGrid[row] == e.getSource()) {
								///// (DATE) LIST<EVENT> <DIALOG>
								System.out.println(row);
							}

						}

					}
				});
				contentPane.add(viewAllEventsButtonGrid[i]); ////////// VIEWALLEVENTS
			}else {
				JLabel blankDay = new JLabel("i");
				contentPane.add(blankDay); ////////////////////////////////// BLANC
			}
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 24; ++i) {
			for (int j = 0; j < 8; j++) {

				if (j == 0) { /////////////// FOR HOURS
					JLabel hourLabel = new JLabel(timeInterval[i]);
					contentPane.add(hourLabel);
				} else {

					Date currentDayDate = new Date(year - 1900, month,Integer.parseInt(dateLabelGrid[j - 1].getText()));

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
					Date currentTime = new Date(currentDayDate.getYear(), currentDayDate.getMonth(),currentDayDate.getDate(), startTimeInt, 0);
//					System.out.print(currentDayDate);
//					System.out.println(currentTime);
					// ////////////// (DATE, TIME) LIST<EVENT>
					int eventCount = 0;
					Event event = null;
//					System.out.println(eventTable.getRowCount());
					for (int x = 0; x < eventTable.getRowCount(); ++x) {
						if ((int) eventTable.getValueAt(x, 0) == currentDayDate.getDate()) {
							event = (Event) eventTable.getValueAt(x, 1);
							
//							System.out.print(event.getStartTime().getHours());
//							System.out.println("<=" + currentTime.getHours());
//							
//							System.out.print(event.getEndTime().getHours());
//							System.out.println(">=" + currentTime.getHours() + "\n");
							
							int startHour =event.getStartTime().getHours();
							int endHour =event.getEndTime().getHours();
							int currentHour =currentTime.getHours();
							
							if (startHour <= currentHour && endHour >= currentHour) {
								eventCount++;
								System.out.println(startHour + " "  + endHour + currentHour + "in");

							}
						}
					}
//					System.out.println(eventCount);
					if (eventCount == 0) {
						JLabel blankDay4 = new JLabel("");
						contentPane.add(blankDay4);
//						System.out.println("blanc");
					} else if (eventCount >= 1) {
						if (eventCount == 1) {
							String eventTitle = event.getTitle();
							eventButtonGrid[i][j - 1] = new JButton(eventTitle);
						} else {
							eventButtonGrid[i][j - 1] = new JButton("...");
						}
						eventButtonGrid[i][j - 1].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int row = 0; row < 24; row++) {
									for (int col = 0; col < 7; col++) {
										if (eventButtonGrid[row][col] == e.getSource()) {
											///// (DATE, TIME, TITLE) EVENT <DIALOG>
											System.out.println(row + "" + col);
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
