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
		
		JToggleButton tglbtnDateButton = new JToggleButton(date);
		tglbtnDateButton.setBounds(0, 1, 115, 50);
		add(tglbtnDateButton);
		
		if(event1 != null) {
		JToggleButton tglbtnEvent3 = new JToggleButton(event1);
		tglbtnEvent3.setBounds(0, 50, 115, 14);
		add(tglbtnEvent3);
		}
		
		if(event2 != null) {
		JToggleButton tglbtnEvent2 = new JToggleButton(event2);
		tglbtnEvent2.setBounds(0, 64, 115, 14);
		add(tglbtnEvent2);
		}
		
		if(event3 != null) {
		JToggleButton tglbtnEvent1 = new JToggleButton(event3);
		tglbtnEvent1.setBounds(0, 78, 115, 14);
		add(tglbtnEvent1);
		}
	}
	
	public static DatePanel getDatePanel(String date, String event1, String event2, String event3) {
		return new DatePanel(date, event1, event2, event3);
	}

}
