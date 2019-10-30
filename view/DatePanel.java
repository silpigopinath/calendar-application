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
	
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("ButtonClick" + this.getName());
//	}
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
		
		btnDateButton = new JButton(date);
		btnDateButton.setBounds(0, 1, 115, 50);
		add(btnDateButton);
//		btnDateButton.addActionListener(this);
		
		if(event1 != null) {
		btnEvent1 = new JButton(event1);
		btnEvent1.setBounds(0, 50, 115, 14);
		add(btnEvent1);
		}
		
		if(event2 != null) {
		btnEvent2 = new JButton(event2);
		btnEvent2.setBounds(0, 64, 115, 14);
		add(btnEvent2);
		}
		
		if(event3 != null) {
		btnEvent3 = new JButton(event3);
		btnEvent3.setBounds(0, 78, 115, 14);
		add(btnEvent3);
		}
	}
	
	
	public static DatePanel getDatePanel(String date, String event1, String event2, String event3) {
		return new DatePanel(date, event1, event2, event3);
	}

}
