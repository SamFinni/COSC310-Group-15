package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import chat.ChatBot;

public class Interface extends JFrame implements KeyListener, ActionListener {
	Log log = new Log();
	ResultSet rs;
	public static boolean blnMimicing = false;

	private static final long serialVersionUID = 2007645652554426099L;
	private static String[] quote = new String[2];
	private final Button btnClose;

	JPanel p = new JPanel();
	static JTextArea dialog = new JTextArea(24, 60);
	static JTextArea input = new JTextArea(1, 60);
	public static JLabel label = new JLabel();

	public static JLabel label2 = new JLabel();
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	// makes window
	public Interface() {
		super("Chat-Bot");
		setSize(700, 500);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		btnClose = new Button("close");

		p.add(scroll);
		label.setText("                                                 ");
		label2.setText("*");
		p.add(label);
		p.add(label2);
		p.add(input);
		p.add(btnClose);
		DefaultCaret caret = (DefaultCaret) dialog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		p.setBackground(new Color(220, 220, 220));
		getContentPane().add(p);

		log.typeln(this.getTitle() + " online");
		
		dialog.setEditable(false);
		input.addKeyListener(this);
		btnClose.addActionListener(this);

		
		setVisible(true);
		input.requestFocus();
	}

	// adds to text area
	public static void addText(String str) {
		dialog.setText(dialog.getText() + "\n--->You: " + str);
	}

	public static void respond(String str) {
		dialog.setText(dialog.getText() + "\n--->Nova: " + str);
	}

	public static String getquote() {
		return quote[0];
	}

	// adds input on ENTER
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// System.out.println("keypressed: Enter");
			if (!input.getText().equals("")) {
				input.setEditable(false);
				quote[1] = Long.toString(System.currentTimeMillis());
				quote[0] = input.getText();

				input.setText("");
				quote[0] = quote[0].trim();
				addText(quote[0]);

				String s = getquote();
				log.typeln("User input: " + s);
				
				
				
			} else {
				input.setEditable(false);
				input.setText("");
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	public void actionPerformed(ActionEvent e) {

		// neuronHandler.sav();
		// memoryHandler.sav();
		System.exit(0);
	}
}
