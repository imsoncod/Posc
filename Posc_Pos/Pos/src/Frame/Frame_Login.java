package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Oracle.Oracle_DAO;
import client.client;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Color;

public class Frame_Login extends JFrame implements ActionListener, KeyListener {

	public JPanel contentPane;
	public JTextField txt_usernum;
	public JPasswordField txt_userpw;
	public JLabel label_PoscLogo, label_usernum, label_userpw, label_request;
	public JButton btn_login;
	public static String usernum;

	public Frame_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Posc");
		setSize(427, 310);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/WindowIcon.png")));
		
		setLoginFrame();
		
		setVisible(true);
		txt_userpw.requestFocus();
	}

	private void setLoginFrame() {
		label_PoscLogo = new JLabel("Posc");
		label_PoscLogo.setForeground(new Color(53,135,145));
		label_PoscLogo.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 50));
		label_PoscLogo.setBounds(0, 12, 421, 71);
		label_PoscLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_PoscLogo);
		
		label_usernum = new JLabel("È¸¿ø¹øÈ£");
		label_usernum.setForeground(new Color(62,62,62));
		label_usernum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_usernum.setBounds(33, 123, 62, 18);
		contentPane.add(label_usernum);
		
		txt_usernum = new JTextField("19-00001");
		txt_usernum.setHorizontalAlignment(SwingConstants.CENTER);
		txt_usernum.setBorder(new LineBorder(new Color(53,135,145),1));
		txt_usernum.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		txt_usernum.setBackground(SystemColor.control);
		txt_usernum.setBounds(109, 120, 177, 29);
		contentPane.add(txt_usernum);
		txt_usernum.setColumns(10);
		txt_usernum.setFocusable(false);
		
		label_userpw = new JLabel("ºñ¹Ð¹øÈ£");
		label_userpw.setForeground(new Color(62,62,62));
		label_userpw.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_userpw.setBounds(33, 181, 62, 18);
		contentPane.add(label_userpw);
		
		txt_userpw = new JPasswordField();
		txt_userpw.setHorizontalAlignment(SwingConstants.CENTER);
		txt_userpw.setBorder(new LineBorder(new Color(53,135,145),1));
		txt_userpw.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		txt_userpw.setBounds(109, 176, 177, 29);
		txt_userpw.setColumns(10);
		contentPane.add(txt_userpw);
		txt_userpw.addKeyListener(this);
		
		btn_login = new JButton("·Î±×ÀÎ");
		btn_login.setForeground(new Color(53,135,145));
		btn_login.setBackground(Color.WHITE);
		btn_login.setOpaque(true);
		btn_login.setFocusable(false);
		btn_login.setBorder(new LineBorder(new Color(53,135,145),1));
		btn_login.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 18));
		btn_login.setBounds(299, 120, 91, 85);
		contentPane.add(btn_login);
		btn_login.addActionListener(this);
		
		label_request = new JLabel("¹®ÀÇ) 010-9140-3915");
		label_request.setForeground(Color.GRAY);
		label_request.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_request.setBounds(259, 238, 154, 25);
		contentPane.add(label_request);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Oracle_DAO oracle = new Oracle_DAO();
		Object event = e.getSource();
		if(event == btn_login) {
			usernum = txt_usernum.getText();
			String userpw = txt_userpw.getText();
			if(oracle.OracleLogin(usernum, userpw)==0) {
				new Frame_Main();
				dispose();
			}else {
				new Frame_MessageBox("ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Oracle_DAO oracle = new Oracle_DAO();
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			usernum = txt_usernum.getText();
			String userpw = txt_userpw.getText();
			if(oracle.OracleLogin(usernum, userpw)==0) {
				client clnt = new client();
				new Frame_Main();
				dispose();
			}else {
				new Frame_MessageBox("ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
