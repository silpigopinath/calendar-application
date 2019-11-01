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

import controller.CalendarController;
import model.Event;

import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DayView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayView frame = new DayView(new GregorianCalendar());
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
	public DayView(GregorianCalendar calendar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(27, 2, 0, 0);
		contentPane.setLayout(grid);

		////////////////////////////////
		String[] week = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
				"   Saturday" };
		String[] timeInterval = { "12 AM", "01 AM", "02 AM", "03 AM", "04 AM", "05 AM", "06 AM", "07 AM", "08 AM",
				"09 AM", "10 AM", "11 AM", "12 PM", "01 PM", "02 PM", "03 PM", "04 PM", "05 PM", "06 PM", "07 PM",
				"08 PM", "09 PM", "10 PM", "11 PM" };

		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);

		////////////////////////////////
		JLabel dateLabel = new JLabel(Integer.toString(todayDate));
		contentPane.add(dateLabel);

		JLabel blankLabel1 = new JLabel();
		contentPane.add(blankLabel1);

		JLabel dayLabel = new JLabel(week[calendar.get(Calendar.DAY_OF_WEEK) - 1]);

		contentPane.add(dayLabel);

		JButton viewAllTasksButton = new JButton("View All Tasks");
		viewAllTasksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					List<Event> eventList = CalendarController.readDay(calendar.getTime());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if (e.getMessage().equals("No Events for the Day")) {
						// KEEP QUIET
					}
				}

			}
		});
		contentPane.add(viewAllTasksButton);

		JLabel blankLabel2 = new JLabel();
		contentPane.add(blankLabel2);
		JLabel blankLabel3 = new JLabel("");
		contentPane.add(blankLabel3);

		for (int i = 0; i < 48; ++i) {
			///////////////////////////////////
			String interval = timeInterval[i / 2];
			int startTimeInt, val;

			if (interval.equals("12 AM")) {
				startTimeInt = 0;
			} else if (interval.equals("12 PM")) {
				startTimeInt = 12;
			} else if (interval.charAt(3) == "A".charAt(0)) {
				startTimeInt = Integer.parseInt(interval.substring(0, 2));
			} else {
				startTimeInt = Integer.parseInt(interval.substring(0, 2));
			}
			Date startTime = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), startTimeInt, 0);
			List<Event> eventListOfHour = null;
			try {
				eventListOfHour = CalendarController.readByDateTime(calendar.getTime(), startTime);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JButton eventButtonGrid[] = new JButton[24];
			///////////////////////////////////
			if (i % 2 == 0) {
				JLabel timeLabel = new JLabel(timeInterval[i / 2]);
				contentPane.add(timeLabel);
			} else if (eventListOfHour.isEmpty()) {
				JLabel blankLabel = new JLabel("");
				contentPane.add(blankLabel);
			} else if (eventListOfHour.size() == 1) {
				String eventTitle = eventListOfHour.get(0).getTitle();
				eventButtonGrid[i / 2] = new JButton(eventTitle);
				eventButtonGrid[i / 2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int row = 0; row < 24; row++) {

							if (eventButtonGrid[row] != null && e.getSource() == eventButtonGrid[row]) {
								//////////////////// (DATE) LIST<EVENT> <DIALOG>
								System.out.println(eventButtonGrid[row]);
								System.out.println(row + "" + row);

							}

						}
					}
				});

				contentPane.add(eventButtonGrid[i / 2]);
			} else {
				String eventTitle = "...";
				eventButtonGrid[i / 2] = new JButton(eventTitle);
				eventButtonGrid[i / 2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int row = 0; row < 24; row++) {

							if (eventButtonGrid[row] != null && e.getSource() == eventButtonGrid[row]) {
								//////////////////// (DATE) LIST<EVENT> <DIALOG>
								System.out.println(eventButtonGrid[row]);
								System.out.println(row + "" + row);

							}

						}
					}
				});
			}
		}
	}

	public JPanel getDayView() {
		return contentPane;
	}
}
