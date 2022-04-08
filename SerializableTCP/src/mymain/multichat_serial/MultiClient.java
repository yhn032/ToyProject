package mymain.multichat_serial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame; // �������۷���
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiClient extends JFrame {

	// ����� â
	JTextArea jta_display;

	// �޽��� â
	JTextField jtf_message;

	// ������ ���
	JList<String> jlist_user_list;

	// ��Ʈ ����
	Font font = new Font("����ü", Font.BOLD, 16);

	// ���� ���� ��ư
	JButton jbt_connect;

	boolean bConnect = false; // ���� �������� ���� ���¸� �����ϴ� ���� ����
	
	//��Ʈ��ũ
	Socket client;
	
	//BufferedReader br; -> ������Ʈ ������ ���� �Ŷ� �ʿ����.
	ObjectInputStream  ois; //��ü���Ž�Ʈ��
	ObjectOutputStream oos; //��ü�۽Ž�Ʈ��
	
	
	String nickname = "�ڵ�õ��";

	//ȭ����� �׸���
	JPanel grimPan;
	
	//�޸𸮻��� �׸��� : �׷ȴ� �׸��� ""�������� �ʰ� �ϱ� ���ؼ�"" ȭ��� ȣȯ�Ǵ� �޸� ���� �׸����� ����� ī���ؼ� ����Ѵ�.(���� ���۸� ���)
	//���� ���� : setVisible(true) ���Ŀ� �����ؾ� �Ѵ�. ȭ��� ȣȯ�Ǵ� �����츦 ���� ����� �ʱ�ȭ�ؾ��Ѵ�.
	Image memPan;
	
	//������
	int line_thick = 5;
	//int line_color = 0;
	
	Color local_color = Color.red;
	
	public MultiClient() {
		// TODO Auto-generated constructor stub
		super("��Ƽ Ŭ���̾�Ʈ");

		// ���â �ʱ�ȭ
		init_display();

		// �޽��� �Է�â
		init_input_message();

		// ������ ��� �ʱ�ȭ
		init_user_list();
		
		//�׸��� �ʱ�ȭ
		init_grimPan();
		
		//���콺 �̺�Ʈ �ʱ�ȭ
		init_mouse_event();

		// ��ġ x y
		this.setLocation(600, 100);

		// ũ��
		// this.setSize(400, 400);
		pack();
		
		//ũ�� ���� �Ұ�
		setResizable(false);

		// �����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		// ���� // JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�޸� �׸��� ����(grimPan�� ȣȯ�Ǵ� �̹���(�޸𸮻��� memPan)�� ������!)
		//������ �׸��� memPan���� �׸��� grimPan�� ��¸� �ϴ� ��
		memPan = grimPan.createImage(400, 400);
	}

	private void init_mouse_event() {
		// TODO Auto-generated method stub
		//�׸��ǿ��� ���콺�� �巡�׵Ǹ�
		
		grimPan.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				//super.mouseDragged(e);
				//System.out.println(e);
				//��Ʈ��ũ ������ �ȵǾ������� �׳� ������
				if(bConnect == false) { 
					System.out.println("--��Ʈ��ũ ������ �̿��ϼ���--");
					return; 
				}
				
				
				//���� ���콺�� ��ǥ (���콺 ��ǥ���� �� ���� ��ŭ ���� �׸� �Ŵ�.)
				Point pt = e.getPoint();

				//��Ʈ��ũ ����
				
				try {
					Data data = new Data();
					
					
					data.protocol = Data.DRAW;
					data.pt       = pt;
					data.thick    = line_thick;
					data.color    = local_color;
				
					oos.writeObject(data);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
	}

	private void init_grimPan() {
		// TODO Auto-generated method stub
		grimPan = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				//super.paintComponent(g);
				
				
				//�޸� �׸��� ������ ���� ȭ������ �����ؿ���
				g.drawImage(memPan, 0, 0, this);
			}
			
		};
		
		grimPan.setPreferredSize(new Dimension(400, 400));
		
		this.add(grimPan, "West");
	}

	private void init_input_message() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new BorderLayout());

		jtf_message = new JTextField();

		jbt_connect = new JButton("����");
		jbt_connect.setPreferredSize(new Dimension(120, 0));

		jtf_message.setFont(font);
		jbt_connect.setFont(font);

		p.add(jtf_message, "Center");
		p.add(jbt_connect, "East");

		//�׸��� �޴� 
		JPanel p1 = new JPanel(new GridLayout(1, 3));
		p1.setPreferredSize(new Dimension(400, 0));
		
		
		
		//1. �� ���� �޴�
		Integer [] thick_array = {5, 10, 15, 20, 25, 30,100};
		JComboBox<Integer> jcb_thick = new JComboBox<Integer>(thick_array);
		p1.add(jcb_thick);
		jcb_thick.setFont(font);
		
		//2. ������
		JButton jbt_color = new JButton("������");
		jbt_color.setFont(font);
		p1.add(jbt_color);
		
		//3. �����
		JButton jbt_clear = new JButton("�����");
		jbt_clear.setFont(font);
		p1.add(jbt_clear);
		
		
		//�޺��ڽ� �̺�Ʈ
		jcb_thick.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				// ���� ������ ������ line_thick�� �����ϴ� �̺�Ʈ
				
				// ���Ӱ� ����� ���� selected�̺�Ʈ�� ȣ��ǰ� 
				// ���� ���� �������� deselected�̺�Ʈ�� ȣ��ȴ�.
				//System.out.println(e);
				if(e.getStateChange()==ItemEvent.SELECTED) { //�ΰ��� �޼ҵ尡 �ִµ� ���� �ٲ� ��쿡 ����Ѵ�. deselected�� ���� ó���� �ʿ��� ��쿡 ����Ѵ� 
					line_thick = (int)jcb_thick.getSelectedItem();
				}		
			}
		});
		
		//�� ���� ��ư
		jbt_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				on_button_color();
			}
		});
		
		// ����� ��ư
		jbt_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				on_button_clear();
			}
		});
		
		
		p.add(p1, "West");
		
		this.add(p, "South");

		// �����ư ������ �� �̺�Ʈ
		jbt_connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// on_xxxx : callback method
				on_button_connect();
			}
		});

		// �޼��� â���� �Է½� �̺�Ʈ
		jtf_message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// super.keyPressed(e);
				int key = e.getKeyCode();// ���� Ű ��
				if (key == KeyEvent.VK_ENTER) {
					send_message();
				}
			}
		});

	}

	protected void on_button_clear() {
		// TODO Auto-generated method stub
		// �޸� �� ����� 
		Graphics g = memPan.getGraphics();
		//          x  y width  height
		g.clearRect(0, 0, 400, 400);
		
		//�޸� -> ȭ�� ����
		grimPan.repaint();
	}

	protected void on_button_color() {
		// TODO Auto-generated method stub
		
		Color im_color = JColorChooser.showDialog(this, "�������� �����ϼ���", local_color);
		
		//������ Į�� ������(ĵ���̳� ������ ��찡 �ƴϸ�. �� ��쿡�� im_color�� �� ���� ����. ���� local_color�� ������ �ʾƼ� ���� ������ ������ �ȴ�.)
		if(im_color != null) local_color = im_color;
	}

	protected void send_message() {
		// TODO Auto-generated method stub
		// ����Ǿ� ���� ������ �˷��ְ� ������
		if (bConnect == false) {
			jtf_message.setText("");
			JOptionPane.showMessageDialog(this, "�����Ͻ� �� �̿��Ͻ� �� �ֽ��ϴ�!!");
			return;
		}

		// �Է� �޽��� �о����
		String message = jtf_message.getText().trim();
		if (message.isEmpty()) {
			// �ƹ��͵� �� �Ѵ�.
			jtf_message.setText("");
			return;
		}

		try {

			// ���۸޽��� ���� : "MSG#��ȭ��#����\n"
			//String send_data = String.format("MSG#%s#%s\n", nickname, message);
			//client.getOutputStream().write(send_data.getBytes());

			Data data = new Data();
			data.protocol = Data.MSG;
			data.nickname = nickname;
			data.message = message;
			
			oos.writeObject(data);
			
			// ���� �Էµ� �޽��� �����
			jtf_message.setText("");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	protected void on_button_connect() {
		// TODO Auto-generated method stub
		// toggle ó�� : true <-> false
		bConnect = !bConnect;

		if (bConnect) {// ����

			try {
				// �ش���Ʈ�� ����õ�

				client = new Socket("localhost", 8000); // -> �� �ڽ��� ������ ������ ��
				//client = new Socket("192.168.0.9", 8000); // -> Ư�� �ּҿ� ������ ��
				
				//��ü��Ʈ�� �ʱ�ȭ : ��� ���ϰ� Cross���·� ���� ����(��:oos <-> �� : ois);
				oos = new ObjectOutputStream(client.getOutputStream()); //����
				ois = new ObjectInputStream(client.getInputStream()); 	//����
				
				
				
				// ù��° ���� �޼��� ���� "IN#ȫ�浿\n" �������� �� ������ �����Ƿ� �ݵ�� "����"�� ������ �Ѵ�.
						//String send_message = String.format("IN#%s\n", nickname);
						//client.getOutputStream().write(send_message.getBytes());
				Data data = new Data();
				data.protocol = 1;
				data.nickname = nickname;
				
				//���� ����� ���� �������� �����͸� �����ߴ�.
				oos.writeObject(data);
				
				// �޽��� ���� ���
				read_message();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				JOptionPane.showMessageDialog(this, "�������");
				bConnect = false;
			}

		} else {// ����

			try {

				/*
				 * String exit_message = String.format("OUT#%s\n", nickname);
				 * client.getOutputStream().write(exit_message.getBytes());
				 * 
				 * read_message();
				 */

				// ������ �ݴ´�.
				client.close();

			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		// ��ư�� ĸ��(Ÿ��Ʋ) ����
		jbt_connect.setText(bConnect ? "����" : "����");
	}

	private void read_message() {
		// TODO Auto-generated method stub
		

		// ������ ���Ŵ��� ������ ����
		new Thread() {
			public void run() {
				while (true) {
					try {
						
						Data data = (Data)ois.readObject();
						
						if(data == null) break;



						if (data.protocol==data.IN) { // �����̸� ,,,

							String message = String.format("������ [%s]�� �����ϼ̽��ϴ�.", data.nickname);
							display_message(message);

						} else if (data.protocol==data.OUT) { // �����̸� ,,,

							String message = String.format("������ [%s]�� �����ϼ̽��ϴ�.", data.nickname);
							display_message(message);

						} else if (data.protocol==data.LIST) { // ������ ����̸�,,,
							// msg_array[0] = "";
							display_user_list(data.userArray);
							
						} else if (data.protocol==data.MSG) { // ä���̸�,,,

							String message = String.format("[%s]�� ���� :\r\n   %s", data.nickname, data.message);
							display_message(message);
							
						} else if (data.protocol==data.DRAW) { // �׸����,,,

							
							
							//�޸� �ǿ� �׸���. 
							Graphics g = memPan.getGraphics();
							g.setColor(data.color);
							
							g.fillOval(data.pt.x-data.thick, data.pt.y-data.thick, data.thick*2, data.thick*2);
							
							//ȭ�鿡 �����ض�
							grimPan.repaint();		
							
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						break;
					}
				} // end-while

				// ���� ������ ���� ��쿡 ���� �ڵ� (run�޼ҵ尡 �����ٴ� ���� ���̻� ��� ������ ������ ���ٴ� ��) ������ ����� ���
				// ���ǵ� Ÿ�ǵ� run�� �����ٴ� ���� ���� ������ �������ٴ� ���̴�.
				bConnect = false;// ������ ����
				jbt_connect.setText("����");

				// ä��â�ʱ�ȭ
				jta_display.setText("");

				// ���� ��� �ʱ�ȭ (������ ���� ���)
				String[] user_array = new String[0];
				jlist_user_list.setListData(user_array);

				JOptionPane.showMessageDialog(MultiClient.this, "������ ���������ϴ�.");

			}// end-run
		}.start();

	}

	protected void display_user_list(String[] user_array) {
		// TODO Auto-generated method stub

		jlist_user_list.setListData(user_array);
	}

	public void display_message(String message) {
		jta_display.append(message);
		jta_display.append("\r\n");// �� �� �ް� �ٹٲ��
		int position = jta_display.getDocument().getLength();
		jta_display.setCaretPosition(position);

	}

	private void init_user_list() {
		// TODO Auto-generated method stub
		jlist_user_list = new JList<String>();

		JScrollPane jsp = new JScrollPane(jlist_user_list);

		jsp.setPreferredSize(new Dimension(120, 0));

		this.add(jsp, "East");
	}

	private void init_display() {
		// TODO Auto-generated method stub
		jta_display = new JTextArea();
		jta_display.setFont(font);

		JScrollPane jsp = new JScrollPane(jta_display);
		jsp.setPreferredSize(new Dimension(400, 400));

		this.add(jsp, "Center");

		// �б� �������� ����
		jta_display.setEditable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MultiClient();
	}

}