package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame {
	JTextField tf = new JTextField(20);

	public Frame() {
		Log log = new Log();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setUndecorated(false); // Temporary
		setSize(500, 500);
		// setShape(new RoundRectangle2D.Double(20, 20, 250, 250, 200, 200));
		// setLocationRelativeTo(null);
		setLocation((dim.width / 2) - (getWidth() / 2), (dim.height / 2) - (getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ChatBot");

		// Background
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("C:/Users/Mike/workspace/Nova")));
		setLayout(new FlowLayout());
		add(tf);
		
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = tf.getText();
				log.typeln("User input: " + input);
			}
		});

		setVisible(true);
		Log.typeln(getTitle() + " online");
	}
}
