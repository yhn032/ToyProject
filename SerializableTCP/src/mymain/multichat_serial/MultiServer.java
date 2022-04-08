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

import javax.swing.JFrame; // 스윙컴퍼런스
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame {

	//모니터 창
	JTextArea jta_display;
	
	//접속자수 
	JTextField jtf_user_count;
	
	//접속자 목록 
	JList<String> jlist_user_list;
	
	//폰트 설정 
	Font font = new Font("굴림체", Font.BOLD, 16);
	
	//네트워크
	ServerSocket server;
	
	//thread 동기화 관리 객체(Object객체면 누구나 가능하다.)
	Object syncObj = new Object();
	
	//자소켓을 관리할 객체를 만들기
	List<ReadThread> socketList = new ArrayList<ReadThread>();
	
	//접속 유저를 관리할 객체를 만들기 
	List<String> 	   userList = new ArrayList<String>();
	public MultiServer() {
		// TODO Auto-generated constructor stub
		super("멀티서버");

		
		//출력창 초기화 
		init_display();
		
		//접속자수 초기화
		init_user_count();
		
		//접속자 목록 초기화 
		init_user_list();
		
		//display_message("서버 대기중...");
		//서버 초기화 
		init_server();
		
		//위치		 x	  y
		this.setLocation(600, 100);

		//크기
		//this.setSize(400, 400);
		pack();

		//보여주기 가시적으로 보여주는 메소드
		this.setVisible(true);

		//종료							// JFrame의 상수
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private void init_server() {
		// TODO Auto-generated method stub
		try {
			//8000 포트 열기
			server = new ServerSocket(8000);
			
			display_message("---서버 대기중...---");
			//별도 스레드 생성 : 접속대기 용도의 스레드
			//익명 객체 생성 기법. 객체를 생성하자마자 나오는 {}의 의미는 확장(상속)의 의미이다. 
			new Thread() {
				
				public void run() {
					while(true) {
						try {
							Socket child = server.accept();
							
							//접속과정 동기화
							synchronized (syncObj) {//새로운 사용자가 들어왔으면 새로운 스레드를 만들고 리스트에 정보를 저장하기 전까지는(블록이 끝날때 까지는) 탈퇴 메시지를 하지못하게 동기화
								
								//접속과정
								ReadThread rt = new ReadThread(child);
								rt.start();
								
								//자소켓을 포함한 스레드객체를 ArraList에 추가 
								socketList.add(rt);//추가되는 과정에서는 아무것도 하지말아라!
								
								//접속자수 출력하는 메소드
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
		jta_display.append(message);//한 줄 받아 출력하고
		jta_display.append("\r\n");//줄바꿔라
		int position = jta_display.getDocument().getLength();
		jta_display.setCaretPosition(position);
		
	}

	private void init_user_list() {
		// TODO Auto-generated method stub
		jlist_user_list = new JList<String>();
		
		JScrollPane jsp = new JScrollPane(jlist_user_list);
		
		jsp.setPreferredSize(new Dimension(120, 0)); //행 길이만 지정하면 높이는 자동 세팅
		
		this.add(jsp, "East");
	}

	private void init_user_count() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new GridLayout(1, 3));
		
		JLabel jlb_user = new JLabel("접속자수 : ", JLabel.RIGHT);
		jlb_user.setFont(font);
		
		jtf_user_count = new JTextField("0");	
		jtf_user_count.setFont(font);
		jtf_user_count.setHorizontalAlignment(JTextField.CENTER); //수평방향 가운데 정렬
		
		JLabel jlb_count = new JLabel("(명)"); //기본값 왼쪽 정렬
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
		
		//읽기 전용으로 선언 
		jta_display.setEditable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MultiServer();
	}
	
//멀티클래스 내부 클래스 선언 영역-------
	class ReadThread extends Thread{
		Socket child;
		
		//BufferedReader br;//줄단위로 읽어올 수 있음 + 성능 향상
		ObjectInputStream  ois;
		ObjectOutputStream oos;
		
		
		public ReadThread(Socket child) {//버퍼드리더로 필터링 하기 위해 사용하는 생성자
			super();
			this.child = child;
			
			try {
				
				//client츳에서   ObjectOutputStream을 먼저 생성
				//server측에서는 ObjectInputStream먼저 생성 
				ois = new ObjectInputStream(child.getInputStream());
				oos = new ObjectOutputStream(child.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//수신
		}


		//child 소켓의 수신대기용도로 이용
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {//상대방이 언제 보낼지 모르니 무한반복하면서 상시대기
				try {
					
					//데이터를 객체로 수신(역직렬화)
					Data data = (Data) ois.readObject();
					if(data ==null) break;
					
					
					//서버모니터링
					display_message(String.format("[%d]-[%s]-[%s]",data.protocol, data.nickname, data.message ));
	

					if(data.protocol == Data.IN) {//입장이면,,,
						
						
						synchronized (syncObj) { //입장에 관련된 일련의 과정이 끝날때까지는 아무것도 하지 못하도록 동기화
							
							//새로 접속한 유저명을 등록
							userList.add(data.nickname);
							//유저목록 Jlist출력
							display_user_list();
							
							//현재 들어온 사용자 정보를 접속된 모든 사용자에게 전송해야 한다. 
							send_message_all(data);
							
							//새로운 사용자가 들어왔을 때, 현재 접속자 목록을 모든 사용자에게 보낸다.
							send_uesr_list_all();
							
						}
						
												
					} else {
						
						synchronized (syncObj) {
							//현재 들어온 메세지를 접속된 모든 사용자에게 전송해야 한다. 
							//엔터가 있는 상태로 받았지만 읽어오는 과정에서 엔터를 버린다. 따라서 현재 읽은 데이터는 엔터가 없다. 하지만 클라이언트도 줄 단위로 받기 때문에 엔터를 추가해줘야 한다.
							send_message_all(data);
						}
						
					}
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//System.out.println("비정상종료");
					break; //상대방이 비정상(정전 or 강제종료)으로 종료한 경우. 어차피 나갔으니 더이상 유지할 필요가 없음
				}
			}//end-while
			
			
			synchronized (syncObj) {
				
				//퇴장에 대한 일련의 처리 과정
				//소켓종료시 처리.. (퇴장)while문 탈출 -> 더이상 통신할 수 있는 소켓이 없음. 유지할 필요가 없음
				
				//현재 ArrayList에서 this의 위치를 구한다.
				//this는 최근에 연결되어서 데이터를 송수신하다가 종료한 클라이언트의 소켓
				int index = socketList.indexOf(this);
				
				//퇴장한 사람의 닉네임. 삭제하기 전에 구한다.
				String out_nickname = userList.get(index);
				
				
				//현재 쓰레드를 소켓 리스트로부터 제거
				socketList.remove(this);
				//인원수 갱신 정보 출력
				display_user_count();
				
				//접속자목록 갱신
				userList.remove(index);
				display_user_list();
				
				
				
				//퇴장 정보 전송 퇴장한 사람은 퇴장 정보를 받을 수 없다. 퇴장하지 않고 연결되어 있는 클라이언트만 받을 수 있음
				Data data = new Data();
				data.protocol = Data.OUT;
				data.nickname = out_nickname;
				//리스트에서 삭제하기 전에 퇴장한 사람의 이름을 따로 저장해두고, 리스트에서 삭제한다. 그 이후에 전체 전송을 하면 이미 소켓리스트에서는 제거된 상태이므로 
				//send_message_all메소드에서도 제거된 소켓을 참조할 수 없다. 따라서 소켓이 끊긴(나간 사람)은 볼 수없고, 아직 연결중인 모든 사용자에게만 퇴장 정보를 전송한다.  
				send_message_all(data);
				
				

				//사용자가 퇴장했을 때, 현재 접속자 목록을 모든 사용자에게 보낸다.
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
		
		//ArrayList의 내용 -> Array
		String []userArray = new String[userList.size()];
		userList.toArray(userArray);
		
		//현재 접속자 목록을 저장할 데이터를 생성
		Data data = new Data();
		data.protocol = data.LIST;
		data.userArray = userArray;
		
		//System.out.println(sb.toString());
		send_message_all(data);
		
	}
	public void send_message_all(Data data) {
		// TODO Auto-generated method stub
		for(int i=0; i<socketList.size(); i++) { // i = 0 1 2
			//rt는 클라이언트 소켓 child의 정보를 가지고 있다.
			
			try {
				
				//소켓리스트에 저장된 i번째 소켓 정보를 rt에 저장한다.
				ReadThread rt = socketList.get(i);
				rt.oos.writeObject(data);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end-for
	}




}