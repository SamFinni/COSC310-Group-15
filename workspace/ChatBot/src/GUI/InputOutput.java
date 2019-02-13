package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputOutput extends JFrame{
	
	public InputOutput(){
		String str = JOptionPane.showInputDialog(null, "Enter your message:");
		System.out.println(str);
	}
	
}
