package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class DatePanel extends JPanel {

	JButton btnDateButton;
	JButton btnEvent1;
	JButton btnEvent2;
	JButton btnEvent3;

	public JButton getBtnDateButton() {
		return btnDateButton;
	}

	public JButton getBtnEvent1() {
		return btnEvent1;
	}

	public JButton getBtnEvent2() {
		return btnEvent2;
	}

	public JButton getBtnEvent3() {
		return btnEvent3;
	}

	/**
	 * Create the panel.
	 */
	public DatePanel(String date, String event1, String event2, String event3) {

		this.setPreferredSize(new Dimension(120, 100));
		setLayout(null);
		
		if (event1 != null) {
			btnEvent1 = new JButton(event1);
			
		}

		if (event2 != null) {
			btnEvent2 = new JButton(event2);
			
		}

		if (event3 != null) {
			btnEvent3 = new JButton(event3);
			
		}
		
		if (event1 == null && event2 == null && event3 == null) {
			btnDateButton = new JButton(date);
			btnDateButton.setBounds(3, 2, 110, 92);
			add(btnDateButton);
		}else if (event2 == null && event3 == null) {
			btnDateButton = new JButton(date);
			btnDateButton.setBounds(3, 2, 110, 78);
			add(btnDateButton);
			
			btnEvent1.setBounds(3, 78, 110, 14);
			add(btnEvent1);
			
		}else if (event3 == null) {
			btnDateButton = new JButton(date);
			btnDateButton.setBounds(3, 2, 110, 64);
			add(btnDateButton);
			
			btnEvent1.setBounds(3, 64, 110, 14);
			add(btnEvent1);
			
			btnEvent2.setBounds(3, 78, 110, 14);
			add(btnEvent2);
			
		} else {
			btnDateButton = new JButton(date);
			btnDateButton.setBounds(3, 2, 110, 50);
			add(btnDateButton);
			
			btnEvent1.setBounds(3, 50, 110, 14);
			add(btnEvent1);
			
			btnEvent2.setBounds(3, 64, 110, 14);
			add(btnEvent2);
			
			btnEvent3.setBounds(3, 78, 110, 14);
			add(btnEvent3);
		}

		
	}

	public static DatePanel getDatePanel(String date, String event1, String event2, String event3) {
		return new DatePanel(date, event1, event2, event3);
	}

}
