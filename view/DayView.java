package view;



import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.CalendarController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JScrollPane;

public class DayView extends JFrame {

	private JPanel contentPane;
	static CalendarController controller; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayView frame = new DayView(new GregorianCalendar());
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
	public DayView(GregorianCalendar calendar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridLayout grid = new GridLayout(27, 2, 0, 0);
		contentPane.setLayout(grid);
	
		////////////////////////////////
		JButton[] numButtons= new JButton[24];
		String[] week = {"   Sunday", "   Monday", "   Tuesday", "   Wednesday", "   Thursday", "   Friday","   Saturday"};
		String[] timeInterval = {"12 AM ", "01 AM ", "02 AM ", "03 AM ", "04 AM ", "05 AM ",
				"06 AM ", "07 AM ", "08 AM ", "09 AM ", "10 AM ", "11 AM ",
				"12 PM ", "01 PM ", "02 PM ", "03 PM ", "04 PM ", "05 PM ",
				"06 PM ", "07 PM ", "08 PM ", "09 PM ", "10 PM ", "11 PM "};
		String[] months= {"January","February","March","April","May","June","July","August","September","October","November","December"};
		int totalDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
		int month=calendar.get(Calendar.MONTH);
		int year=calendar.get(Calendar.YEAR);
		calendar.set(Calendar.DAY_OF_MONTH, 2);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		calendar.set(Calendar.DAY_OF_MONTH, todayDate);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JLabel monthLabel = new JLabel(months[calendar.get(Calendar.MONTH)]);
		scrollPane.setViewportView(monthLabel);
		
		JLabel dateLabel = new JLabel(week[calendar.get(Calendar.DAY_OF_WEEK)]);
		contentPane.add(dateLabel);
		

		JLabel blankLabel1 = new JLabel("");
		contentPane.add(blankLabel1);
		
		JButton task2Button = new JButton("View Tasks");
		contentPane.add(task2Button);
		
		JLabel dayLabel = new JLabel();
		contentPane.add(dayLabel);

		JLabel blankLabel2 = new JLabel("");
		contentPane.add(blankLabel2);
		Date d= new Date(year-1900,month,todayDate);
		System.out.println(d);

		for (int i = 0; i< 24; ++i) {
			
				JLabel timeLabel = new JLabel(timeInterval[i]);
				contentPane.add(timeLabel);
				numButtons[i]=new JButton("Event");
				numButtons[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {        ///Show Event Details
					   
						System.out.println("Event clicked");
						
					}
				});
				contentPane.add(numButtons[i]);
				
			
			

		}
		
	}

	public JPanel getDayView() {
		return contentPane;
	}
}