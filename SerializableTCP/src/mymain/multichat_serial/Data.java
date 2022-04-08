package mymain.multichat_serial;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;

//����ȭ ���� ��ü���� ǥ��
public class Data implements Serializable{

	//�������� ����
	
	
	//protocol ���
	public static final int IN   = 1;
	public static final int OUT  = 2;
	public static final int MSG  = 3;
	public static final int LIST = 4;
	public static final int DRAW = 5;
	
	
	
	
	int protocol;  		//������ ���� : �Էµ� �������� ������ ���� ����
	String nickname;	//��ȭ��
	String message;		//�޽���
	
	//������ ��� 
	String [] userArray; //�迭�� ����ȭ �ȴ�.
	
	//�׸��� ����
	Point pt;			//��ǥ  Point�� ����ȭ ������ ����̴�. - implements java.io.Serializable
	int thick;			//������
	Color color;		//������ Color�� ����ȭ ������ ����̴�. - implements java.io.Serializable
	
	
	
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
