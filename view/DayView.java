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
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.GridBagLayout;

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
		String[] week = {"   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday","   Saturday"};
		String[] timeInterval = {"12 AM : 01 AM", "01 AM : 02 AM", "02 AM : 03 AM", "03 AM : 04 AM", "04 AM : 05 AM", "05 AM : 06 AM",
				"06 AM : 07 AM", "07 AM : 08 AM", "08 AM : 09 AM", "09 AM : 10 AM", "10 AM : 11 AM", "11 AM : 12 PM",
				"12 PM : 01 PM", "01 PM : 02 PM", "02 PM : 03 PM", "03 PM : 04 PM", "04 PM : 05 PM", "05 PM : 06 PM",
				"06 PM : 07 PM", "07 PM : 08 PM", "08 PM : 09 PM", "09 PM : 10 PM", "10 PM : 11 PM", "11 PM : 12 AM",};

		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.set(Calendar.DAY_OF_MONTH, 2);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		calendar.set(Calendar.DAY_OF_MONTH, todayDate);
		////////////////////////////////
		JLabel monthLabel = new JLabel(Integer.toString(calendar.get(Calendar.MONTH)));
		contentPane.add(monthLabel);
		
//		JButton task1Button = new JButton("Task1");                                   ////////////NO OF TASKS
//		contentPane.add(task1Button);
		JLabel blankLabel1 = new JLabel("");
		contentPane.add(blankLabel1);
		
		JLabel dateLabel = new JLabel(Integer.toString(calendar.get(Calendar.DATE)));
		contentPane.add(dateLabel);
		
		JButton task2Button = new JButton("View Tasks");
		contentPane.add(task2Button);
		
		JLabel dayLabel = new JLabel(week[calendar.get(Calendar.DAY_OF_WEEK)]);
		contentPane.add(dayLabel);
//		
//		JButton viewTaskButton = new JButton("More Tasks");
//		contentPane.add(viewTaskButton);
		JLabel blankLabel2 = new JLabel("");
		contentPane.add(blankLabel2);
		
		
		for (int i = 0; i< 48; ++i) {
			if(i % 2 == 0) {
				JLabel timeLabel = new JLabel(timeInterval[i/2]);
				contentPane.add(timeLabel);
			}
			else if(false){                                                              /////////////////// IF TASK
				JButton taskButton = new JButton("Task");
				contentPane.add(taskButton);
			}
			else {
				JLabel blankLabel = new JLabel("");
				contentPane.add(blankLabel);
			}
		}			
	}

}
