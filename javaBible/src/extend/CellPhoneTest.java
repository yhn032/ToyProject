package extend;

public class CellPhoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		D_caPhone dca = new D_caPhone("IN-7600","011-9XXX-9XXXX", 60, "400��");
		MP3Phone mp = new MP3Phone("KN-600","011-9XXX-9XXXX", 60, 256);
		
		//D_caPhone�� MP3PhoneŬ�������� ���� ���� �޼ҵ尡 ������, �ֻ��� Ŭ������ CellPhoneŬ�������� ��ӹ��� �޼ҵ带 �����Ͽ� ����� ���̴�. 
		// ��ó�� ��Ӱ��迡 �ִ� Ŭ�������� �ڽſ��� ���� ����̴��� ���� Ŭ������ ������ �޼ҵ带 ����� �� �ִ�. 
		// ��, ���� Ŭ������ ���� Ŭ������ ����� ������ �� ����. 
		// ���� ��� : �ϳ��� Ŭ������ �ϳ��� ���� Ŭ������ ���� �� �ִ�. 
		// ���� ��� : �ϳ��� Ŭ������ �� ���̻��� ���� Ŭ������ ���� �� �ִ� ���̴�.
		System.out.println(dca.getModel() + "," + dca.getChord() + "," + dca.getNubmer());
	}

}
