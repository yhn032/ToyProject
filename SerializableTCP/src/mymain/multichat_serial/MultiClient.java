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
import javax.swing.JFrame; // 스윙컴퍼런스
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiClient extends JFrame {

	// 모니터 창
	JTextArea jta_display;

	// 메시지 창
	JTextField jtf_message;

	// 접속자 목록
	JList<String> jlist_user_list;

	// 폰트 설정
	Font font = new Font("굴림체", Font.BOLD, 16);

	// 서버 연결 버튼
	JButton jbt_connect;

	boolean bConnect = false; // 현재 서버와의 연결 상태를 관리하는 상태 변수
	
	//네트워크
	Socket client;
	
	//BufferedReader br; -> 오브젝트 단위로 읽을 거라서 필요없다.
	ObjectInputStream  ois; //객체수신스트림
	ObjectOutputStream oos; //객체송신스트림
	
	
	String nickname = "코딩천재";

	//화면상의 그림판
	JPanel grimPan;
	
	//메모리상의 그림판 : 그렸던 그림이 ""지워지지 않게 하기 위해서"" 화면과 호환되는 메모리 상의 그림판을 만들어 카피해서 사용한다.(더블 버퍼링 기법)
	//주의 사항 : setVisible(true) 이후에 생성해야 한다. 화면과 호환되는 윈도우를 먼저 만들고 초기화해야한다.
	Image memPan;
	
	//선정보
	int line_thick = 5;
	//int line_color = 0;
	
	Color local_color = Color.red;
	
	public MultiClient() {
		// TODO Auto-generated constructor stub
		super("멀티 클라이언트");

		// 출력창 초기화
		init_display();

		// 메시지 입력창
		init_input_message();

		// 접속자 목록 초기화
		init_user_list();
		
		//그림판 초기화
		init_grimPan();
		
		//마우스 이벤트 초기화
		init_mouse_event();

		// 위치 x y
		this.setLocation(600, 100);

		// 크기
		// this.setSize(400, 400);
		pack();
		
		//크기 변경 불가
		setResizable(false);

		// 보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		// 종료 // JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//메모리 그림판 생성(grimPan과 호환되는 이미지(메모리상의 memPan)를 만들어라!)
		//실제로 그림은 memPan에서 그리고 grimPan은 출력만 하는 것
		memPan = grimPan.createImage(400, 400);
	}

	private void init_mouse_event() {
		// TODO Auto-generated method stub
		//그림판에서 마우스가 드래그되면
		
		grimPan.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				//super.mouseDragged(e);
				//System.out.println(e);
				//네트워크 접속이 안되어있으면 그냥 끝내라
				if(bConnect == false) { 
					System.out.println("--네트워크 연결후 이용하세요--");
					return; 
				}
				
				
				//눌린 마우스의 좌표 (마우스 좌표에서 선 굵기 만큼 원을 그릴 거다.)
				Point pt = e.getPoint();

				//네트워크 전송
				
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
				
				
				//메모리 그림판 내용을 현재 화면으로 복사해오기
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

		jbt_connect = new JButton("연결");
		jbt_connect.setPreferredSize(new Dimension(120, 0));

		jtf_message.setFont(font);
		jbt_connect.setFont(font);

		p.add(jtf_message, "Center");
		p.add(jbt_connect, "East");

		//그림판 메뉴 
		JPanel p1 = new JPanel(new GridLayout(1, 3));
		p1.setPreferredSize(new Dimension(400, 0));
		
		
		
		//1. 선 굵기 메뉴
		Integer [] thick_array = {5, 10, 15, 20, 25, 30,100};
		JComboBox<Integer> jcb_thick = new JComboBox<Integer>(thick_array);
		p1.add(jcb_thick);
		jcb_thick.setFont(font);
		
		//2. 선색상
		JButton jbt_color = new JButton("선색상");
		jbt_color.setFont(font);
		p1.add(jbt_color);
		
		//3. 지우기
		JButton jbt_clear = new JButton("지우기");
		jbt_clear.setFont(font);
		p1.add(jbt_clear);
		
		
		//콤보박스 이벤트
		jcb_thick.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				// 내가 선택한 값으로 line_thick을 변경하는 이벤트
				
				// 새롭게 변경된 값은 selected이벤트가 호출되고 
				// 변경 전의 기존값은 deselected이벤트가 호출된다.
				//System.out.println(e);
				if(e.getStateChange()==ItemEvent.SELECTED) { //두개의 메소드가 있는데 값이 바뀐 경우에 사용한다. deselected에 대한 처리가 필요한 경우에 사용한다 
					line_thick = (int)jcb_thick.getSelectedItem();
				}		
			}
		});
		
		//선 색상 버튼
		jbt_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				on_button_color();
			}
		});
		
		// 지우기 버튼
		jbt_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				on_button_clear();
			}
		});
		
		
		p.add(p1, "West");
		
		this.add(p, "South");

		// 연결버튼 눌렀을 때 이벤트
		jbt_connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// on_xxxx : callback method
				on_button_connect();
			}
		});

		// 메세지 창에서 입력시 이벤트
		jtf_message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// super.keyPressed(e);
				int key = e.getKeyCode();// 눌린 키 값
				if (key == KeyEvent.VK_ENTER) {
					send_message();
				}
			}
		});

	}

	protected void on_button_clear() {
		// TODO Auto-generated method stub
		// 메모리 판 지우기 
		Graphics g = memPan.getGraphics();
		//          x  y width  height
		g.clearRect(0, 0, 400, 400);
		
		//메모리 -> 화면 복사
		grimPan.repaint();
	}

	protected void on_button_color() {
		// TODO Auto-generated method stub
		
		Color im_color = JColorChooser.showDialog(this, "선색상을 선택하세요", local_color);
		
		//선택한 칼라가 있으면(캔슬이나 종료한 경우가 아니면. 이 경우에는 im_color에 널 값이 들어간다. 따라서 local_color가 변하지 않아서 기존 색상이 나오게 된다.)
		if(im_color != null) local_color = im_color;
	}

	protected void send_message() {
		// TODO Auto-generated method stub
		// 연결되어 있지 않으면 알려주고 끝내라
		if (bConnect == false) {
			jtf_message.setText("");
			JOptionPane.showMessageDialog(this, "연결하신 후 이용하실 수 있습니다!!");
			return;
		}

		// 입력 메시지 읽어오기
		String message = jtf_message.getText().trim();
		if (message.isEmpty()) {
			// 아무것도 안 한다.
			jtf_message.setText("");
			return;
		}

		try {

			// 전송메시지 포장 : "MSG#대화명#내용\n"
			//String send_data = String.format("MSG#%s#%s\n", nickname, message);
			//client.getOutputStream().write(send_data.getBytes());

			Data data = new Data();
			data.protocol = Data.MSG;
			data.nickname = nickname;
			data.message = message;
			
			oos.writeObject(data);
			
			// 이전 입력된 메시지 지우기
			jtf_message.setText("");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	protected void on_button_connect() {
		// TODO Auto-generated method stub
		// toggle 처리 : true <-> false
		bConnect = !bConnect;

		if (bConnect) {// 연결

			try {
				// 해당포트로 연결시도

				client = new Socket("localhost", 8000); // -> 나 자신의 서버에 접속할 때
				//client = new Socket("192.168.0.9", 8000); // -> 특정 주소에 접속할 때
				
				//객체스트림 초기화 : 상대 소켓과 Cross형태로 순서 설정(나:oos <-> 너 : ois);
				oos = new ObjectOutputStream(client.getOutputStream()); //전송
				ois = new ObjectInputStream(client.getInputStream()); 	//수신
				
				
				
				// 첫번째 입장 메세지 전송 "IN#홍길동\n" 서버에서 줄 단위로 받으므로 반드시 "엔터"를 보내야 한다.
						//String send_message = String.format("IN#%s\n", nickname);
						//client.getOutputStream().write(send_message.getBytes());
				Data data = new Data();
				data.protocol = 1;
				data.nickname = nickname;
				
				//나와 연결된 상대방 소켓으로 데이터를 전송했다.
				oos.writeObject(data);
				
				// 메시지 수신 대기
				read_message();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				JOptionPane.showMessageDialog(this, "연결실패");
				bConnect = false;
			}

		} else {// 끊기

			try {

				/*
				 * String exit_message = String.format("OUT#%s\n", nickname);
				 * client.getOutputStream().write(exit_message.getBytes());
				 * 
				 * read_message();
				 */

				// 소켓을 닫는다.
				client.close();

			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		// 버튼의 캡션(타이틀) 변경
		jbt_connect.setText(bConnect ? "끊기" : "연결");
	}

	private void read_message() {
		// TODO Auto-generated method stub
		

		// 데이터 수신대기용 스레드 생성
		new Thread() {
			public void run() {
				while (true) {
					try {
						
						Data data = (Data)ois.readObject();
						
						if(data == null) break;



						if (data.protocol==data.IN) { // 입장이면 ,,,

							String message = String.format("▶▶▶ [%s]님 입장하셨습니다.", data.nickname);
							display_message(message);

						} else if (data.protocol==data.OUT) { // 퇴장이면 ,,,

							String message = String.format("◀◀◀ [%s]님 퇴장하셨습니다.", data.nickname);
							display_message(message);

						} else if (data.protocol==data.LIST) { // 접속자 목록이면,,,
							// msg_array[0] = "";
							display_user_list(data.userArray);
							
						} else if (data.protocol==data.MSG) { // 채팅이면,,,

							String message = String.format("[%s]님 말씀 :\r\n   %s", data.nickname, data.message);
							display_message(message);
							
						} else if (data.protocol==data.DRAW) { // 그리기면,,,

							
							
							//메모리 판에 그린다. 
							Graphics g = memPan.getGraphics();
							g.setColor(data.color);
							
							g.fillOval(data.pt.x-data.thick, data.pt.y-data.thick, data.thick*2, data.thick*2);
							
							//화면에 복사해라
							grimPan.repaint();		
							
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						break;
					}
				} // end-while

				// 상대방 소켓이 끊긴 경우에 대한 코드 (run메소드가 끝났다는 것은 더이상 통신 가능한 소켓이 없다는 것) 서버가 종료된 경우
				// 자의든 타의든 run이 끝났다는 것은 상대방 소켓이 끊어졌다는 뜻이다.
				bConnect = false;// 끊어진 상태
				jbt_connect.setText("연결");

				// 채팅창초기화
				jta_display.setText("");

				// 유저 목록 초기화 (연결이 끊긴 경우)
				String[] user_array = new String[0];
				jlist_user_list.setListData(user_array);

				JOptionPane.showMessageDialog(MultiClient.this, "연결이 끊어졌습니다.");

			}// end-run
		}.start();

	}

	protected void display_user_list(String[] user_array) {
		// TODO Auto-generated method stub

		jlist_user_list.setListData(user_array);
	}

	public void display_message(String message) {
		jta_display.append(message);
		jta_display.append("\r\n");// 한 줄 받고 줄바꿔라
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

		// 읽기 전용으로 선언
		jta_display.setEditable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MultiClient();
	}

}