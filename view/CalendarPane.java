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

public class CalendarPane extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarPane frame = new CalendarPane();
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
	public CalendarPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(7, 7, 5, 5);
		contentPane.setLayout(grid);
		
		////////////////////////////////
		String[] week = {"   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday","   Saturday"};
		GregorianCalendar calendar = new GregorianCalendar();
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
//		System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(firstDay);
		
		calendar.set(Calendar.DAY_OF_MONTH, todayDate);
		////////////////////////////////
		
		
		for (int i = 0; i<7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			weekDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(weekDay);
//			System.out.println(i);
		}
		
		for (int i = 0; i<41; ++i) {

			contentPane.add(DatePanel.getDatePanel(Integer.toString(i), "fsgfv", null , null));
		}
		
		
		
		
		
	}

}
