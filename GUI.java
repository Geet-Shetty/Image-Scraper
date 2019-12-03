package scraper;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	String link; 
	String folder;
	
	JLabel linkinput;
	JLabel folderinput;
	
	JTextField tf;
	JTextField tf2;
	JButton button; 
	
	public GUI() {
		
		setLayout(new FlowLayout());
		
		linkinput = new JLabel("Enter Link: ");
		add(linkinput);
		
		tf = new JTextField(25);
		add(tf);
		
		folderinput = new JLabel("Enter Directory: ");
		add(folderinput);
		
		tf2 = new JTextField(25);
		add(tf2);
		
		button = new JButton("Grab Images");
		add(button);

		event e = new event();
		button.addActionListener(e);
			
		
	}

	
	public class event implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			link = tf.getText();
			folder = tf2.getText();
			System.out.println(link);
			System.out.println(folder);
			PageScraper.ConnectAndPull(link, folder);
			
			
		}
		
	}
	
	public String getLink() {
		return link;
	}
	
	public String getFolder() {
		return folder; 
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(450,150);
		gui.setTitle("Image Grabber");
		gui.setVisible(true);
	}

}
