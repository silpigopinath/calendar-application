package view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Event;

import javax.swing.JScrollPane;
import javax.swing.JTree;

public class EventList extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EventList dialog = new EventList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EventList() {
		
	}
	public EventList(List<Event> ls) {
		
		setBounds(100, 100, 450, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 434, 362);
			contentPanel.add(scrollPane);
			try {
			    DefaultMutableTreeNode event=new DefaultMutableTreeNode("Event");
			    DefaultMutableTreeNode title=new DefaultMutableTreeNode("title");  
			    event.add(title);
			    
				
			    for(int i=0;i<ls.size();i++)
			    {
			    	
			   
			    String str=ls.get(i).getTitle();
			    DefaultMutableTreeNode node=new DefaultMutableTreeNode(str);  
			    title.add(node);
			    String s1="Start date : "+ls.get(i).getStartDate();
			    DefaultMutableTreeNode start=new DefaultMutableTreeNode(s1);
			    String s2="End date : "+ls.get(i).getEndDate();
			    DefaultMutableTreeNode end=new DefaultMutableTreeNode(s2);
			    String t1="Start Time :"+ls.get(i).getStartTime();
			    DefaultMutableTreeNode time1=new DefaultMutableTreeNode(t1);
			    String t2="End  Time :"+ls.get(i).getEndTime();
			    DefaultMutableTreeNode time2=new DefaultMutableTreeNode(t2);
			    String loc="Location  :"+ls.get(i).getLocation();
			    DefaultMutableTreeNode location=new DefaultMutableTreeNode(loc);
			    String des="Description :"+ls.get(i).getDescription();
			    DefaultMutableTreeNode description=new DefaultMutableTreeNode(des);
			    node.add(start);
			    node.add(end);
			    node.add(time1);
			    node.add(time2);
			    node.add(location);
			    node.add(description);
			    }
				JTree tree = new JTree(event);
				
				scrollPane.setViewportView(tree);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
	}

}
