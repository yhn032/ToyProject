package mymain.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame; // 스윙컴퍼런스
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import myutil.MyConstant;

public class 벽돌게임 extends JFrame {

	JPanel gamePan;
	Timer timer = null;

	// 바
	Bar bar = new Bar();

	// 볼
	Ball ball = new Ball();

	// 벽돌생성
	Block[][] block_array = new Block[6][6];
	
	int block_count;
	
	//라이프 카운팅
	int life_count = 3;
		
	Font font = new Font("굴림체", Font.BOLD, 30);

	public 벽돌게임() {
		// TODO Auto-generated constructor stub
		super("벽돌깨기게임");
		
		block_count = block_array.length * block_array[0].length;
		
		// 게임판 초기화
		init_gamepan();

		// timer초기화
		init_timer();

		// 키이벤트 초기화
		init_key_event();

		// bar 초기화
		init_bar();

		// ball 초기화
		init_ball();

		// block 초기화
		init_block();

		// 위치 x y
		this.setLocation(200, 100);

		// 크기
		// this.setSize(400, 400);
		pack();
		setResizable(false);

		// 보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		// 종료 // JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init_block() {
		// TODO Auto-generated method stub
		int block_w = MyConstant.BlockGame.GAMEPAN_WIDTH / block_array[0].length;
		int block_h = 30;

		Block.width = block_w;
		Block.height = block_h;

		Color[] color_array = { Color.red, Color.green, Color.blue, Color.yellow, Color.black };

		for (int i = 0; i < block_array.length; i++) { // 행 첨자
			for (int j = 0; j < block_array[i].length; j++) { // 열 첨자
				Block block = new Block();
				block.pos.x = j * block_w;
				block.pos.y = i * block_h;
				//block.width = block_w;
				//block.height = block_h;
				block.color = color_array[(i + j) % color_array.length];

				// if((i==0) || (j==0)) block.bShow = false;
				// if((i+j)%2 ==0 ) block.bShow = false;

				// 배열에 채우기
				block_array[i][j] = block;

			}
		}
	}

	private void init_ball() {
		// TODO Auto-generated method stub
		ball.pos.x = bar.pos.x + bar.width / 2; // MyConstant.BlockGame.GAMEPAN_WIDTH/2;
		ball.pos.y = bar.pos.y - ball.radius;
	}

	private void init_bar() {
		// TODO Auto-generated method stub
		bar.color = Color.blue;

		bar.width = 600;
		bar.height = 30;

		bar.pos.x = MyConstant.BlockGame.GAMEPAN_WIDTH / 2 - bar.width / 2;
		bar.pos.y = MyConstant.BlockGame.GAMEPAN_HEIGHT - bar.height - 5;

	}

	private void init_key_event() {
		// TODO Auto-generated method stub
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// super.keyPressed(e);

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_S)
					;
				{
					// 게임시작
					timer.start();
				}
				if (key == KeyEvent.VK_LEFT) {
					bar.key_state = bar.key_state | MyConstant.Key.LEFT;
				} else if (key == KeyEvent.VK_RIGHT) {
					bar.key_state = bar.key_state | MyConstant.Key.RIGHT;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				// super.keyReleased(e);

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_LEFT) {
					bar.key_state = bar.key_state ^ MyConstant.Key.LEFT;
				} else if (key == KeyEvent.VK_RIGHT) {
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
		// 공 움직이기
		ball.move();
		// 바 움직이기
		bar.move();

		// 충돌 체크하기

		// 1. 바랑 공이랑 충돌
		collision_bar_ball();
		// 2. 공이랑 블록이랑 충돌
		collision_ball_block();
		// 종료
	}


	private void collision_ball_block() {
		// TODO Auto-generated method stub
			
		// 공이 올라갈 때
		if (ball.bDown == false)
			collision_ball_up_block();
		else if (ball.bDown == true) {
			collision_ball_down_block();
		}
		
		if(ball.bRight)
			collision_ball_right();
		else
			collision_ball_left();
	}

	private void collision_ball_left() {
		// TODO Auto-generated method stub
		// 공의 충돌 좌표를 구한다.
		int cx = ball.pos.x + ball.radius;
		int cy = ball.pos.y;

		int block_total_height = block_array.length * Block.height;
		
		//현재 공의 위치가 블록 영역 아래쪽에 위치한다.
		if(cy > block_total_height) return;
		
		//현재 충돌 지점의 좌표를 이용해서 블록의 첨자를 구한다. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//첨자 범위를 벗어났으면 끝내라
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//이미 깨져있으면 끝내라..
		if(block_array[row][col].bShow == false) return;
		
		//안 깨져 있으면 깨진 상태로 변경
		block_array[row][col].bShow = false;
		
		//공의 진행 상황 변경(우 -> 좌)
		ball.bRight = false;
		
		//현재 스테이지 모두 깼나?
		check_stage_clear();
	}

