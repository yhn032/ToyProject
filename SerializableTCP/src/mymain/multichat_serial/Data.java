package mymain.multichat_serial;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;

//직렬화 가능 객체임을 표시
public class Data implements Serializable{

	//프로토콜 설계
	
	
	//protocol 상수
	public static final int IN   = 1;
	public static final int OUT  = 2;
	public static final int MSG  = 3;
	public static final int LIST = 4;
	public static final int DRAW = 5;
	
	
	
	
	int protocol;  		//데이터 종류 : 입력된 데이터의 정보를 담을 변수
	String nickname;	//대화명
	String message;		//메시지
	
	//접속자 목록 
	String [] userArray; //배열도 직렬화 된다.
	
	//그리기 정보
	Point pt;			//좌표  Point는 직렬화 가능한 대상이다. - implements java.io.Serializable
	int thick;			//선굵기
	Color color;		//선색상 Color는 직렬화 가능한 대상이다. - implements java.io.Serializable
	
	
	
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public String getNicnkname() {
		return nickname;
	}
	public void setNicnkname(String nicnkname) {
		this.nickname = nicnkname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getUserArray() {
		return userArray;
	}
	public void setUserArray(String[] userArray) {
		this.userArray = userArray;
	}
	public Point getPt() {
		return pt;
	}
	public void setPt(Point pt) {
		this.pt = pt;
	}
	public int getThick() {
		return thick;
	}
	public void setThick(int thick) {
		this.thick = thick;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
