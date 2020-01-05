package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Color;

public class Frame_MessageBox extends JFrame implements ActionListener, KeyListener {
	JLabel label_msg;
	JButton btn_ok;

	private JPanel contentPane;
	private JLabel label_posc;
	public Frame_MessageBox(String msg) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(380, 220);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new LineBorder(new Color(53,135,145), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_msg = new JLabel(msg);
		label_msg.setForeground(new Color(62,62,62));
		label_msg.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16));
		label_msg.setHorizontalAlignment(SwingConstants.CENTER);
		label_msg.setBounds(49, 70, 275, 47);
		contentPane.add(label_msg);
		
		btn_ok = new JButton("È®ÀÎ");
		btn_ok.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		btn_ok.setBorder(new LineBorder(new Color(53,135,145)));
		btn_ok.setFocusable(false);
		btn_ok.setOpaque(true);
		btn_ok.setBackground(Color.WHITE);
		btn_ok.setForeground(new Color(53,135,145));
		btn_ok.setBounds(134, 156, 105, 27);
		btn_ok.addActionListener(this);
		btn_ok.addKeyListener(this);
		contentPane.add(btn_ok);
		
		label_posc = new JLabel("Posc");
		label_posc.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		label_posc.setForeground(new Color(53,135,145));
		label_posc.setBounds(10, 10, 57, 15);
		contentPane.add(label_posc);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if(event == btn_ok) {
			contentPane.remove(label_msg);
			contentPane.remove(btn_ok);
			dispose();
		}	
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			contentPane.remove(label_msg);
			contentPane.remove(btn_ok);
			dispose();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
