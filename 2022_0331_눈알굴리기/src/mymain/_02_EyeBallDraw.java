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
import javax.swing.JPanel; // �������۷���

public class _02_EyeBallDraw extends JFrame {
	
	//�׸����� ���� ���̸� ������ ���
	public static final int GRIMPAN_WIDTH  = 400;
	public static final int GRIMPAN_HEIGHT = 630;
	
	static Image back_img, back_off_img;
	static {
		back_img = Toolkit.getDefaultToolkit().getImage("���η�.png");
		back_off_img = Toolkit.getDefaultToolkit().getImage("���η�off.png");
		
	}
	
	JPanel grimPan;
	
	//���� ��ǥ
	Point eye_left  = new Point();
	Point eye_right = new Point();
	int eye_radius;
	
	//���� ��ǥ
	Point eyeball_left = new Point();
	Point eyeball_right = new Point();
	int eyeball_radius;
	
	//�� �ȿ� �����Ͱ� ���Գ�?
	//���콺 �����Ͱ� �� �ȿ� ���ͼ� true�� �Ǹ� �̹����� �ٲپ� �־��
	boolean bEyeInPt = false;
	
	public _02_EyeBallDraw() {
		// TODO Auto-generated constructor stub
		super("���� �׸���");
		
		//�׸��� �ʱ�ȭ
		init_grimPan();
		
		//���� ��ġ �ʱ�ȭ
		init_eye_position(); 
		
		//������ ��ġ �ʱ�ȭ
		init_eyeball_position();
		
		//���콺 �̺�Ʈ �ʱ�ȭ
		init_mouse_event();
		
		//��ġ		 x	  y
		this.setLocation(200, 100);

		//ũ��
		//this.setSize(400, 400);
		//�ڽ�������(�׸���)�� ���μ� ����ڴ�.
		pack();
		setResizable(false);

		//�����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		//����							// JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init_mouse_event() {
		// TODO Auto-generated method stub
		//�߻�Ŭ���� -> ��ü�� ���������� ��ü ������ ���ÿ� ����� �޾Ƽ� �����ϴ�. �͸�ü
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//super.mousePressed(e);
				//���� ��ư
				/*
				int button = e.getButton();
				if(button == MouseEvent.BUTTON1) {
					System.out.println("���� Ŭ��");
				}
				 */
				
				//���� ��ǥ
				Point pt = e.getPoint();
				
				move_eyeball_left_position(pt);	//���� ���� ��ġ ���ϱ�
				move_eyeball_right_position(pt);	//������ ���� ��ġ ���ϱ�
				
				grimPan.repaint();
			
			
			}

			private void move_eyeball_right_position(Point mousePt) {
				// TODO Auto-generated method stub
				int x = mousePt.x - eye_right.x;	//�غ�
				int y = mousePt.y - eye_right.y; //����
				//������ ����
				double r = Math.sqrt(x*x + y*y);
				
				double rate = eyeball_radius / r;
				//		������ ���� ���� ���� ���̹Ƿ� �ڴ� �� �ʿ� ����
				bEyeInPt = bEyeInPt || (r < eye_radius);
				
				//������ ��ǥ
				eyeball_right.x = (int) (eye_right.x + rate*x);
				eyeball_right.y = (int) (eye_right.y + rate*y);
			}

			private void move_eyeball_left_position(Point mousePt) {
				// TODO Auto-generated method stub
				int x = mousePt.x - eye_left.x;	//�غ�
				int y = mousePt.y - eye_left.y; //����
				//������ ����
				double r = Math.sqrt(x*x + y*y);
				
				bEyeInPt = (r < eye_radius);
				
				
				double rate = eyeball_radius / r;
				
				//������ ��ǥ
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
				
				//������ ��ǥ
				Point pt = e.getPoint();
				
				move_eyeball_left_position(pt);	//���� ���� ��ġ ���ϱ�
				move_eyeball_right_position(pt);	//������ ���� ��ġ ���ϱ�
				
				grimPan.repaint();
			
			}
			
		}; 
		//�׸��ǿ� ���콺 �̺�Ʈ ���� 
		grimPan.addMouseListener(adapter);		//mousePressed mouseReleased 
		grimPan.addMouseMotionListener(adapter);//mouseDragged
	}

	private void init_eyeball_position() {
		// TODO Auto-generated method stub
		//���� �߽� ��ǥ�� �����ϰ� 
		eyeball_left.x = eye_left.x;
		eyeball_left.y = eye_left.y;
		
		eyeball_right.x = eye_right.x;
		eyeball_right.y = eye_right.y;
		
		//������ ������	= ���� ������ /2
		eyeball_radius = eye_radius/2;
	}

	private void init_eye_position() {
		// TODO Auto-generated method stub
		//���ʴ� �ʱ�ȭ
		eye_left.x = 122; //GRIMPAN_WIDTH/4;
		eye_left.y = 124; //GRIMPAN_HEIGHT/2;
		
		//�����ʴ� �ʱ�ȭ
		eye_right.x = 296; //GRIMPAN_WIDTH/4 * 3;
		eye_right.y = 128; // GRIMPAN_HEIGHT/2;
		
		//���� ������ 
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
				
				//������ �׷ȴ� ������ ����� �ؾ� �ܻ��� ���� �ʴ´�. 
				//g.clearRect(0, 0, GRIMPAN_WIDTH, GRIMPAN_HEIGHT);

				//����̹����� ���� �׷����� ���� ������ �׸� ������ ������
				g.drawImage(back_img, 0, 0, this);
				
				//���� ��������
				if(bEyeInPt) {
					g.drawImage(back_off_img, 0, 0, this);
					return;
				} 
				
				
				/*
				//���׸��� 
				//����
				g.drawOval(eye_left.x-eye_radius, eye_left.y-eye_radius, eye_radius*2, eye_radius*2);
				
				//������
				g.drawOval(eye_right.x-eye_radius, eye_right.y-eye_radius, eye_radius*2, eye_radius*2);
				*/
				
				g.setColor(Color.blue);
				//���� �׸���
				//����
				g.fillOval(eyeball_left.x - eyeball_radius, eyeball_left.y - eyeball_radius, eyeball_radius*2, eyeball_radius*2);
				//������
				g.fillOval(eyeball_right.x - eyeball_radius, eyeball_right.y - eyeball_radius, eyeball_radius*2, eyeball_radius*2);
			
				

			}
		};
		
		grimPan.setPreferredSize(new Dimension(GRIMPAN_WIDTH,GRIMPAN_HEIGHT));
		this.add(grimPan); //�⺻ borderlayout���� ��ġ ���� ���ϸ� center�� ����.
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new _02_EyeBallDraw();
	}

}
