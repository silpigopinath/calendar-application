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

public class MonthView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
					calendar.set(calendar.MONTH, 1);
					System.out.println(calendar.getTime());
					MonthView frame = new MonthView(calendar);
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
	public MonthView(GregorianCalendar calendar) {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(7, 7, 5, 5);
		contentPane.setLayout(grid);

		////////////////////////////////
		String[] week = { "   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday",
				"   Saturday" };

		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);

//		calendar.set(Calendar.DAY_OF_MONTH, todayDate);
		
		System.out.println("Total: " + totalDaysInMonth + ", Today: " + todayDate + ", FirstDay : " + firstDay);
		////////////////////////////////

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			weekDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(weekDay);
		}
		for (int i = 0; i < firstDay - 1; ++i) {
			JLabel blankDay = new JLabel();
			blankDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(blankDay);
		}
		
		for (int i = 1; i <= totalDaysInMonth; ++i) {
			contentPane.add(DatePanel.getDatePanel(Integer.toString(i), "task to do", null, null));
		}
		for (int i = firstDay + totalDaysInMonth - 1; i < 42; ++i) {
			JLabel blankDay = new JLabel();
			blankDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(blankDay);
		}

	}
	
	public JPanel getMonthView() {
		return contentPane;
	}

}