	private void check_stage_clear() {
		// TODO Auto-generated method stub
		block_count--;

		if(block_count == 0) {
			init_ball();
			init_bar();
			
			gamePan.repaint();
			
			
			JOptionPane.showMessageDialog(this, "Stage Clear!!");
		}
			
	}

	private void collision_ball_right() {
		// TODO Auto-generated method stub
		// 공의 충돌 좌표를 구한다.
		int cx = ball.pos.x - ball.radius;
		int cy = ball.pos.y;
	
		int block_total_height = block_array.length * Block.height;
		
		//현재 공의 위치가 블록 영역 아래쪽에 위치한다.
		if(cy > block_total_height) return;
		
		//현재 충돌 지점의 좌표를 이용해서 블록의 첨자를 구한다. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//첨자 범위를 벗어났으면 끝내라
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//이미 깨져있으면 끝내라..
		if(block_array[row][col].bShow == false) return;
		
		//안 깨져 있으면 깨진 상태로 변경
		block_array[row][col].bShow = false;
		
		//공의 진행 상황 변경(좌 -> 우)
		ball.bRight = true;
		
		//현재 스테이지 모두 깼나?
		check_stage_clear();
	}

	private void collision_ball_up_block() {
		// TODO Auto-generated method stub
		// 공의 충돌 좌표를 구한다.
		int cx = ball.pos.x;
		int cy = ball.pos.y - ball.radius;

		int block_total_height = block_array.length * Block.height;
		
		//현재 공의 위치가 블록 영역 아래쪽에 위치한다.
		if(cy > block_total_height) return;
		
		//현재 충돌 지점의 좌표를 이용해서 블록의 첨자를 구한다. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//첨자 범위를 벗어났으면 끝내라
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//이미 깨져있으면 끝내라..
		if(block_array[row][col].bShow == false) return;
		
		//안 깨져 있으면 깨진 상태로 변경
		block_array[row][col].bShow = false;
		
		//공의 진행 상황 변경(위 -> 아래)
		ball.bDown = true;
		
		//현재 스테이지 모두 깼나?
		check_stage_clear();
	}

	private void collision_ball_down_block() {
		// TODO Auto-generated method stub
		// 공의 충돌 좌표를 구한다.
		//아래로 내려올 때
		int cx = ball.pos.x;
		int cy = ball.pos.y + ball.radius;

		int block_total_height = block_array.length * Block.height;
		
		//현재 공의 위치가 블록 영역 아래쪽에 위치한다.
		if(cy > block_total_height) return;
		
		//현재 충돌 지점의 좌표를 이용해서 블록의 첨자를 구한다. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//첨자 범위를 벗어났으면 끝내라
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//이미 깨져있으면 끝내라..
		if(block_array[row][col].bShow == false) return;
		
		//안 깨져 있으면 깨진 상태로 변경
		block_array[row][col].bShow = false;
		
		//공의 진행 상황 변경(아래 -> 위)
		ball.bDown = false;
		
		//현재 스테이지 모두 깼나?
		check_stage_clear();
	}

	private void collision_bar_ball() {
		// TODO Auto-generated method stub
		// 공이 바에 충돌되었을 때
		if (ball.pos.x >= bar.pos.x && ball.pos.x <= (bar.pos.x + bar.width)
				&& (ball.pos.y + ball.radius) >= bar.pos.y) {
			ball.pos.y = bar.pos.y - ball.radius;
			ball.bDown = false;
		}
		// 바닥에 닿을 때
		if ((ball.pos.y + ball.radius) >= MyConstant.BlockGame.GAMEPAN_HEIGHT) {
			
			life_count--;
			init_bar();
			init_ball();
			gamePan.repaint();

			timer.stop();
			
			if(life_count == 0)
				//게임 종료
				JOptionPane.showMessageDialog(this, "Game over!!");
		}
	}

	private void init_gamepan() {
		// TODO Auto-generated method stub
		gamePan = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);

				// 블록 그리기
				draw_block(g);

				// 바그리기
				bar.draw(g);

				// 공 그리기
				ball.draw(g);
				
				//라이프카운트
				draw_lifeCount(g);
			}
		};

		// 크기 예약
		gamePan.setPreferredSize(
				new Dimension(MyConstant.BlockGame.GAMEPAN_WIDTH, MyConstant.BlockGame.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	protected void draw_lifeCount(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.setFont(font);
		for(int i=0; i<life_count; i++) {
			g.drawString("♥", i * 30, MyConstant.BlockGame.GAMEPAN_HEIGHT-5);
			
		}
	}

	protected void draw_block(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < block_array.length; i++) {

			for (int k = 0; k < block_array[i].length; k++) {
				Block block = block_array[i][k];
				// 안깨진 블록이면 보여줘라
				if (block_array[i][k].bShow)
					block.draw(g);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new 벽돌게임();
	}

}