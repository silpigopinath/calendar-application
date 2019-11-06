package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;
import controller.CalendarController;
import model.Event;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class EventDetails extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtEventName;

	private JTextField txtLocation;

	private static JPanel buttonPane;

	private static CalendarController controller;

	/**
	 * Launch the application.
	 */

	public static void viewEventDetails(Event event) {

		try {
			EventDetails dialog = new EventDetails(event);

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);

		} catch (Exception e) {

			throw e;

		}

	}

	public static void viewEventDetails(String title, Date date, Date time) {

		try {
			EventDetails dialog = new EventDetails(title, date, time);

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);

		} catch (Exception e) {

			throw e;

		}

	}

	/**
	 * Create the dialog.
	 */
	public EventDetails() {

	}

	public EventDetails(Event evnt) {

		this.setTitle("Event Details");

		setBounds(100, 100, 406, 445);

		setResizable(false);

		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(null);

		display(evnt);
	}

	public EventDetails(String title, Date date, Date time) {

		this.setTitle("Event Details");

		setBounds(100, 100, 406, 445);

		setResizable(false);

		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(null);
		try {
			Event evnt = controller.getEventByTime(date, time, title);
			display(evnt);
		} catch (SQLException e1) {

		}
	}

	public void display(Event evnt) {

		txtEventName = new JTextField();

		txtEventName.setBounds(122, 11, 241, 20);

		contentPanel.add(txtEventName);

		txtEventName.setColumns(10);
		txtEventName.setText(evnt.getTitle());

		txtLocation = new JTextField();

		txtLocation.setBounds(122, 42, 241, 20);

		contentPanel.add(txtLocation);

		txtLocation.setColumns(10);
		txtLocation.setText(evnt.getLocation());

		JTextArea txtrEventDescription = new JTextArea();

		txtrEventDescription.setBounds(122, 203, 241, 130);

		JScrollPane scrollPane = new JScrollPane(txtrEventDescription);

		scrollPane.setBounds(122, 203, 241, 130);

		contentPanel.add(scrollPane);
		txtrEventDescription.setText(evnt.getDescription());

		JLabel lblEventName = new JLabel("Event Name");

		lblEventName.setBounds(10, 11, 84, 20);

		contentPanel.add(lblEventName);

		JLabel lblLocation = new JLabel("Location");

		lblLocation.setBounds(10, 42, 84, 20);

		contentPanel.add(lblLocation);

		JLabel lblStartTime = new JLabel("Start Time");

		lblStartTime.setBounds(10, 104, 84, 20);

		contentPanel.add(lblStartTime);

		JLabel lblDescription = new JLabel("Description");

		lblDescription.setBounds(10, 205, 84, 20);

		contentPanel.add(lblDescription);

		JLabel lblEndTime = new JLabel("End Time");

		lblEndTime.setBounds(10, 166, 84, 20);

		contentPanel.add(lblEndTime);

		JDateChooser startDateChooser = new JDateChooser();

		startDateChooser.setBounds(122, 73, 241, 20);

		contentPanel.add(startDateChooser);

		startDateChooser.setDate(evnt.getStartDate());

		JLabel lblStartDate = new JLabel("Start Date");

		lblStartDate.setBounds(10, 73, 84, 20);

		contentPanel.add(lblStartDate);

		JDateChooser endDateChooser = new JDateChooser();

		endDateChooser.setBounds(122, 135, 241, 20);

		contentPanel.add(endDateChooser);
		endDateChooser.setDate(evnt.getEndDate());

		JLabel lblEndDate = new JLabel("End Date");

		lblEndDate.setBounds(10, 135, 84, 20);

		contentPanel.add(lblEndDate);

		String[] timeInterval = { "12 AM", "01 AM", "02 AM", "03 AM", "04 AM", "05 AM", "06 AM", "07 AM", "08 AM",

				"09 AM", "10 AM", "11 AM", "12 PM", "01 PM", "02 PM", "03 PM", "04 PM", "05 PM", "06 PM", "07 PM",

				"08 PM", "09 PM", "10 PM", "11 PM" };

		JComboBox cmbBoxStartTime = new JComboBox(timeInterval);

		cmbBoxStartTime.setBounds(122, 104, 241, 20);

		contentPanel.add(cmbBoxStartTime);

		int start = evnt.getStartTime().getHours();
		int end = evnt.getEndTime().getHours();

		JComboBox cmbBoxEndTime = new JComboBox(timeInterval);

		cmbBoxEndTime.setBounds(122, 166, 241, 20);

		contentPanel.add(cmbBoxEndTime);

		cmbBoxStartTime.setSelectedIndex(start);
		cmbBoxEndTime.setSelectedIndex(end);

		JLabel lblWarning = new JLabel("");

		lblWarning.setBounds(10, 344, 241, 20);

		contentPanel.add(lblWarning);

		buttonPane = new JPanel();

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Save and close");

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {
					CalendarController.removeEvent(evnt);
				} catch (SQLException e) {

				}
				String eventName = "", eventLocation = "", description = "";

				Date endDate, startDate, startTime, endTime;

				eventName = txtEventName.getText();

				eventLocation = txtLocation.getText();

				startDate = startDateChooser.getDate();

				endDate = endDateChooser.getDate();

				String startTimeString = (String) cmbBoxStartTime.getSelectedItem();

				String endTimeString = (String) cmbBoxEndTime.getSelectedItem();

				description = txtrEventDescription.getText();

				if (eventName == null || eventName.equals("")) {

					lblWarning.setText("Event Name Cannot be Empty");

				} else if (startDate == null) {

					lblWarning.setText("Start Date Cannot be Empty");

				}

				else if (endDate != null && startDate.compareTo(endDate) > 0) {

					lblWarning.setText("End Date Before Start Date!!!");

				}

				else if (endDate == null) {

					endDate = startDate;

				}

				int startTimeInt;

				String val;

				if (startTimeString.equals("12 AM")) {

					startTimeInt = 0;

				} else if (startTimeString.equals("12 PM")) {

					startTimeInt = 12;

				} else if (startTimeString.charAt(3) == "A".charAt(0)) {

					val = startTimeString.substring(0, 2);

					startTimeInt = Integer.parseInt(val);

				} else {

					val = startTimeString.substring(0, 2);

					startTimeInt = Integer.parseInt(val) + 12;

				}

				int endTimeInt;

				String endVal;

				if (endTimeString.equals("12 AM")) {

					endTimeInt = 0;

				} else if (endTimeString.equals("12 PM")) {

					endTimeInt = 12;

				} else if (endTimeString.charAt(3) == "A".charAt(0)) {

					endVal = endTimeString.substring(0, 2);

					endTimeInt = Integer.parseInt(endVal);

				} else {

					endVal = endTimeString.substring(0, 2);

					endTimeInt = Integer.parseInt(endVal) + 12;

				}

				System.out.println(endTimeInt);

				startTime = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate(), startTimeInt, 0);

				endTime = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), endTimeInt, 0);

				controller.addEvent(new Event(eventName, eventLocation, description, startDate,

						startTime, endDate, endTime));

				HomePage.refreshView();

				dispose();

			}

		});

		buttonPane.add(okButton);

		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Delete");

		cancelButton.setActionCommand("Delete");

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {
					controller.removeEvent(evnt);
					HomePage.refreshView();
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		buttonPane.add(cancelButton);
	}

}
