package mymain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame; // �������۷���
import javax.swing.JPanel;

public class _01_TestGraphics extends JFrame {

	static Image img;;
	//static �ʱ�ȭ (���α׷� ���� �߰��� ���θ޼ҵ尡 ������ ��)
	static {
		//�̹��� �ε�
		img = Toolkit.getDefaultToolkit().getImage("ĸó.png");
	}
	
	JPanel grimPan;
	
	public _01_TestGraphics() {
		// TODO Auto-generated constructor stub
		super("Graphics ����");
		
		init_grimpan();
		
		//��ġ		 x	  y
		this.setLocation(200, 100);

		//ũ��
		//this.setSize(400, 400);
		pack();

		//�����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		//����							// JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}

	Font font = new Font("����ü", Font.BOLD, 25);
	
	private void init_grimpan() {
		// TODO Auto-generated method stub
		//JPanel : ��ȭ�� (����Ŭ����)
		class GrimPanel extends JPanel{
			
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				//Graphics : �׸��⵵��(��, ����)
//�ؽ�Ʈ ���
				
				
				//��Ʈ����
				g.setFont(font);
				//���ڿ� ���(��ǥ������ : ��µ� ������ ���� �ϴ�)
				g.drawString("����������?", 0, 30);
				
				// �׸��� ����ϱ�
				g.setColor(Color.black);
				g.drawString("�׸��� ����", 100+1, 60+1); 
				g.setColor(Color.red);
				g.drawString("�׸��� ����", 100, 60);
//���� ���
				g.setColor(Color.blue);
				//�簢��(�׵θ���)
				g.drawRect(10, 100, 100, 100);
				//�簢��(ä���� �׸���)
				g.fillRect(120, 100, 100, 100);
				
				//��(�׵θ���)
				g.drawOval(10, 210, 100, 100);
				//��(ä����)
				g.fillOval(120, 210, 100, 100);
				
				//��
				g.drawLine(400, 0, 0, 400);
//�̹���
				g.drawImage(img, 220, 100,100,100, this);
				g.drawImage(img, 0, 0, this);
			}
		}
		
		grimPan = new GrimPanel();
		
		grimPan.setPreferredSize(new Dimension(400, 400));
		this.add(grimPan,"Center");
		
		//����, �� �� �׸��� ��ü 
		//Graphics g = grimPan.getGraphics();
		//g.drawString("�ȳ��ϼ���", 0, 30);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new _01_TestGraphics();
	}

}