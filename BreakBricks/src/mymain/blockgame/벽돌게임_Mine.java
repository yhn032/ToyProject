package mymain.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame; // 스윙컴퍼런스
import javax.swing.JPanel;
import javax.swing.Timer;

import myutil.MyConstant;

public class 벽돌게임_Mine extends JFrame {
	
	JPanel gamePan;
	Timer timer = null;
	
	//바
	Bar bar = new Bar(); 
	
	//볼
	Ball ball = new Ball();
	
	//벽돌생성
	Block [][] block_array = new Block[6][6]; 
	
	public 벽돌게임_Mine() {
		// TODO Auto-generated constructor stub
		super("벽돌깨기게임");
		
		//게임판 초기화
		init_gamepan();
		
		//timer초기화 
		init_timer();
		
		//키이벤트 초기화 
		init_key_event();
		
		//bar 초기화
		init_bar();
		
		//ball 초기화
		init_ball();
		
		//block 초기화
		init_block();
		
		//충돌(bar & ball)
		crash_bar_ball();
		
		//충돌(bar & block)
		crash_ball_block();
		
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

	private void crash_ball_block() {
		// TODO Auto-generated method stub
		int minX, maxX, minY, maxY;
		int distX, distY;
		for(int i=0; i < block_array.length;i++) {
			for(int k =0; k < block_array[i].length; k++) {
				if(block_array[i][k].bShow) {
					minX = block_array[i][k].pos.x - ball.radius; //왼쪽 충돌
					maxX = block_array[i][k].pos.x + block_array[i][k].width + ball.radius; //오른쪽 충돌
					minY = block_array[i][k].pos.y - ball.radius; //위 쪽 충돌
					maxY = block_array[i][k].pos.y + block_array[i][k].height + ball.radius; //아래쪽 충돌
					
					//해당 블록과 충돌여부 파악
					if(ball.pos.x >= minX && ball.pos.x <= maxX && ball.pos.y >= minY && ball.pos.y <= maxY) {
						block_array[i][k].bShow = false;
						
						//충돌 방향 파악 
						distX = Math.min(ball.pos.x - minX, ball.pos.x -maxX);
						distY = Math.min(ball.pos.y - minY, ball.pos.y -maxY);
						
						//좌우 충돌
						if(distX >= distY) {
							ball.bRight = !ball.bRight;
						}else { //위아래 충돌
							ball.bDown = !ball.bDown;
							
						}
					}
				}
				 
							
			}
		}
	}

	private void crash_bar_ball() {
		// TODO Auto-generated method stub
		int minX, minY, maxX;
		minX = bar.pos.x - ball.radius;
		maxX = bar.pos.x + bar.width + ball.radius;
		minY = bar.pos.y - ball.radius;
		
		if(ball.pos.x >= minX && ball.pos.x <= maxX && ball.pos.y > minY) {
			ball.bDown = !ball.bDown;
		}
		
		
	}

	private void init_block() {
		// TODO Auto-generated method stub
		int block_w = MyConstant.BlockGame.GAMEPAN_WIDTH/block_array[0].length;
		int block_h = 30;
		
		Color [] color_array = {Color.red, Color.green, Color.blue, Color.yellow, Color.black};
		
		for(int i=0; i<block_array.length;i++) {			//행 첨자
			for(int j=0; j<block_array[i].length;j++) {		//열 첨자
				Block block = new Block();
				block.pos.x = j*block_w;
				block.pos.y = i*block_h;
				block.width = block_w;
				block.height = block_h;
				block.color = color_array[(i+j)%color_array.length];
				
				//if((i==0) || (j==0)) block.bShow = false;
				//if((i+j)%2 ==0 ) block.bShow = false;
				
				//배열에 채우기
				block_array[i][j] = block;
				
				
			}
		}
	}

	private void init_ball() {
		// TODO Auto-generated method stub
		ball.pos.x = bar.pos.x + bar.width/2; //MyConstant.BlockGame.GAMEPAN_WIDTH/2;
		ball.pos.y = bar.pos.y - ball.radius;
	}

	private void init_bar() {
		// TODO Auto-generated method stub
		bar.color = Color.blue;
		
		bar.width  = 130;
		bar.height = 30;
		
		bar.pos.x = MyConstant.BlockGame.GAMEPAN_WIDTH/2  - bar.width/2;
		bar.pos.y = MyConstant.BlockGame.GAMEPAN_HEIGHT - bar.height-5;
		
		
	}

	private void init_key_event() {
		// TODO Auto-generated method stub
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//super.keyPressed(e);
				
				int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_S);{
					//게임시작 
					timer.start();
				}
				if(key == KeyEvent.VK_LEFT) {
					bar.key_state = bar.key_state | MyConstant.Key.LEFT;
				} else if(key == KeyEvent.VK_RIGHT) {
					bar.key_state = bar.key_state | MyConstant.Key.RIGHT;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//super.keyReleased(e);
				
				int key = e.getKeyCode();

				if(key == KeyEvent.VK_LEFT) {
					bar.key_state = bar.key_state ^ MyConstant.Key.LEFT;
				} else if(key == KeyEvent.VK_RIGHT) {
					bar.key_state = bar.key_state ^ MyConstant.Key.RIGHT;
				}
			}
		});
	}

	private void init_timer() {
		// TODO Auto-generated method stub
		ActionListener timer_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				process();

				gamePan.repaint();
			}
		};
		
		timer = new Timer(10, timer_listener);
	}

	protected void process() {
		// TODO Auto-generated method stub
		//공 움직이기
		ball.move();
		//바 움직이기
		bar.move();
		//충돌 체크하기
		//1. 바랑 공이랑 충돌
		crash_bar_ball();
		//2. 공이랑 블록이랑 충돌
		crash_ball_block();
		//종료
		end();
	}

	private void end() {
		// TODO Auto-generated method stub
		if((ball.pos.y + ball.radius) > MyConstant.BlockGame.GAMEPAN_HEIGHT){
			timer.stop();
		}
	}

	private void init_gamepan() {
		// TODO Auto-generated method stub
		gamePan = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				
				//블록 그리기 
				draw_block(g);
				
				//바그리기
				bar.draw(g);
				
				//공 그리기 
				ball.draw(g);
				
				
				
			}	
		};
		
		//크기 예약
		gamePan.setPreferredSize(new Dimension(MyConstant.BlockGame.GAMEPAN_WIDTH, MyConstant.BlockGame.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	protected void draw_block(Graphics g) {
		// TODO Auto-generated method stub
		for(int i=0; i<block_array.length; i++) {
			
			for(int k=0; k<block_array[i].length; k++) {
				Block block  = block_array[i][k];
				//안깨진 블록이면 보여줘라
				if(block_array[i][k].bShow)
					block.draw(g);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new 벽돌게임_Mine();
	}

}