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
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePage extends JFrame {

	 private static JPanel contentPane;
	 private static GregorianCalendar calendar;
	 private static JPanel calendarPanel = null;
	 private static JLabel lblMonthLabel;
	 private static JLabel lblYearLabel;
	 private static JMenuBar menuBar = null;
	 private static String viewIndicator = null;
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
		setBounds(50, 50, 885, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		calendar = new GregorianCalendar();               ////////////INITIALIZING CALENDAR

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 885, 21);

		JMenuItem mntmMonthView = new JMenuItem("Month View");
		menuBar.add(mntmMonthView);
		mntmMonthView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showMonthView();
			}
		});

		JMenuItem mntmWeekView = new JMenuItem("Week View");
		menuBar.add(mntmWeekView);
		mntmWeekView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showWeekView();
			}
		});
		
		JMenuItem mntmDayView = new JMenuItem("Day View");
		menuBar.add(mntmDayView);
		mntmDayView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				showDayView();
			}
		});

		JMenuItem mntmAddEvent = new JMenuItem("Add Event");
		menuBar.add(mntmAddEvent);
		mntmAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEvent dialog = new AddEvent();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JMenuItem mntmGoToToday = new JMenuItem("Go To Today");
		menuBar.add(mntmGoToToday);
		mntmGoToToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calendar = new GregorianCalendar();
				showDayView();
			}
		});
		
		//////////////////////
		showMonthView();
		/////////////////////
	}
	
	public static void refreshView() {
		if(viewIndicator.equals("m")) {
			showMonthView();
		}else if(viewIndicator.equals("w")) {
			showWeekView();
		}else {
			showDayView();
		}
	}
	
	public static void showMonthView() {
		
		contentPane.removeAll();                              
		SwingUtilities.updateComponentTreeUI(contentPane);
		
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
		calendarPanel = month.getMonthView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		contentPane.add(menuBar);
//		frame.add(contentPane);
//		SwingUtilities.updateComponentTreeUI(contentPane);
		SwingUtilities.updateComponentTreeUI(contentPane);
		////////////////////////////
		viewIndicator = "m";
		
	}
	
	public static void showWeekView() {
		
		contentPane.removeAll();                              
		SwingUtilities.updateComponentTreeUI(contentPane);
			
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		lblMonthLabel = new JLabel(months[calendar.get(Calendar.MONTH)]);
		lblMonthLabel.setBounds(336, 22, 65, 26);

		lblYearLabel = new JLabel(Integer.toString(calendar.get(Calendar.YEAR)));
		lblYearLabel.setBounds(405, 22, 33, 26);
	
		
		
	
		JButton btnLeftButton = new JButton("<");
		btnLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int date = calendar.get(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, date-7);
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
				int date = calendar.get(Calendar.DAY_OF_YEAR);
				calendar.set(Calendar.DAY_OF_YEAR, date+7);
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

		WeekView week = new WeekView(calendar);
		
		calendarPanel = week.getWeekView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		contentPane.add(menuBar);
		SwingUtilities.updateComponentTreeUI(contentPane);
		/////////////////////////////
		viewIndicator = "w";
	}
	
	public static void showDayView() {
		
		contentPane.removeAll();                              
		SwingUtilities.updateComponentTreeUI(contentPane);
		
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
				int date = calendar.get(Calendar.DAY_OF_YEAR);
				calendar.set(Calendar.DAY_OF_YEAR, date-1);
				System.out.println(calendar.get(Calendar.DATE));
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showDayView();
			}
		});
		btnLeftButton.setBounds(281, 22, 41, 22);
		contentPane.add(btnLeftButton);
		
		JButton btnRightButton = new JButton(">");
		btnRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int month = calendar.get(Calendar.MONTH);
				int date = calendar.get(Calendar.DAY_OF_YEAR);
				calendar.set(Calendar.DAY_OF_YEAR, date+1);                            
				contentPane.remove(calendarPanel);
				contentPane.remove(lblMonthLabel);
				contentPane.remove(lblYearLabel);
				showDayView();
			}
		});
		btnRightButton.setBounds(445, 22, 41, 22);
		contentPane.add(btnRightButton);
		
		contentPane.add(lblMonthLabel);
		contentPane.add(lblYearLabel);
		
		DayView day = new DayView(calendar);
		calendarPanel = day.getDayView();
		calendarPanel.setBounds(0, 47, 870, 730);
		contentPane.add(calendarPanel);
		contentPane.add(menuBar);
		SwingUtilities.updateComponentTreeUI(contentPane);
		viewIndicator = "d";
	}
}
