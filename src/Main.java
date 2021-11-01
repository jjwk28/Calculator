import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	
	// 기본 사칙연산만 가능, 괄호연산 불가
	
	static String[] button = {"7", "8", "9", "÷", "4", "5", "6", "×",
							  "1", "2", "3", "-", "C", "0", "=", "+"};
	static JLabel lb1, lb2; // lb1 : 계산 식 출력, lb2 : 계산 결과 출력
	static String result = ""; // 계산 결과
	static boolean TF; // "="을 누르는 경우 true로 전환
	
	public Main() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel(); // 계산 식 및 결과 표시
		JPanel p2 = new JPanel(); // 7, 8, 9, ÷
		JPanel p3 = new JPanel(); // 4, 5, 6, ×
		JPanel p4 = new JPanel(); // 1, 2, 3, -
		JPanel p5 = new JPanel(); // C, 0, =, +
		
		p1.setLayout(new GridLayout(2, 1));
		lb1 = new JLabel("0");
		lb1.setFont(new Font("맑은고딕", Font.BOLD, 20));
		lb1.setOpaque(true);
		lb1.setForeground(Color.gray);
		lb1.setBackground(Color.black);
		lb1.setVerticalAlignment(SwingConstants.CENTER);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lb2 = new JLabel("0");
		lb2.setFont(new Font("맑은고딕", Font.BOLD, 40));
		lb2.setOpaque(true);
		lb2.setForeground(Color.white);
		lb2.setBackground(Color.black);
		lb2.setVerticalAlignment(SwingConstants.CENTER);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		p1.add(lb1);
		p1.add(lb2);
		
		p2.setLayout(new GridLayout(1, 4));
		for(int i = 0; i < 4; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("맑은고딕", Font.BOLD, 30));
			if(i % 4 == 3) {
				btn.setBackground(new Color(234, 237, 238));
			}
			else {
				btn.setBackground(Color.white);
			}
			btn.addActionListener(new MyActionListener());
			p2.add(btn);
		}
		p3.setLayout(new GridLayout(1, 4));
		for(int i = 4; i < 8; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("맑은고딕", Font.BOLD, 30));
			if(i % 4 == 3) {
				btn.setBackground(new Color(234, 237, 238));
			}
			else {
				btn.setBackground(Color.white);
			}
			btn.addActionListener(new MyActionListener());
			p3.add(btn);
		}
		p4.setLayout(new GridLayout(1, 4));
		for(int i = 8; i < 12; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("맑은고딕", Font.BOLD, 30));
			if(i % 4 == 3) {
				btn.setBackground(new Color(234, 237, 238));
			}
			else {
				btn.setBackground(Color.white);
			}
			btn.addActionListener(new MyActionListener());
			p4.add(btn);
		}
		p5.setLayout(new GridLayout(1, 4));
		for(int i = 12; i < 16; i++) {
			JButton btn = new JButton(button[i]);
			btn.setFont(new Font("맑은고딕", Font.BOLD, 30));
			if(i % 4 == 3) {
				btn.setBackground(new Color(234, 237, 238));
			}
			else if(i % 4 != 1) {
				btn.setBackground(new Color(132, 190, 247));
			}
			else {
				btn.setBackground(Color.white);
			}
			btn.addActionListener(new MyActionListener());
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
	private class MyActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			for(int i = 0; i < button.length; i++) {
				if(b.getText().equals(button[i])) {
					if(i % 4 == 3) { // 연산자를 눌렀을 경우
						if(TF) {
							TF = false;
						}
						result += b.getText();
						lb2.setText(result);
						break;
					}
					else if(i == 12) { // 'C'를 눌렀을 경우
						result = "0";
						lb1.setText(result);
						lb2.setText(result);
						result = "";
						TF = false;
						break;
					}
					else if(i == 14) { // '='를 눌렀을 경우
						lb1.setText(result);
						result = Integer.toString(calculator());
						lb2.setText(result);
						TF = true;
						break;
					}
					else { // 숫자를 눌렀을 경우
						if(TF) {
							result = "";
							TF = false;
						}
						result += b.getText();
						lb2.setText(result);
						break;
					}
				}
			}
			if(result.length() >= 20) { // 계산 식이 20자리가 넘어가면 글자크기 축소
				lb2.setFont(new Font("맑은고딕", Font.BOLD, 30));
			}
		}
	}
	public static int calculator() { // 연산식 계산
		ArrayList<String> list = postfix(result);
		Stack<Integer> stack = new Stack<>(); // 연산 과정 저장
		
		for(int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			
			// 연산자인 경우
			if(s.equals("+") || s.equals("-") || s.equals("×") || s.equals("÷")) {
				if(stack.size() >= 2) {
					int n1 = stack.pop();
					int n2 = stack.pop();
					int res = cal(n2, n1, s);
					stack.push(res);
				}
			}
			else { // 연산자가 아닌 경우
				stack.push(Integer.parseInt(s));
			}
		}
		int answer = stack.pop();
		return answer;
	}
	public static int cal(int a, int b, String str) { // 연산자 계산
		switch(str) {
		case "+":
			return a+b;
		case "-":
			return a-b;
		case "×":
			return a*b;
		case "÷":
			return a/b;
		}
		return 0;
	}
	public static ArrayList<String> postfix(String result) { // 중위연산 -> 후위연산
		String temp = ""; // 숫자 임시 저장
		ArrayList<String> list = new ArrayList<>(); // 후위 표기법 저장
		Stack<Character> op = new Stack<>(); // 연산자 저장
		
		for(int i = 0; i < result.length(); i++) {
			char ch = result.charAt(i);
			
			if(ch >= '0' && ch <= '9') {
				temp += ch;
			}
			else {
				list.add(temp);
				temp = "";
				
				if(op.isEmpty()) { // op가 비었으면 연산자 그대로 저장
					op.add(ch);
				}
				else {
					if(rankOp(ch) > rankOp(op.peek())) { // op 가장 위의 연산자보다 우선순위가 높은 경우 그대로 저장
						op.add(ch);
					}
					else {
						// op가 비어있지않고 op 가장 위의 연산자보다 우선순위가 작거나 같은 경우 pop한 연산자를 list에 저장
						while(!op.isEmpty() && rankOp(ch) <= rankOp(op.peek())) {
							list.add(Character.toString(op.pop()));
						}
						op.add(ch);
					}
				}
			}
		}
		list.add(temp);
		while(!op.isEmpty()) {
			list.add(Character.toString(op.pop()));
		}
		return list;
	}
	public static int rankOp(char ch) { // 연산자의 우선순위를 리턴
		switch(ch) {
		case '+':
		case '-':
			return 1;
		case '×':
		case '÷':
			return 2;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
