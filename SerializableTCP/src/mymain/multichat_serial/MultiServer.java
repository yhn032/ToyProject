package mymain.multichat_serial;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame; // �������۷���
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame {

	//����� â
	JTextArea jta_display;
	
	//�����ڼ� 
	JTextField jtf_user_count;
	
	//������ ��� 
	JList<String> jlist_user_list;
	
	//��Ʈ ���� 
	Font font = new Font("����ü", Font.BOLD, 16);
	
	//��Ʈ��ũ
	ServerSocket server;
	
	//thread ����ȭ ���� ��ü(Object��ü�� ������ �����ϴ�.)
	Object syncObj = new Object();
	
	//�ڼ����� ������ ��ü�� �����
	List<ReadThread> socketList = new ArrayList<ReadThread>();
	
	//���� ������ ������ ��ü�� ����� 
	List<String> 	   userList = new ArrayList<String>();
	public MultiServer() {
		// TODO Auto-generated constructor stub
		super("��Ƽ����");

		
		//���â �ʱ�ȭ 
		init_display();
		
		//�����ڼ� �ʱ�ȭ
		init_user_count();
		
		//������ ��� �ʱ�ȭ 
		init_user_list();
		
		//display_message("���� �����...");
		//���� �ʱ�ȭ 
		init_server();
		
		//��ġ		 x	  y
		this.setLocation(600, 100);

		//ũ��
		//this.setSize(400, 400);
		pack();

		//�����ֱ� ���������� �����ִ� �޼ҵ�
		this.setVisible(true);

		//����							// JFrame�� ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private void init_server() {
		// TODO Auto-generated method stub
		try {
			//8000 ��Ʈ ����
			server = new ServerSocket(8000);
			
			display_message("---���� �����...---");
			//���� ������ ���� : ���Ӵ�� �뵵�� ������
			//�͸� ��ü ���� ���. ��ü�� �������ڸ��� ������ {}�� �ǹ̴� Ȯ��(���)�� �ǹ��̴�. 
			new Thread() {
				
				public void run() {
					while(true) {
						try {
							Socket child = server.accept();
							
							//���Ӱ��� ����ȭ
							synchronized (syncObj) {//���ο� ����ڰ� �������� ���ο� �����带 ����� ����Ʈ�� ������ �����ϱ� ��������(����� ������ ������) Ż�� �޽����� �������ϰ� ����ȭ
								
								//���Ӱ���
								ReadThread rt = new ReadThread(child);
								rt.start();
								
								//�ڼ����� ������ �����尴ü�� ArraList�� �߰� 
								socketList.add(rt);//�߰��Ǵ� ���������� �ƹ��͵� �������ƶ�!
								
								//�����ڼ� ����ϴ� �޼ҵ�
								display_user_count();
							}
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void display_user_count() {
		// TODO Auto-generated method stub
		jtf_user_count.setText(socketList.size()+"");
	}
	public void display_message(String message) {
		jta_display.append(message);//�� �� �޾� ����ϰ�
		jta_display.append("\r\n");//�ٹٲ��
		int position = jta_display.getDocument().getLength();
		jta_display.setCaretPosition(position);
		
	}

	private void init_user_list() {
		// TODO Auto-generated method stub
		jlist_user_list = new JList<String>();
		
		JScrollPane jsp = new JScrollPane(jlist_user_list);
		
		jsp.setPreferredSize(new Dimension(120, 0)); //�� ���̸� �����ϸ� ���̴� �ڵ� ����
		
		this.add(jsp, "East");
	}

	private void init_user_count() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new GridLayout(1, 3));
		
		JLabel jlb_user = new JLabel("�����ڼ� : ", JLabel.RIGHT);
		jlb_user.setFont(font);
		
		jtf_user_count = new JTextField("0");	
		jtf_user_count.setFont(font);
		jtf_user_count.setHorizontalAlignment(JTextField.CENTER); //������� ��� ����
		
		JLabel jlb_count = new JLabel("(��)"); //�⺻�� ���� ����
		jlb_count.setFont(font);
		
		p.add(jlb_user);
		p.add(jtf_user_count);
		p.add(jlb_count);
		
		this.add(p, "South");
	}

	private void init_display() {
		// TODO Auto-generated method stub
		jta_display = new JTextArea();
		jta_display.setFont(font);
		
		JScrollPane jsp = new JScrollPane(jta_display);
		jsp.setPreferredSize(new Dimension(400, 400));
		
		
		this.add(jsp, "Center");
		
		//�б� �������� ���� 
		jta_display.setEditable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MultiServer();
	}
	
//��ƼŬ���� ���� Ŭ���� ���� ����-------
	class ReadThread extends Thread{
		Socket child;
		
		//BufferedReader br;//�ٴ����� �о�� �� ���� + ���� ���
		ObjectInputStream  ois;
		ObjectOutputStream oos;
		
		
		public ReadThread(Socket child) {//���۵帮���� ���͸� �ϱ� ���� ����ϴ� ������
			super();
			this.child = child;
			
			try {
				
				//client������   ObjectOutputStream�� ���� ����
				//server�������� ObjectInputStream���� ���� 
				ois = new ObjectInputStream(child.getInputStream());
				oos = new ObjectOutputStream(child.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//����
		}


		//child ������ ���Ŵ��뵵�� �̿�
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {//������ ���� ������ �𸣴� ���ѹݺ��ϸ鼭 ��ô��
				try {
					
					//�����͸� ��ü�� ����(������ȭ)
					Data data = (Data) ois.readObject();
					if(data ==null) break;
					
					
					//��������͸�
					display_message(String.format("[%d]-[%s]-[%s]",data.protocol, data.nickname, data.message ));
	

					if(data.protocol == Data.IN) {//�����̸�,,,
						
						
						synchronized (syncObj) { //���忡 ���õ� �Ϸ��� ������ ������������ �ƹ��͵� ���� ���ϵ��� ����ȭ
							
							//���� ������ �������� ���
							userList.add(data.nickname);
							//������� Jlist���
							display_user_list();
							
							//���� ���� ����� ������ ���ӵ� ��� ����ڿ��� �����ؾ� �Ѵ�. 
							send_message_all(data);
							
							//���ο� ����ڰ� ������ ��, ���� ������ ����� ��� ����ڿ��� ������.
							send_uesr_list_all();
							
						}
						
												
					} else {
						
						synchronized (syncObj) {
							//���� ���� �޼����� ���ӵ� ��� ����ڿ��� �����ؾ� �Ѵ�. 
							//���Ͱ� �ִ� ���·� �޾����� �о���� �������� ���͸� ������. ���� ���� ���� �����ʹ� ���Ͱ� ����. ������ Ŭ���̾�Ʈ�� �� ������ �ޱ� ������ ���͸� �߰������ �Ѵ�.
							send_message_all(data);
						}
						
					}
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//System.out.println("����������");
					break; //������ ������(���� or ��������)���� ������ ���. ������ �������� ���̻� ������ �ʿ䰡 ����
				}
			}//end-while
			
			
			synchronized (syncObj) {
				
				//���忡 ���� �Ϸ��� ó�� ����
				//��������� ó��.. (����)while�� Ż�� -> ���̻� ����� �� �ִ� ������ ����. ������ �ʿ䰡 ����
				
				//���� ArrayList���� this�� ��ġ�� ���Ѵ�.
				//this�� �ֱٿ� ����Ǿ �����͸� �ۼ����ϴٰ� ������ Ŭ���̾�Ʈ�� ����
				int index = socketList.indexOf(this);
				
				//������ ����� �г���. �����ϱ� ���� ���Ѵ�.
				String out_nickname = userList.get(index);
				
				
				//���� �����带 ���� ����Ʈ�κ��� ����
				socketList.remove(this);
				//�ο��� ���� ���� ���
				display_user_count();
				
				//�����ڸ�� ����
				userList.remove(index);
				display_user_list();
				
				
				
				//���� ���� ���� ������ ����� ���� ������ ���� �� ����. �������� �ʰ� ����Ǿ� �ִ� Ŭ���̾�Ʈ�� ���� �� ����
				Data data = new Data();
				data.protocol = Data.OUT;
				data.nickname = out_nickname;
				//����Ʈ���� �����ϱ� ���� ������ ����� �̸��� ���� �����صΰ�, ����Ʈ���� �����Ѵ�. �� ���Ŀ� ��ü ������ �ϸ� �̹� ���ϸ���Ʈ������ ���ŵ� �����̹Ƿ� 
				//send_message_all�޼ҵ忡���� ���ŵ� ������ ������ �� ����. ���� ������ ����(���� ���)�� �� ������, ���� �������� ��� ����ڿ��Ը� ���� ������ �����Ѵ�.  
				send_message_all(data);
				
				

				//����ڰ� �������� ��, ���� ������ ����� ��� ����ڿ��� ������.
				send_uesr_list_all();
			}
			
		}
	}
//end-ReadThread-------------------------

	public void display_user_list() {
		// TODO Auto-generated method stub
		String [] user_array = new String[userList.size()];
		userList.toArray(user_array);
		
		jlist_user_list.setListData(user_array); 
	}
	
	public void send_uesr_list_all() {
	// TODO Auto-generated method stub
		
		//ArrayList�� ���� -> Array
		String []userArray = new String[userList.size()];
		userList.toArray(userArray);
		
		//���� ������ ����� ������ �����͸� ����
		Data data = new Data();
		data.protocol = data.LIST;
		data.userArray = userArray;
		
		//System.out.println(sb.toString());
		send_message_all(data);
		
	}
	public void send_message_all(Data data) {
		// TODO Auto-generated method stub
		for(int i=0; i<socketList.size(); i++) { // i = 0 1 2
			//rt�� Ŭ���̾�Ʈ ���� child�� ������ ������ �ִ�.
			
			try {
				
				//���ϸ���Ʈ�� ����� i��° ���� ������ rt�� �����Ѵ�.
				ReadThread rt = socketList.get(i);
				rt.oos.writeObject(data);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end-for
	}




}