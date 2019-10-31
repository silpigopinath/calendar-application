package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class AddEvent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEventName;
	private JTextField txtLocation;
	private JTextField txtStartTime;
	private JTextField txtEndTime;

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
		setBounds(100, 100, 276, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEventDetails = new JLabel("Event Details");
		lblEventDetails.setBounds(10, 11, 155, 25);
		lblEventDetails.grabFocus();
		contentPanel.add(lblEventDetails);
		
		txtEventName = new JTextField();
		txtEventName.setToolTipText("Event Name");
		txtEventName.setText("Event Name");
		txtEventName.setBounds(10, 47, 241, 20);
//		txtEventName.setFocusable(false);
		txtEventName.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        txtEventName.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    	if("".equals(txtEventName.getText())) {
		    		txtEventName.setText("Event Name");
		    	}
//		    	
		    }
		});
		contentPanel.add(txtEventName);
		txtEventName.setColumns(10);
		
		
		txtLocation = new JTextField();
		txtLocation.setText("Location");
		txtLocation.setBounds(10, 78, 241, 20);
		txtLocation.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        txtLocation.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		contentPanel.add(txtLocation);
		txtLocation.setColumns(10);
		
		txtStartTime = new JTextField();
		txtStartTime.setText("Start Time");
		txtStartTime.setBounds(123, 109, 129, 20);
		contentPanel.add(txtStartTime);
		txtStartTime.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBounds(0, 0, 91, 20);
		dateChooser.setToolTipText("");
		dateChooser.getCalendarButton().setText("Start Date");
		dateChooser.setBounds(10, 109, 91, 20);
		dateChooser.grabFocus();
		contentPanel.add(dateChooser);
		dateChooser.setLayout(null);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.getCalendarButton().setText("End Date");
		dateChooser_1.getCalendarButton().setBounds(0, 0, 91, 20);
		dateChooser_1.setBounds(10, 147, 91, 20);
		contentPanel.add(dateChooser_1);
		dateChooser_1.setLayout(null);
		
		txtEndTime = new JTextField();
		txtEndTime.setText("End Time");
		txtEndTime.setBounds(123, 147, 128, 20);
		contentPanel.add(txtEndTime);
		txtEndTime.setColumns(10);
		
		JTextArea txtrEventDescription = new JTextArea();
		txtrEventDescription.setText("Event Description");
		txtrEventDescription.setBounds(10, 176, 241, 130);
		contentPanel.add(txtrEventDescription);
		
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
//						Date startTime = txtStartTime.getText();
//						new Event()
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
