import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	
	static String[] button = {"7", "8", "9", "¡À", "4", "5", "6", "¡¿",
							  "1", "2", "3", "-", "C", "0", "=", "+"};
	private Label tempL, resL;
	static int op;
	static String temp = "", res;
	static Stack<Character> stack = new Stack<>();
	
	public Main() {
		setTitle("°è»ê±â");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		p1.setLayout(new GridLayout(1, 1));
		JLabel lb = new JLabel("0");
		lb.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 30));
		lb.setVerticalAlignment(SwingConstants.CENTER);
		lb.setHorizontalAlignment(SwingConstants.RIGHT);
		p1.add(lb);
		
		p2.setLayout(new GridLayout(1, 4));
		for(int i = 0; i < 4; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 30));
			p2.add(btn);
		}
		p3.setLayout(new GridLayout(1, 4));
		for(int i = 4; i < 8; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 30));
			p3.add(btn);
		}
		p4.setLayout(new GridLayout(1, 4));
		for(int i = 8; i < 12; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 30));
			p4.add(btn);
		}
		p5.setLayout(new GridLayout(1, 4));
		for(int i = 12; i < 16; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 30));
			p5.add(btn);
		}
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		c.add(p5);
		
		setSize(500, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
