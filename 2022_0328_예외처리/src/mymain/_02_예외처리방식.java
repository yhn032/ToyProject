package mymain;

public class _02_����ó����� {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 1;
		int res;
		
		String str = "ABC";
		int len = 0;
		int [] ar = new int[5];
		
		//����ó����� 
		try {
			//�����ؾ��� �� �ڵ�
			//�ڹ� ���ο��� 0�϶� ���... ���������� ���ܸ� �����ϴ�.
			if (b==0) throw new ArithmeticException("0���� ������ ����ϱ�..."); 
			res = a/b;
			
			//�ڹ� ���ο��� null�� ��� ���ܸ� ������.
			if(str == null) throw new NullPointerException("str is null : �����ϴ� ��ü�� ����");
			len = str.length();
			
			//�ڹ� ���ο��� ���ܰ� �߻��ϸ� ���ܸ� ������
			int index = 10;
			if(index <0 || index >= ar.length) throw new ArrayIndexOutOfBoundsException("�迭������ ������ϴ�.");
			
			ar[index] = 100;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			//�����Ǵ� ����ó�� ����
			e.printStackTrace();
			//System.out.println("0���� ���� �� �����ϴ�.");
		} catch (NullPointerException e ) {
			e.printStackTrace();
			//System.out.println("str is null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("---End---");
	}

}
