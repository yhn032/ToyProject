package Class;

public class autoBoxing {
	Integer var;
	
	public Integer getInt() {
		return var;
	}
	/*���� Ŭ���� Ÿ���� ��������� var�� ���ڷ� �Ѿ�� �������� �־��ִ� �κ��� �����ϱ� �����.
	 *JDK 5.0���ķ� �����Ϸ��� ���������� Integer��� wrapper ��ü�� �����Ͽ� �����ϴ� �������� ������ֹǷ� �ڵ��� ������ �����������̴�.
	 */
	public void setInt(int i) {
		//�ڵ� �ڽ� 
		//var = Integer.valueOf(i);
		//�����Ϸ��� �⺻ �ڷ��� ���� i�� �ڵ����� ��üȭ ������ -> �ڵ� �ڽ�
		var = i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		autoBoxing a1 = new autoBoxing();
		a1.setInt(10000);
		//int res = var.intValue();
		//�����Ϸ��� ��ü �ڷ��� ���� var�� �ڵ����� �⺻�ڷ������� �ٲ��� -> �ڵ� ��ڽ�
		int res = a1.getInt();
		System.out.println("res : " + res);
		
		//�ڽ�
		//�⺻ Ÿ���� ���� ��ü�� ��ȯ�ϴ� ��; 
		Integer ten = Integer.valueOf(10);
		
		//��ڽ�
		//Wrapper ��ü�� �⺻ Ÿ���� ������ �ǵ����� ��
		int n = ten.intValue();
	}

}
