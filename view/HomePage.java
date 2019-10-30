package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.Calendar;
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
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePage extends JFrame {

	 static JPanel contentPane;
	 static GregorianCalendar calendar;
	 static JPanel calendarPanel = null;
	 static JPanel weekPanel = null;
	 JLabel lblMonthLabel;
	 JLabel lblYearLabel;

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
		
		calendar = new GregorianCalendar();               ////////////INITIALIZING CALENDAR

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1184, 21);
		contentPane.add(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmMonthView = new JMenuItem("Month View");
		mnView.add(mntmMonthView);
		mntmMonthView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(calendarPanel);
				showMonthView(calendar);
			}
		});

		JMenuItem mntmWeekView = new JMenuItem("Week View");
		mnView.add(mntmWeekView);
		mntmWeekView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(calendarPanel);                                 //////////////
				showWeekView(calendar);
			}
		});
		
		JMenuItem mntmDayView = new JMenuItem("Day View");
		mnView.add(mntmDayView);
		mntmDayView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(calendarPanel);                                 //////////////
				showDayView(calendar);
			}
		});

		JMenuItem mntmAddEvent = new JMenu("Add Event");
		menuBar.add(mntmAddEvent);

		//////////////////////
		showMonthView(calendar);
		/////////////////////
	}

	public void showMonthView(GregorianCalendar calendar) {
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		lblMonthLabel = new JLabel(months[calendar.get(Calendar.MONTH)]);
		lblMonthLabel.setBounds(336, 22, 59, 26);

		lblYearLabel = new JLabel(Integer.toString(calendar.get(Calendar.YEAR)));
		lblYearLabel.setBounds(405, 22, 33, 26);
	
		JButton btnLeftButton = new JButton("<");
		btnLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int month = calendar.get(Calendar.MONTH);
				calendar.set(Calendar.DATE, 1);
				calendar.set(Calendar.MONTH, month - 1);
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showMonthView(calendar);
			}
		});
		btnLeftButton.setBounds(281, 22, 41, 22);
		contentPane.add(btnLeftButton);
		
		JButton btnRightButton = new JButton(">");
		btnRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int month = calendar.get(Calendar.MONTH);
				calendar.set(Calendar.DATE, 1);
				calendar.set(Calendar.MONTH, month + 1);
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showMonthView(calendar);
			}
		});
		btnRightButton.setBounds(445, 22, 41, 22);
		contentPane.add(btnRightButton);
		
		///////////////////////////
		MonthView month = new MonthView(calendar);
		contentPane.add(lblMonthLabel);
		contentPane.add(lblYearLabel);
		calendarPanel = month.getMonthView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		SwingUtilities.updateComponentTreeUI(this);
		////////////////////////////
		
	}
	
	public void showWeekView(GregorianCalendar calendar) {
		WeekView week = new WeekView(calendar);
		calendarPanel = week.getWeekView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		System.out.println("week added");
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void showDayView(GregorianCalendar calendar) {
		DayView day = new DayView(calendar);
		calendarPanel = day.getDayView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		System.out.println("day added");
		SwingUtilities.updateComponentTreeUI(this);
	}
}
