package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddEvent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEventName;
	private JTextField txtLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEvent dialog = new AddEvent();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEvent() {
		
		this.setTitle("Add Event");
		setBounds(100, 100, 406, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtEventName = new JTextField();
		txtEventName.setBounds(122, 11, 241, 20);
		contentPanel.add(txtEventName);
		txtEventName.setColumns(10);
		
		
		txtLocation = new JTextField();
		txtLocation.setBounds(122, 42, 241, 20);
		contentPanel.add(txtLocation);
		txtLocation.setColumns(10);
		
		JTextArea txtrEventDescription = new JTextArea();
		txtrEventDescription.setBounds(122, 235, 241, 130);
		contentPanel.add(txtrEventDescription);
		
		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setBounds(10, 11, 84, 20);
		contentPanel.add(lblEventName);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 42, 84, 20);
		contentPanel.add(lblLocation);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(10, 104, 84, 20);
		contentPanel.add(lblStartTime);
		
		JLabel lblEventDescription = new JLabel("Event Description");
		lblEventDescription.setBounds(10, 237, 84, 20);
		contentPanel.add(lblEventDescription);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(10, 166, 84, 20);
		contentPanel.add(lblEndTime);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(122, 73, 241, 20);
		contentPanel.add(dateChooser);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(10, 73, 84, 20);
		contentPanel.add(lblStartDate);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(122, 135, 241, 20);
		contentPanel.add(dateChooser_1);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(10, 135, 84, 20);
		contentPanel.add(lblEndDate);
		
		String[] timeInterval = { "12 AM : 01 AM", "01 AM : 02 AM", "02 AM : 03 AM", "03 AM : 04 AM", "04 AM : 05 AM",
				"05 AM : 06 AM", "06 AM : 07 AM", "07 AM : 08 AM", "08 AM : 09 AM", "09 AM : 10 AM", "10 AM : 11 AM",
				"11 AM : 12 PM", "12 PM : 01 PM", "01 PM : 02 PM", "02 PM : 03 PM", "03 PM : 04 PM", "04 PM : 05 PM",
				"05 PM : 06 PM", "06 PM : 07 PM", "07 PM : 08 PM", "08 PM : 09 PM", "09 PM : 10 PM", "10 PM : 11 PM",
				"11 PM : 12 AM", };
		JComboBox cmbBoxStartTime = new JComboBox(timeInterval);
//		cmbBoxStartTime.addItemListener(this); 
		 
		cmbBoxStartTime.setBounds(122, 104, 241, 20);
		contentPanel.add(cmbBoxStartTime);
		
		JComboBox cmbBoxEndTime = new JComboBox(timeInterval);
		cmbBoxEndTime.setBounds(122, 166, 241, 20);
		contentPanel.add(cmbBoxEndTime);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String eventName = txtEventName.getText();
						String eventLocation = txtLocation.getText();
						Date startDate = dateChooser.getDate();
						Date endDate = dateChooser_1.getDate();
						String startTime = (String)cmbBoxStartTime.getSelectedItem();
						String endTime = (String)cmbBoxEndTime.getSelectedItem();
						
					}
				});
				okButton.setActionCommand("OK");
				okButton.grabFocus();
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
