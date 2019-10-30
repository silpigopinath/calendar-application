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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		DatePanel[][] dateButtonGrid = new DatePanel[6][7];

		////////////////////////////////
		String[] week = { "   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday",
				"   Saturday" };
		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		////////////////////////////////

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel(week[i]);
			weekDay.setPreferredSize(new Dimension(120, 100));
			contentPane.add(weekDay);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////
		// for (int i = 0; i < firstDay - 1; ++i) {
		// JLabel blankDay = new JLabel();
		// blankDay.setPreferredSize(new Dimension(120, 100));
		// contentPane.add(blankDay);
		// }
		//
		// for (int i = 1; i <= totalDaysInMonth; ++i) {
		// contentPane.add(DatePanel.getDatePanel(Integer.toString(i), "task to do",
		////////////////////////////////////////////////////////////////////////////////////////////////// null,
		////////////////////////////////////////////////////////////////////////////////////////////////// null));
		// }
		// for (int i = firstDay + totalDaysInMonth - 1; i < 42; ++i) {
		// JLabel blankDay = new JLabel();
		// blankDay.setPreferredSize(new Dimension(120, 100));
		// contentPane.add(blankDay);
		// }
		//
		//////////////////////////////////////////////////////////////////////////////////////////////////
		for (int j = 0; j < firstDay - 1; j++) {

		}

		// if ((i * 7) + (j + 1) >= (firstDay - 1) + totalDaysInMonth) { //////////
		// CONDITION FOR EVENT
		// dateButtonGrid[i][j - 1] = new DatePanel(Integer.toString((i * 7) + (j + 1) -
		// (firstDay - 1)), "", null,
		// null); ////////// VIEW EVENT
		//
		// dateButtonGrid[i][j - 1].getBtnDateButton().addActionListener(new
		// ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// for (int row = 0; row < 6; row++) {
		// for (int col = 0; col < 7; col++) {
		// if (dateButtonGrid[row][col] == e.getSource()) {
		// ///// VIEW EVENT DETAILS
		// System.out.println(row);
		// System.out.println(col);
		// }
		// }
		// }
		//
		// }
		// });
		//
		// contentPane.add(dateButtonGrid[i][j - 1]);
		// }
		int ROWS = 6;
		int COLS = 7;

		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				//////////////////////
				if (i == 0 && j < firstDay - 1) {
					JLabel blankDay = new JLabel();
					contentPane.add(blankDay);
				}
				/////////////////////
				else if ((i * COLS) + (j + 1) <= (firstDay - 1) + totalDaysInMonth) {
					dateButtonGrid[i][j] = new DatePanel(Integer.toString((i * 7) + (j + 1) - (firstDay - 1)), "gfg",
							null, null);                                                                                       ////////CHECK FOR TASK
					dateButtonGrid[i][j].getBtnDateButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// System.out.println(e.getSource());
							for (int row = 0; row < ROWS; row++) {
								for (int col = 0; col < COLS; col++) {
									if (dateButtonGrid[row][col] != null
											&& e.getSource() == dateButtonGrid[row][col].getBtnDateButton()) {
										////////////////////VIEW EVENT DIALOG
										System.out.println(dateButtonGrid[row][col].getBtnDateButton().getText());
										System.out.println(row + "" + col);
										
									}
								}
							}
						}
					});

					contentPane.add(dateButtonGrid[i][j]);

				}
				/////////////////////
				else {
					JLabel blankDay = new JLabel();
					contentPane.add(blankDay);
				}
			}

		}

		// else {
		// JLabel blankDay4 = new JLabel("");
		// contentPane.add(blankDay4);
		// }

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public JPanel getMonthView() {
		return contentPane;
	}

}
