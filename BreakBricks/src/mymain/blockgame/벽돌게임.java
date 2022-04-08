package mymain.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame; // �������۷���
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import myutil.MyConstant;

public class �������� extends JFrame {

	JPanel gamePan;
	Timer timer = null;

	// ��
	Bar bar = new Bar();

	// ��
	Ball ball = new Ball();

	// ��������
	Block[][] block_array = new Block[6][6];
	
	int block_count;
	
	//������ ī����
	int life_count = 3;
		
	Font font = new Font("����ü", Font.BOLD, 30);

	public ��������() {
		// TODO Auto-generated constructor stub
		super("�����������");
		
		block_count = block_array.length * block_array[0].length;
		
		// ������ �ʱ�ȭ
		init_gamepan();

		// timer�ʱ�ȭ
		init_timer();

		// Ű�̺�Ʈ �ʱ�ȭ
		init_key_event();

		// bar �ʱ�ȭ
		init_bar();

		// ball �ʱ�ȭ
		init_ball();

		// block �ʱ�ȭ
		init_block();

		// ��ġ x y
		this.setLocation(200, 100);

		// ũ��
		// this.setSize(400, 400);
		pack();
		setResizable(false);

		// �����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		// ���� // JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init_block() {
		// TODO Auto-generated method stub
		int block_w = MyConstant.BlockGame.GAMEPAN_WIDTH / block_array[0].length;
		int block_h = 30;

		Block.width = block_w;
		Block.height = block_h;

		Color[] color_array = { Color.red, Color.green, Color.blue, Color.yellow, Color.black };

		for (int i = 0; i < block_array.length; i++) { // �� ÷��
			for (int j = 0; j < block_array[i].length; j++) { // �� ÷��
				Block block = new Block();
				block.pos.x = j * block_w;
				block.pos.y = i * block_h;
				//block.width = block_w;
				//block.height = block_h;
				block.color = color_array[(i + j) % color_array.length];

				// if((i==0) || (j==0)) block.bShow = false;
				// if((i+j)%2 ==0 ) block.bShow = false;

				// �迭�� ä���
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
					// ���ӽ���
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
		// �� �����̱�
		ball.move();
		// �� �����̱�
		bar.move();

		// �浹 üũ�ϱ�

		// 1. �ٶ� ���̶� �浹
		collision_bar_ball();
		// 2. ���̶� ����̶� �浹
		collision_ball_block();
		// ����
	}


	private void collision_ball_block() {
		// TODO Auto-generated method stub
			
		// ���� �ö� ��
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
		// ���� �浹 ��ǥ�� ���Ѵ�.
		int cx = ball.pos.x + ball.radius;
		int cy = ball.pos.y;

		int block_total_height = block_array.length * Block.height;
		
		//���� ���� ��ġ�� ��� ���� �Ʒ��ʿ� ��ġ�Ѵ�.
		if(cy > block_total_height) return;
		
		//���� �浹 ������ ��ǥ�� �̿��ؼ� ����� ÷�ڸ� ���Ѵ�. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//÷�� ������ ������� ������
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//�̹� ���������� ������..
		if(block_array[row][col].bShow == false) return;
		
		//�� ���� ������ ���� ���·� ����
		block_array[row][col].bShow = false;
		
		//���� ���� ��Ȳ ����(�� -> ��)
		ball.bRight = false;
		
		//���� �������� ��� ����?
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
		// ���� �浹 ��ǥ�� ���Ѵ�.
		int cx = ball.pos.x - ball.radius;
		int cy = ball.pos.y;
	
		int block_total_height = block_array.length * Block.height;
		
		//���� ���� ��ġ�� ��� ���� �Ʒ��ʿ� ��ġ�Ѵ�.
		if(cy > block_total_height) return;
		
		//���� �浹 ������ ��ǥ�� �̿��ؼ� ����� ÷�ڸ� ���Ѵ�. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//÷�� ������ ������� ������
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//�̹� ���������� ������..
		if(block_array[row][col].bShow == false) return;
		
		//�� ���� ������ ���� ���·� ����
		block_array[row][col].bShow = false;
		
		//���� ���� ��Ȳ ����(�� -> ��)
		ball.bRight = true;
		
		//���� �������� ��� ����?
		check_stage_clear();
	}

	private void collision_ball_up_block() {
		// TODO Auto-generated method stub
		// ���� �浹 ��ǥ�� ���Ѵ�.
		int cx = ball.pos.x;
		int cy = ball.pos.y - ball.radius;

		int block_total_height = block_array.length * Block.height;
		
		//���� ���� ��ġ�� ��� ���� �Ʒ��ʿ� ��ġ�Ѵ�.
		if(cy > block_total_height) return;
		
		//���� �浹 ������ ��ǥ�� �̿��ؼ� ����� ÷�ڸ� ���Ѵ�. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//÷�� ������ ������� ������
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//�̹� ���������� ������..
		if(block_array[row][col].bShow == false) return;
		
		//�� ���� ������ ���� ���·� ����
		block_array[row][col].bShow = false;
		
		//���� ���� ��Ȳ ����(�� -> �Ʒ�)
		ball.bDown = true;
		
		//���� �������� ��� ����?
		check_stage_clear();
	}

	private void collision_ball_down_block() {
		// TODO Auto-generated method stub
		// ���� �浹 ��ǥ�� ���Ѵ�.
		//�Ʒ��� ������ ��
		int cx = ball.pos.x;
		int cy = ball.pos.y + ball.radius;

		int block_total_height = block_array.length * Block.height;
		
		//���� ���� ��ġ�� ��� ���� �Ʒ��ʿ� ��ġ�Ѵ�.
		if(cy > block_total_height) return;
		
		//���� �浹 ������ ��ǥ�� �̿��ؼ� ����� ÷�ڸ� ���Ѵ�. 
		int row = cy / Block.height;
		int col = cx / Block.width;
		
		//÷�� ������ ������� ������
		if(col < 0 || col >= block_array[0].length) return;
		if(row < 0 || row >= block_array.length) return;
		
		//�̹� ���������� ������..
		if(block_array[row][col].bShow == false) return;
		
		//�� ���� ������ ���� ���·� ����
		block_array[row][col].bShow = false;
		
		//���� ���� ��Ȳ ����(�Ʒ� -> ��)
		ball.bDown = false;
		
		//���� �������� ��� ����?
		check_stage_clear();
	}

	private void collision_bar_ball() {
		// TODO Auto-generated method stub
		// ���� �ٿ� �浹�Ǿ��� ��
		if (ball.pos.x >= bar.pos.x && ball.pos.x <= (bar.pos.x + bar.width)
				&& (ball.pos.y + ball.radius) >= bar.pos.y) {
			ball.pos.y = bar.pos.y - ball.radius;
			ball.bDown = false;
		}
		// �ٴڿ� ���� ��
		if ((ball.pos.y + ball.radius) >= MyConstant.BlockGame.GAMEPAN_HEIGHT) {
			
			life_count--;
			init_bar();
			init_ball();
			gamePan.repaint();

			timer.stop();
			
			if(life_count == 0)
				//���� ����
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

				// ��� �׸���
				draw_block(g);

				// �ٱ׸���
				bar.draw(g);

				// �� �׸���
				ball.draw(g);
				
				//������ī��Ʈ
				draw_lifeCount(g);
			}
		};

		// ũ�� ����
		gamePan.setPreferredSize(
				new Dimension(MyConstant.BlockGame.GAMEPAN_WIDTH, MyConstant.BlockGame.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	protected void draw_lifeCount(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.setFont(font);
		for(int i=0; i<life_count; i++) {
			g.drawString("��", i * 30, MyConstant.BlockGame.GAMEPAN_HEIGHT-5);
			
		}
	}

	protected void draw_block(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < block_array.length; i++) {

			for (int k = 0; k < block_array[i].length; k++) {
				Block block = block_array[i][k];
				// �ȱ��� ����̸� �������
				if (block_array[i][k].bShow)
					block.draw(g);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new ��������();
	}

}