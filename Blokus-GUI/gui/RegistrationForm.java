package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.GuiActionTranslator;

public class RegistrationForm {
	
	private GuiActionTranslator translator;
	private JFrame frame;
	private JPanel content;
	private JTextField[] argFields = new JTextField[4];
	private String[] names = {"Username: ", "Password: ", "Verify Password: ", "Player Name: "};
	JButton create, cancel;
	
	public RegistrationForm(GuiActionTranslator translator) {
		this.translator = translator;
		init();
	}
	
	private void init() {
		frame = new JFrame("Register User");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildPanel();
		frame.add(content);
		frame.pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int positionX = (int)screen.getWidth()/2 - (int)frame.getSize().getWidth()/2;
		int positionY = (int)screen.getHeight()/2 - (int)frame.getSize().getHeight()/2;
		frame.setLocation(positionX, positionY);
		frame.setVisible(true);
	}
	
	private void buildPanel() {
		content = new JPanel();
		content.setLayout(new BorderLayout());
		addFields(content);
		addButtons(content);
	}
	
	private void addFields(JPanel panel) {
		initFields();
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(0,2));
		
		for(int i = 0; i < argFields.length; i++) {
			fields.add(new JLabel(names[i]));
			fields.add(argFields[i]);
		}
		panel.add(fields, BorderLayout.NORTH);
	}
	
	private void initFields() {
		for(int i = 0; i < argFields.length; i++) {
			if(i == 1 || i == 2)
				argFields[i] = new JPasswordField(15);
			else
				argFields[i] = new JTextField(15);
		}
	}
	
	private void addButtons(JPanel panel) {
		JPanel buttons = new JPanel();
		create = new JButton("Create");
		create.setFocusPainted(false);
		registerCreateAction();
		buttons.add(create);
		
		cancel = new JButton("Cancel");
		cancel.setFocusPainted(false);
		registerCancelAction();
		buttons.add(cancel);
		
		panel.add(buttons, BorderLayout.SOUTH);
	}
	
	public void registerCreateAction() {
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String[] args = extractCreateCriteria();
				if(args != null && args[1].equals(args[2])) {
					if(translator.registerUser(args[0], args[1], args[3])) {
						JOptionPane.showMessageDialog(new JFrame("Success"), "User " + args[0] + " was created successfully!");
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(new JFrame("Error"), "Something went wrong.\n The username is" +
								" already in use.");
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame("Error"), "Something went wrong.\nPerhaps the password fields" +
							" don't match, or you left a field blank.");
				}
			}
		});
	}
	
	public void registerCancelAction() {
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});
	}
	
	private String[] extractCreateCriteria() {
		String[] args = new String[argFields.length];
		for(int i = 0; i < argFields.length; i++) {
			if(argFields[i].getText().equals(""))
				return null;
			else {
				args[i] = argFields[i].getText();
			}
		}
		return args;
	}

}
