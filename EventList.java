package view;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.CalendarController;
import model.Event;

import javax.swing.JScrollPane;
import javax.swing.JTree;

public class EventList extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTree tree;
	private static Event evnt;
	/**
	 * Launch the application.
	 */
	public static void getEventList(List<Event> ls) {
		try {
			EventList dialog = new EventList(ls);
			dialog.setTitle("Events List");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the dialog.
	 */
	public EventList() {
		getContentPane().setLayout(null);
		
	}
	
	public EventList(List<Event> ls) {
		
		setBounds(100, 100, 430, 390);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 434, 362);
			contentPanel.add(scrollPane);
			try {
				if(ls!=null)
				{
			    DefaultMutableTreeNode event=new DefaultMutableTreeNode("Events");
			   
			    
				
			    for(int i=0;i<ls.size();i++)
			    {
			    	
			   evnt=ls.get(i);
			    String str=evnt.getTitle();
			    DefaultMutableTreeNode node=new DefaultMutableTreeNode(str);  
			    event.add(node);
			    String s1="Start date : "+evnt.getStartDate();
			    DefaultMutableTreeNode start=new DefaultMutableTreeNode(s1);
			    String s2="End date : "+evnt.getEndDate();
			    DefaultMutableTreeNode end=new DefaultMutableTreeNode(s2);
			    String t1="Start Time :"+evnt.getStartTime();
			    DefaultMutableTreeNode time1=new DefaultMutableTreeNode(t1);
			    String t2="End  Time :"+evnt.getEndTime();
			    DefaultMutableTreeNode time2=new DefaultMutableTreeNode(t2);
			    String loc="Location  :"+evnt.getLocation();
			    DefaultMutableTreeNode location=new DefaultMutableTreeNode(loc);
			    String des="Description :"+evnt.getDescription();
			    DefaultMutableTreeNode description=new DefaultMutableTreeNode(des);
			    node.add(start);
			    node.add(end);
			    node.add(time1);
			    node.add(time2);
			    node.add(location);
			    node.add(description);
			    }
				tree = new JTree(event);
				
				scrollPane.setViewportView(tree);
				tree.addTreeSelectionListener(new TreeSelectionListener() {
				    public void valueChanged(TreeSelectionEvent e) {
				    	
				    	DefaultMutableTreeNode root = (DefaultMutableTreeNode)
		                           tree.getModel().getRoot();
				        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				                           tree.getLastSelectedPathComponent();
				         
				        if(node.isLeaf())
				        {
				        	DefaultMutableTreeNode parent = (DefaultMutableTreeNode)
			                           node.getParent(); 
				        	System.out.println(root.getIndex(parent));
				        	int index=root.getIndex(parent);
				        	System.out.println(ls.get(index).getTitle());			        	
				        	new EventDetails(ls.get(index)).setVisible(true);
				        	}
				        else if(!node.isRoot())
				        {
				        	int index=root.getIndex(node);
				        	new EventDetails(ls.get(index)).setVisible(true);
				        }
				        
				        
				    }
				});
			}
			}catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}

	}

}