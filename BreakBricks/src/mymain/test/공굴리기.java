package mymain.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel; // �������۷���
import javax.swing.Timer;

import myutil.MyConstant;

public class �������� extends JFrame {

	JPanel gamePan = new JPanel();
	
	//���� ����
	int ball_x;
	int ball_y;
	int ball_radius;
	boolean bRight = true;//���������̳�?
	boolean bDown  = false;//�Ʒ����̳�?
	
	Timer timer;
	
	public ��������() {
		// TODO Auto-generated constructor stub
		super("��������");
		
		//������ �ʱ�ȭ 
		init_gamepan();
		
		//������ġ �ʱ�ȭ
		init_ball_position();
		
		//Ÿ�̸� �ʱ�ȭ
		init_timer();
		
		//Ű�̺�Ʈ �ʱ�ȭ
		init_key_event();
		
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
		//swing timer�� �ֱ������� ActionListener�� �غ��� ��ü�� ȣ���Ѵ�.
		ActionListener timer_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//��ȭ�� ���� 
				process();
				
				gamePan.repaint();
			}
		};
		
		
		//				1/1000s �ʰ������� actionperformed�� ȣ���Ѵ�.
		timer = new Timer(10, timer_listener);		//100FPS(Frame per sec) �ʴ� 100�� ȣ���Ѵ�.
		//timer ����
		timer.start();
	}


	protected void process() {
		// TODO Auto-generated method stub
		//�� �����̱�
		move_ball();
	}

	int speed = 5;
	private void move_ball() {
		// TODO Auto-generated method stub
		
		//�¿����
		if(bRight) 
			ball_x += speed;
		else
			ball_x -= speed;
		
		//���Ϲ��� 
		if(bDown)
			ball_y += speed;
		else
			ball_y -= speed;
		
		//������ ���� ��ҳ�?
		//���� �׷����� ������ �����ؾ� ���ǽ��� ���صȴ�
		if((ball_x + ball_radius) > MyConstant.MoveBall.GAMEPAN_WIDTH) {
			ball_x = MyConstant.MoveBall.GAMEPAN_WIDTH - ball_radius;
			bRight = false;
		} else if ((ball_x - ball_radius) < 0) { //���ʺ��� ��ҳ�?
			ball_x = ball_radius;
			bRight = true;
		}
		
		//�Ʒ��� ���� ��ҳ�?
		if((ball_y + ball_radius) > MyConstant.MoveBall.GAMEPAN_HEIGHT) {
			ball_y = MyConstant.MoveBall.GAMEPAN_HEIGHT - ball_radius;
			bDown = false;
		} else if ((ball_y - ball_radius) < 0) { //���ʺ��� ��ҳ�?
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
				//���׸��� 
				g.setColor(Color.red);
				//				x						y				width		height
				g.fillOval(ball_x-ball_radius, ball_y-ball_radius, ball_radius*2, ball_radius*2);
			}
		};
		
		
		
		//ũ�� ����
		gamePan.setPreferredSize(new Dimension(MyConstant.MoveBall.GAMEPAN_WIDTH, MyConstant.MoveBall.GAMEPAN_HEIGHT));
		this.add(gamePan);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new ��������();
	}

}