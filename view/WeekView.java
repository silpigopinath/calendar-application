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

public class WeekView extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeekView frame = new WeekView(new GregorianCalendar());
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
	public WeekView(GregorianCalendar calendar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(27, 8, 0, 0);
		contentPane.setLayout(grid);
		
		JButton[][] taskButtonGrid = new JButton[24][7];
		
		
		
		////////////////////////////////
		String[] week = { "   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday",
				"   Saturday" };
		String[] timeInterval = {"12 AM : 01 AM", "01 AM : 02 AM", "02 AM : 03 AM", "03 AM : 04 AM", "04 AM : 05 AM", "05 AM : 06 AM",
								"06 AM : 07 AM", "07 AM : 08 AM", "08 AM : 09 AM", "09 AM : 10 AM", "10 AM : 11 AM", "11 AM : 12 PM",
								"12 PM : 01 PM", "01 PM : 02 PM", "02 PM : 03 PM", "03 PM : 04 PM", "04 PM : 05 PM", "05 PM : 06 PM",
								"06 PM : 07 PM", "07 PM : 08 PM", "08 PM : 09 PM", "09 PM : 10 PM", "10 PM : 11 PM", "11 PM : 12 AM",};
		
//		calendar.set(Calendar.DAY_OF_MONTH, 3);

		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
		int todayDay = calendar.get(Calendar.DAY_OF_WEEK);		
		int sundayIndex = todayDate - todayDay + 1;
		int sundayDate = 0;
		if(sundayIndex > 0) {
			sundayDate = sundayIndex;
		}

		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(sundayIndex <= 0) {
			
			sundayDate = lastDay + sundayIndex;
		}
		////////////////////////////////
		
		

		JLabel blankDay1 = new JLabel("");
		contentPane.add(blankDay1);

		for (int i = 0; i < 7; ++i) {
			if(i + sundayDate > lastDay) {
				sundayDate = 1 - i;
			}
			JLabel weekDay = new JLabel("                " + (i + sundayDate));
			contentPane.add(weekDay);
		}

		JLabel blankDay2 = new JLabel("");
		contentPane.add(blankDay2);

		for (int i = 0; i < 7; ++i) {
			JLabel weekDay = new JLabel("       " + week[i]);
			contentPane.add(weekDay);
		}
		JLabel blankDay3 = new JLabel("");
		contentPane.add(blankDay3);
		for (int i = 0; i < 7; ++i) {
			if(true) {                                                            //////////EVENT CONDITION
				JButton eventList = new JButton("View Events");					  //////////NO OF EVENTS
				contentPane.add(eventList);
			}
		}

		for (int i = 0; i < 24; ++i) {
			for(int j = 0; j < 8; j++) {
				if(j % 8 == 0) {
					JLabel hourLabel = new JLabel(timeInterval[i/8]);
					contentPane.add(hourLabel);
				}
				else if (i>20){                                                         //////////CHECK FOR EVENT
					taskButtonGrid[i][j - 1] = new JButton("eventx");					//////////VIEW EVENT
					taskButtonGrid[i][j - 1].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for (int row = 0; row < 24; row++) {
							    for (int col = 0; col < 7; col++) {
							       if (taskButtonGrid[row][col] == e.getSource()) {
							       /////VIEW EVENT DIALOG
							    	   System.out.println(row + "" + col);
							       }
							    }
							  }
							
						}
					});
					
					contentPane.add(taskButtonGrid[i][j - 1]);
				}
				else {
					JLabel blankDay4 = new JLabel("");
					contentPane.add(blankDay4);
				}
			}
			
		}

	}
	
	public JPanel getWeekView() {
		return contentPane;
	}

}
