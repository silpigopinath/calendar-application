package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePage extends JFrame {

	private static JPanel contentPane;
	static GregorianCalendar calendar = new GregorianCalendar();
	
	static JYearChooser yearChooser = null;
	static JMonthChooser monthChooser = null;
	static JLabel lblMonthLabel = new JLabel();
	static JPanel calendarPanel = null;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1184, 21);
		contentPane.add(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmMonthView = new JMenuItem("Month View");
		mnView.add(mntmMonthView);

		JMenuItem mntmWeekView = new JMenuItem("Week View");
		mnView.add(mntmWeekView);

		JMenuItem mntmDayView = new JMenuItem("Day View");
		mnView.add(mntmDayView);

		JMenuItem mntmAddEvent = new JMenu("Add Event");
		menuBar.add(mntmAddEvent);

		

		//////////////////////
		showMonthView(new GregorianCalendar());
		/////////////////////
	}

	public static void showMonthView(GregorianCalendar calendar) {
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(466, 22, 88, 20);
		yearChooser.addPropertyChangeListener("year", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
				calendar.set(GregorianCalendar.MONTH, monthChooser.getMonth());
				calendar.set(GregorianCalendar.YEAR, (int) e.getNewValue());
				System.out.println("Comet :" + calendar.getTime());
				contentPane.remove(calendarPanel);
				showMonthView(calendar);

			}
		});

		monthChooser = new JMonthChooser();
		monthChooser.setBounds(228, 22, 116, 20);
		monthChooser.addPropertyChangeListener("month", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
				calendar.set(GregorianCalendar.YEAR, yearChooser.getYear());
				calendar.set(GregorianCalendar.MONTH, (int) e.getNewValue());
				contentPane.remove(calendarPanel);
				showMonthView(calendar);
			}
		});

//		lblMonthLabel = new JLabel("");
		lblMonthLabel.setBounds(354, 22, 100, 26);
		
		contentPane.add(yearChooser);
		contentPane.add(monthChooser);
		contentPane.add(lblMonthLabel);
		lblMonthLabel.removeAll();
		lblMonthLabel.setText(Integer.toString(calendar.get(GregorianCalendar.MONTH)));


		MonthView month = new MonthView(calendar);
		
		calendarPanel = month.getMonthView();
		calendarPanel.setBounds(0, 47, 870, 730);
		
		
		contentPane.add(calendarPanel);
		System.out.println("done");
		
		
	}
}
