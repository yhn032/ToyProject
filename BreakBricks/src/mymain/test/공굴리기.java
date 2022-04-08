package mymain.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel; // 스윙컴퍼런스
import javax.swing.Timer;

import myutil.MyConstant;

public class 공굴리기 extends JFrame {

	JPanel gamePan = new JPanel();
	
	//공의 정보
	int ball_x;
	int ball_y;
	int ball_radius;
	boolean bRight = true;//우측방향이냐?
	boolean bDown  = false;//아래쪽이냐?
	
	Timer timer;
	
	public 공굴리기() {
		// TODO Auto-generated constructor stub
		super("공굴리기");
		
		//게임판 초기화 
		init_gamepan();
		
		//공의위치 초기화
		init_ball_position();
		
		//타이머 초기화
		init_timer();
		
		//키이벤트 초기화
		init_key_event();
		
		//위치		 x	  y
		this.setLocation(200, 100);

		//크기
		//this.setSize(400, 400);
		pack();
		setResizable(false);

		//보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		//종료							// JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	private void init_key_event() {
		// TODO Auto-generated method stub
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				
				int key = e.getKeyCode();
				
				if(key==KeyEvent.VK_LEFT) bRight = false;
				else if(key == KeyEvent.VK_RIGHT) bRight = true;
				else if(key == KeyEvent.VK_UP) bDown = false;
				else if(key == KeyEvent.VK_DOWN) bDown = true;
				else if(key == KeyEvent.VK_1) {
					ball_radius++;
				}
				else if(key == KeyEvent.VK_2) {
					ball_radius--;
				}
				
			}
		});
	}


	private void init_timer() {
		// TODO Auto-generated method stub
		//swing timer는 주기적으로 ActionListener을 준비한 객체를 호출한다.
		ActionListener timer_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//변화값 설정 
				process();
				
				gamePan.repaint();
			}
		};
		
		
		//				1/1000s 초간격으로 actionperformed를 호출한다.
		timer = new Timer(10, timer_listener);		//100FPS(Frame per sec) 초당 100번 호출한다.
		//timer 구동
		timer.start();
	}


	protected void process() {
		// TODO Auto-generated method stub
		//공 움직이기
		move_ball();
	}

	int speed = 5;
	private void move_ball() {
		// TODO Auto-generated method stub
		
		//좌우방향
		if(bRight) 
			ball_x += speed;
		else
			ball_x -= speed;
		
		//상하방향 
		if(bDown)
			ball_y += speed;
		else
			ball_y -= speed;
		
		//오른쪽 벽에 닿았냐?
		//공이 그려지는 과정을 이해해야 조건식이 이해된다
		if((ball_x + ball_radius) > MyConstant.MoveBall.GAMEPAN_WIDTH) {
			ball_x = MyConstant.MoveBall.GAMEPAN_WIDTH - ball_radius;
			bRight = false;
		} else if ((ball_x - ball_radius) < 0) { //왼쪽벽에 닿았냐?
			ball_x = ball_radius;
			bRight = true;
		}
		
		//아래쪽 벽에 닿았냐?
		if((ball_y + ball_radius) > MyConstant.MoveBall.GAMEPAN_HEIGHT) {
			ball_y = MyConstant.MoveBall.GAMEPAN_HEIGHT - ball_radius;
			bDown = false;
		} else if ((ball_y - ball_radius) < 0) { //위쪽벽에 닿았냐?
			ball_y = ball_radius;
			bDown = true;
		}
		
	}


	private void init_ball_position() {
		// TODO Auto-generated method stub
		ball_radius = 15;
		ball_x = MyConstant.MoveBall.GAMEPAN_WIDTH/2;
		ball_y = MyConstant.MoveBall.GAMEPAN_HEIGHT/2;
	}


	private void init_gamepan() {
		// TODO Auto-generated method stub
		gamePan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				//공그리기 
				g.setColor(Color.red);
				//				x						y				width		height
				g.fillOval(ball_x-ball_radius, ball_y-ball_radius, ball_radius*2, ball_radius*2);
			}
		};
		
		
		
		//크기 예약
		gamePan.setPreferredSize(new Dimension(MyConstant.MoveBall.GAMEPAN_WIDTH, MyConstant.MoveBall.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new 공굴리기();
	}

}