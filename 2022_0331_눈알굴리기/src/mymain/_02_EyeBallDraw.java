package mymain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel; // 스윙컴퍼런스

public class _02_EyeBallDraw extends JFrame {
	
	//그림판의 폭과 높이를 지정할 상수
	public static final int GRIMPAN_WIDTH  = 400;
	public static final int GRIMPAN_HEIGHT = 630;
	
	static Image back_img, back_off_img;
	static {
		back_img = Toolkit.getDefaultToolkit().getImage("도로로.png");
		back_off_img = Toolkit.getDefaultToolkit().getImage("도로로off.png");
		
	}
	
	JPanel grimPan;
	
	//눈의 좌표
	Point eye_left  = new Point();
	Point eye_right = new Point();
	int eye_radius;
	
	//눈알 좌표
	Point eyeball_left = new Point();
	Point eyeball_right = new Point();
	int eyeball_radius;
	
	//눈 안에 포인터가 들어왔냐?
	//마우스 포인터가 눈 안에 들어와서 true가 되면 이미지를 바꾸어 넣어라
	boolean bEyeInPt = false;
	
	public _02_EyeBallDraw() {
		// TODO Auto-generated constructor stub
		super("눈알 그리기");
		
		//그림판 초기화
		init_grimPan();
		
		//눈의 위치 초기화
		init_eye_position(); 
		
		//눈알의 위치 초기화
		init_eyeball_position();
		
		//마우스 이벤트 초기화
		init_mouse_event();
		
		//위치		 x	  y
		this.setLocation(200, 100);

		//크기
		//this.setSize(400, 400);
		//자식윈도우(그림판)를 감싸서 만들겠다.
		pack();
		setResizable(false);

		//보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		//종료							// JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init_mouse_event() {
		// TODO Auto-generated method stub
		//추상클래스 -> 객체를 못만들지만 객체 생성과 동시에 상속을 받아서 가능하다. 익명객체
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//super.mousePressed(e);
				//눌린 버튼
				/*
				int button = e.getButton();
				if(button == MouseEvent.BUTTON1) {
					System.out.println("왼쪽 클릭");
				}
				 */
				
				//눌린 좌표
				Point pt = e.getPoint();
				
				move_eyeball_left_position(pt);	//왼쪽 눈알 위치 구하기
				move_eyeball_right_position(pt);	//오른쪽 눈알 위치 구하기
				
				grimPan.repaint();
			
			
			}

			private void move_eyeball_right_position(Point mousePt) {
				// TODO Auto-generated method stub
				int x = mousePt.x - eye_right.x;	//밑변
				int y = mousePt.y - eye_right.y; //높이
				//빗변의 길이
				double r = Math.sqrt(x*x + y*y);
				
				double rate = eyeball_radius / r;
				//		경제적 연산 수행 앞이 참이므로 뒤는 할 필요 없음
				bEyeInPt = bEyeInPt || (r < eye_radius);
				
				//눈알의 좌표
				eyeball_right.x = (int) (eye_right.x + rate*x);
				eyeball_right.y = (int) (eye_right.y + rate*y);
			}

			private void move_eyeball_left_position(Point mousePt) {
				// TODO Auto-generated method stub
				int x = mousePt.x - eye_left.x;	//밑변
				int y = mousePt.y - eye_left.y; //높이
				//빗변의 길이
				double r = Math.sqrt(x*x + y*y);
				
				bEyeInPt = (r < eye_radius);
				
				
				double rate = eyeball_radius / r;
				
				//눈알의 좌표
				eyeball_left.x = (int) (eye_left.x + rate*x);
				eyeball_left.y = (int) (eye_left.y + rate*y);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				init_eyeball_position();
				grimPan.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				
				//움직인 좌표
				Point pt = e.getPoint();
				
				move_eyeball_left_position(pt);	//왼쪽 눈알 위치 구하기
				move_eyeball_right_position(pt);	//오른쪽 눈알 위치 구하기
				
				grimPan.repaint();
			
			}
			
		}; 
		//그림판에 마우스 이벤트 설정 
		grimPan.addMouseListener(adapter);		//mousePressed mouseReleased 
		grimPan.addMouseMotionListener(adapter);//mouseDragged
	}

	private void init_eyeball_position() {
		// TODO Auto-generated method stub
		//눈의 중심 좌표와 동일하게 
		eyeball_left.x = eye_left.x;
		eyeball_left.y = eye_left.y;
		
		eyeball_right.x = eye_right.x;
		eyeball_right.y = eye_right.y;
		
		//눈알의 반지름	= 눈알 반지름 /2
		eyeball_radius = eye_radius/2;
	}

	private void init_eye_position() {
		// TODO Auto-generated method stub
		//왼쪽눈 초기화
		eye_left.x = 122; //GRIMPAN_WIDTH/4;
		eye_left.y = 124; //GRIMPAN_HEIGHT/2;
		
		//오른쪽눈 초기화
		eye_right.x = 296; //GRIMPAN_WIDTH/4 * 3;
		eye_right.y = 128; // GRIMPAN_HEIGHT/2;
		
		//눈알 반지름 
		//eye_radius = GRIMPAN_WIDTH/4 - 30;
		eye_radius = 50;
	}

	private void init_grimPan() {
		// TODO Auto-generated method stub
		grimPan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				//super.paintComponent(g);
				
				//이전에 그렸던 내용을 지우고 해야 잔상이 남지 않는다. 
				//g.clearRect(0, 0, GRIMPAN_WIDTH, GRIMPAN_HEIGHT);

				//배경이미지를 먼저 그려지다 보니 이전에 그린 내용이 지워짐
				g.drawImage(back_img, 0, 0, this);
				
				//눈을 감았으면
				if(bEyeInPt) {
					g.drawImage(back_off_img, 0, 0, this);
					return;
				} 
				
				
				/*
				//눈그리기 
				//왼쪽
				g.drawOval(eye_left.x-eye_radius, eye_left.y-eye_radius, eye_radius*2, eye_radius*2);
				
				//오른쪽
				g.drawOval(eye_right.x-eye_radius, eye_right.y-eye_radius, eye_radius*2, eye_radius*2);
				*/
				
				g.setColor(Color.blue);
				//눈알 그리기
				//왼쪽
				g.fillOval(eyeball_left.x - eyeball_radius, eyeball_left.y - eyeball_radius, eyeball_radius*2, eyeball_radius*2);
				//오른쪽
				g.fillOval(eyeball_right.x - eyeball_radius, eyeball_right.y - eyeball_radius, eyeball_radius*2, eyeball_radius*2);
			
				

			}
		};
		
		grimPan.setPreferredSize(new Dimension(GRIMPAN_WIDTH,GRIMPAN_HEIGHT));
		this.add(grimPan); //기본 borderlayout에서 위치 지정 안하면 center로 간다.
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new _02_EyeBallDraw();
	}

}
