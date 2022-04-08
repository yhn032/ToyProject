package mymain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame; // 스윙컴퍼런스
import javax.swing.JPanel;

public class _01_TestGraphics extends JFrame {

	static Image img;;
	//static 초기화 (프로그램 실행 중간에 메인메소드가 생성될 때)
	static {
		//이미지 로딩
		img = Toolkit.getDefaultToolkit().getImage("캡처.png");
	}
	
	JPanel grimPan;
	
	public _01_TestGraphics() {
		// TODO Auto-generated constructor stub
		super("Graphics 연습");
		
		init_grimpan();
		
		//위치		 x	  y
		this.setLocation(200, 100);

		//크기
		//this.setSize(400, 400);
		pack();

		//보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		//종료							// JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}

	Font font = new Font("굴림체", Font.BOLD, 25);
	
	private void init_grimpan() {
		// TODO Auto-generated method stub
		//JPanel : 도화지 (내부클래스)
		class GrimPanel extends JPanel{
			
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				//Graphics : 그리기도구(봇, 연필)
//텍스트 출력
				
				
				//폰트적용
				g.setFont(font);
				//문자열 출력(좌표기준점 : 출력된 문자의 좌측 하단)
				g.drawString("안지워지나?", 0, 30);
				
				// 그림자 출력하기
				g.setColor(Color.black);
				g.drawString("그리기 연습", 100+1, 60+1); 
				g.setColor(Color.red);
				g.drawString("그리기 연습", 100, 60);
//도형 출력
				g.setColor(Color.blue);
				//사각형(테두리만)
				g.drawRect(10, 100, 100, 100);
				//사각형(채워서 그리기)
				g.fillRect(120, 100, 100, 100);
				
				//원(테두리만)
				g.drawOval(10, 210, 100, 100);
				//원(채워서)
				g.fillOval(120, 210, 100, 100);
				
				//선
				g.drawLine(400, 0, 0, 400);
//이미지
				g.drawImage(img, 220, 100,100,100, this);
				g.drawImage(img, 0, 0, this);
			}
		}
		
		grimPan = new GrimPanel();
		
		grimPan.setPreferredSize(new Dimension(400, 400));
		this.add(grimPan,"Center");
		
		//연필, 붓 등 그리기 객체 
		//Graphics g = grimPan.getGraphics();
		//g.drawString("안녕하세요", 0, 30);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new _01_TestGraphics();
	}

}