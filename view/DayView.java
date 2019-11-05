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
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.set(Calendar.DATE, 2);
					DayView frame = new DayView(calendar);
					frame.setVisible(true);
				} catch (Exception e) {
					// e.printStackTrace();
					throw e;
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
		String[] week = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "   Saturday" };
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

		List<Event> eventList = null;

		try {
			eventList = CalendarController.readDay(calendar.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (e.getMessage().equals("No Events for the Day")) {
				// KEEP QUIET
			}
		}

		if (eventList.size() > 0) {
			JButton viewAllTasksButton = new JButton("View All Tasks");
			viewAllTasksButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});
			contentPane.add(viewAllTasksButton);
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
			for (int x = 0; x < eventList.size(); ++x) {
				Event event = eventList.get(x);
				int startHour = event.getStartTime().getHours(), endHour = event.getEndTime().getHours(), currentHour = currentTime.getHours(); 
				if(startHour <= currentHour && endHour >= currentHour) {
					if(eventTitle != null) {
						eventTitle = "...";
					}else {
						eventTitle = event.getTitle();
					}
					
				}
			}
			
			
			if (i % 2 == 0) { 													//////////////// TIME
				JLabel timeLabel = new JLabel(timeInterval[i / 2]);
				contentPane.add(timeLabel);
			}
			else if(null == eventTitle) {
				JLabel blankLabel = new JLabel("");
				contentPane.add(blankLabel);
			}else {
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
			}
			
			///////////////////////////////////
		}
	}

	public JPanel getDayView() {
		return contentPane;
	}
}
