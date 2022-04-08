package mymain.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame; // �������۷���
import javax.swing.JPanel;
import javax.swing.Timer;

import myutil.MyConstant;

public class ��������_Mine extends JFrame {
	
	JPanel gamePan;
	Timer timer = null;
	
	//��
	Bar bar = new Bar(); 
	
	//��
	Ball ball = new Ball();
	
	//��������
	Block [][] block_array = new Block[6][6]; 
	
	public ��������_Mine() {
		// TODO Auto-generated constructor stub
		super("�����������");
		
		//������ �ʱ�ȭ
		init_gamepan();
		
		//timer�ʱ�ȭ 
		init_timer();
		
		//Ű�̺�Ʈ �ʱ�ȭ 
		init_key_event();
		
		//bar �ʱ�ȭ
		init_bar();
		
		//ball �ʱ�ȭ
		init_ball();
		
		//block �ʱ�ȭ
		init_block();
		
		//�浹(bar & ball)
		crash_bar_ball();
		
		//�浹(bar & block)
		crash_ball_block();
		
		//��ġ		 x	  y
		this.setLocation(200, 100);

		//ũ��
		//this.setSize(400, 400);
		pack();
		setResizable(false);

		//�����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		//����							// JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}

	private void crash_ball_block() {
		// TODO Auto-generated method stub
		int minX, maxX, minY, maxY;
		int distX, distY;
		for(int i=0; i < block_array.length;i++) {
			for(int k =0; k < block_array[i].length; k++) {
				if(block_array[i][k].bShow) {
					minX = block_array[i][k].pos.x - ball.radius; //���� �浹
					maxX = block_array[i][k].pos.x + block_array[i][k].width + ball.radius; //������ �浹
					minY = block_array[i][k].pos.y - ball.radius; //�� �� �浹
					maxY = block_array[i][k].pos.y + block_array[i][k].height + ball.radius; //�Ʒ��� �浹
					
					//�ش� ��ϰ� �浹���� �ľ�
					if(ball.pos.x >= minX && ball.pos.x <= maxX && ball.pos.y >= minY && ball.pos.y <= maxY) {
						block_array[i][k].bShow = false;
						
						//�浹 ���� �ľ� 
						distX = Math.min(ball.pos.x - minX, ball.pos.x -maxX);
						distY = Math.min(ball.pos.y - minY, ball.pos.y -maxY);
						
						//�¿� �浹
						if(distX >= distY) {
							ball.bRight = !ball.bRight;
						}else { //���Ʒ� �浹
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
		
		for(int i=0; i<block_array.length;i++) {			//�� ÷��
			for(int j=0; j<block_array[i].length;j++) {		//�� ÷��
				Block block = new Block();
				block.pos.x = j*block_w;
				block.pos.y = i*block_h;
				block.width = block_w;
				block.height = block_h;
				block.color = color_array[(i+j)%color_array.length];
				
				//if((i==0) || (j==0)) block.bShow = false;
				//if((i+j)%2 ==0 ) block.bShow = false;
				
				//�迭�� ä���
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
					//���ӽ��� 
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
		//�� �����̱�
		ball.move();
		//�� �����̱�
		bar.move();
		//�浹 üũ�ϱ�
		//1. �ٶ� ���̶� �浹
		crash_bar_ball();
		//2. ���̶� ����̶� �浹
		crash_ball_block();
		//����
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
				
				//��� �׸��� 
				draw_block(g);
				
				//�ٱ׸���
				bar.draw(g);
				
				//�� �׸��� 
				ball.draw(g);
				
				
				
			}	
		};
		
		//ũ�� ����
		gamePan.setPreferredSize(new Dimension(MyConstant.BlockGame.GAMEPAN_WIDTH, MyConstant.BlockGame.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	protected void draw_block(Graphics g) {
		// TODO Auto-generated method stub
		for(int i=0; i<block_array.length; i++) {
			
			for(int k=0; k<block_array[i].length; k++) {
				Block block  = block_array[i][k];
				//�ȱ��� ����̸� �������
				if(block_array[i][k].bShow)
					block.draw(g);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new ��������_Mine();
	}

}