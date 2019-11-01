package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class WorkingHome extends JFrame {

	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkingHome frame = new WorkingHome();
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
	public WorkingHome() {
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 47, 870, 730);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1184, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnFile.add(mntmNewMenuItem_1);
		
		
		JLabel lblMonthLabel = new JLabel("OCTOBER");
		lblMonthLabel.setBounds(336, 22, 59, 26);
		contentPane.add(lblMonthLabel);
		
		JButton btnRightButton = new JButton(">");
		btnRightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRightButton.setBounds(445, 24, 41, 23);
		contentPane.add(btnRightButton);
		
		JLabel lblyearLabel = new JLabel("2019");
		lblyearLabel.setBounds(405, 22, 33, 26);
		contentPane.add(lblyearLabel);
		
		JButton btnLeftButton = new JButton("<");
		btnLeftButton.setBounds(285, 24, 41, 23);
		contentPane.add(btnLeftButton);
		btnLeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
//		String[] months = {"January", "February", "March", "April"};
//		JComboBox comboBox = new JComboBox(months);
//		comboBox.setBounds(35, 22, 74, 20);
//		contentPane.add(comboBox);
		
		
		
		
		//////////////////////
//		MonthView month = new MonthView(new GregorianCalendar());
//		calendarPanel = month.getMonthView();
//		calendarPanel.setBounds(0, 31, 870, 730);
//		contentPane.add(calendarPanel);
		/////////////////////
		
//		panel
//		contentPane.add(month.getMonthView());
		
//		MonthView.showMonthView(new GregorianCalendar());
	}
}
