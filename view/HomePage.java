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

	 private static JPanel contentPane;
	 private static GregorianCalendar calendar;
	 private static JPanel calendarPanel = null;
	 private static JPanel weekPanel = null;
	 private JLabel lblMonthLabel;
	 private JLabel lblYearLabel;

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
				contentPane.removeAll();                                 //////////////
				SwingUtilities.updateComponentTreeUI(contentPane);
				contentPane.add(menuBar);
				showMonthView();
			}
		});

		JMenuItem mntmWeekView = new JMenuItem("Week View");
		mnView.add(mntmWeekView);
		mntmWeekView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();                                 //////////////
				SwingUtilities.updateComponentTreeUI(contentPane);
				contentPane.add(menuBar);
				showWeekView();
//				System.out.println("week view call" + calendar.getTime());
			}
		});
		
		JMenuItem mntmDayView = new JMenuItem("Day View");
		mnView.add(mntmDayView);
		mntmDayView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();                                 //////////////
				SwingUtilities.updateComponentTreeUI(contentPane);
				contentPane.add(menuBar); 
				showDayView(calendar);
			}
		});

		JMenuItem mntmAddEvent = new JMenu("Add Event");
		menuBar.add(mntmAddEvent);
		mntmAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEvent();
			}
		});

		//////////////////////
		showMonthView();
		/////////////////////
	}

	public void addEvent() {
		
	}
	
	
	public void showMonthView() {
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		lblMonthLabel = new JLabel(months[calendar.get(Calendar.MONTH)]);
		lblMonthLabel.setBounds(336, 22, 65, 26);

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
				showMonthView();
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
				showMonthView();
			}
		});
		btnRightButton.setBounds(445, 22, 41, 22);
		contentPane.add(btnRightButton);
		
		
		///////////////////////////
		contentPane.add(lblMonthLabel);
		contentPane.add(lblYearLabel);
		MonthView month = new MonthView(calendar);
		System.out.println(calendar.getTime());                         //
		calendarPanel = month.getMonthView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		SwingUtilities.updateComponentTreeUI(this);
		////////////////////////////
		
	}
	
	public void showWeekView() {
			
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		lblMonthLabel = new JLabel(months[calendar.get(Calendar.MONTH)]);
		lblMonthLabel.setBounds(336, 22, 65, 26);

		lblYearLabel = new JLabel(Integer.toString(calendar.get(Calendar.YEAR)));
		lblYearLabel.setBounds(405, 22, 33, 26);
	
		
		
	
		JButton btnLeftButton = new JButton("<");
		btnLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("bef<" + calendar.getTime());
				int date = calendar.get(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, date-7);
//				System.out.println("aft<" + calendar.getTime());
//				calendar.set(Calendar.MONTH, month - 1);
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showWeekView();
			}
		});
		btnLeftButton.setBounds(281, 22, 41, 22);
		contentPane.add(btnLeftButton);
		
		JButton btnRightButton = new JButton(">");
		btnRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("bef>" + calendar.getTime());
				int date = calendar.get(Calendar.DAY_OF_YEAR);
				calendar.set(Calendar.DAY_OF_YEAR, date+7);
//				System.out.println("aft>" + calendar.getTime());
//				calendar.set(Calendar.MONTH, month + 1);
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showWeekView();
			}
		});
		btnRightButton.setBounds(445, 22, 41, 22);
		contentPane.add(btnRightButton);
		///////////////////////////
		contentPane.add(lblMonthLabel);
		contentPane.add(lblYearLabel);
		
//		System.out.println("bef constr call" + calendar.getTime());

		WeekView week = new WeekView(calendar);
		
//		System.out.println("aft constr call" + calendar.getTime());
		
		calendarPanel = week.getWeekView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
//		System.out.println("week added");
		SwingUtilities.updateComponentTreeUI(this);
		/////////////////////////////
	}
	
	public void showDayView(GregorianCalendar calendar) {
//		paintComponents();
		DayView day = new DayView(calendar);
		calendarPanel = day.getDayView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
//		System.out.println("day added");
		SwingUtilities.updateComponentTreeUI(contentPane);
	}
}
