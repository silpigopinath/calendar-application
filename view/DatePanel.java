package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class DatePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DatePanel(String date, String event1, String event2, String event3) {

		this.setPreferredSize(new Dimension(120, 100));
		setLayout(null);
		
		JButton btnDateButton = new JButton(date);
		btnDateButton.setBounds(0, 1, 115, 50);
		add(btnDateButton);
		
		if(event1 != null) {
		JButton btnEvent3 = new JButton(event1);
		btnEvent3.setBounds(0, 50, 115, 14);
		add(btnEvent3);
		}
		
		if(event2 != null) {
		JButton btnEvent2 = new JButton(event2);
		btnEvent2.setBounds(0, 64, 115, 14);
		add(btnEvent2);
		}
		
		if(event3 != null) {
		JButton btnEvent1 = new JButton(event3);
		btnEvent1.setBounds(0, 78, 115, 14);
		add(btnEvent1);
		}
	}
	
	public static DatePanel getDatePanel(String date, String event1, String event2, String event3) {
		return new DatePanel(date, event1, event2, event3);
	}

}
