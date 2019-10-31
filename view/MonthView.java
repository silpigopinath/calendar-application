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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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

		DatePanel[][] datePanelGrid = new DatePanel[6][7];

		////////////////////////////////
		String[] week = { "   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday",
				"   Saturday" };
		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		int firstDay = firstDayInMonth(month, year);
		////////////////////////////////

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			weekDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(weekDay);
		}

		int ROWS = 6;
		int COLS = 7;
		for (int i = 0; i < ROWS; ++i) {

			for (int j = 0; j < COLS; ++j) {
				if (i == 0 && j < firstDay - 1) {
					JLabel blankDay = new JLabel();
					contentPane.add(blankDay);
				}
				///////////////////////////////////////////////////////////////////////////////////////////
				else if ((i * COLS) + (j + 1) <= (firstDay - 1) + totalDaysInMonth) {
					
					List<Event> eventsToday = null;
					int currentDate = (i * 7) + (j + 1) - (firstDay - 1);
					try {
						eventsToday = CalendarController.readDay(new Date(year, month, currentDate));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					List<String> eventList = null;
					String event1 = null, event2 = null, event3 = null;
					Event event = eventsToday.get(0);
					event1 = event.getTitle();
					event = eventsToday.get(1);
					event2 = event.getTitle();
					event = eventsToday.get(2);
					event3 = event.getTitle();
					if (eventsToday.size() > 3) {
						event3 = "View More Events";
					}
					datePanelGrid[i][j] = new DatePanel(Integer.toString(currentDate), event1, event2, event3);
					///////////////////////////////////////////
					datePanelGrid[i][j].getBtnDateButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for (int row = 0; row < ROWS; row++) {
								for (int col = 0; col < COLS; col++) {
									if (datePanelGrid[row][col] != null
											&& e.getSource() == datePanelGrid[row][col].getBtnDateButton()) {
										//////////////////// VIEW ALL EVENTS OF DAY DIALOG
										System.out.println(datePanelGrid[row][col].getBtnDateButton().getText());
										System.out.println(row + "" + col);

									}
								}
							}
						}
					});

					if (datePanelGrid[i][j].getBtnEvent1() != null) {
						datePanelGrid[i][j].getBtnEvent1().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int row = 0; row < ROWS; row++) {
									for (int col = 0; col < COLS; col++) {
										if (datePanelGrid[row][col] != null
												&& e.getSource() == datePanelGrid[row][col].getBtnEvent1()) {
											//////////////////// VIEW EVENT DETAILS DIALOG
											System.out.println(datePanelGrid[row][col].getBtnEvent1().getText());
											System.out.println(row + "" + col);

										}
									}
								}
							}
						});
					}
					if (datePanelGrid[i][j].getBtnEvent2() != null) {
						datePanelGrid[i][j].getBtnEvent2().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int row = 0; row < ROWS; row++) {
									for (int col = 0; col < COLS; col++) {
										if (datePanelGrid[row][col] != null
												&& e.getSource() == datePanelGrid[row][col].getBtnEvent2()) {
											//////////////////// VIEW EVENT DETAILS DIALOG
											System.out.println(datePanelGrid[row][col].getBtnEvent2().getText());
											System.out.println(row + "" + col);

										}
									}
								}
							}
						});
					}
					if (datePanelGrid[i][j].getBtnEvent3() != null) {
						datePanelGrid[i][j].getBtnEvent3().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int row = 0; row < ROWS; row++) {
									for (int col = 0; col < COLS; col++) {
										if (datePanelGrid[row][col] != null
												&& e.getSource() == datePanelGrid[row][col].getBtnEvent3()) {
											//////////////////// VIEW EVENT DETAILS DIALOG
											System.out.println(datePanelGrid[row][col].getBtnEvent3().getText());
											System.out.println(row + "" + col);

										}
									}
								}
							}
						});
					}
					contentPane.add(datePanelGrid[i][j]);
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////
				else {
					JLabel blankDay = new JLabel();
					contentPane.add(blankDay);
				}
			}

		}

	}

	public static int firstDayInMonth(int month, int year) {
		Calendar monthStart = new GregorianCalendar(year, month, 1);
		return monthStart.get(Calendar.DAY_OF_WEEK);
	}

	public JPanel getMonthView() {
		return contentPane;
	}

}
