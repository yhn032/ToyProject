package extend;
abstract class AbsEx1{
	int a = 10;
	final String STR = "abstract test";
	
	public String getStr() {
		return STR;
	}
	public abstract int getA();
}

abstract class AbsEx2 extends AbsEx1{
	public int getA() {			//�޼ҵ� �������̵�
		return a;					//��ӹ��� ����Ŭ������ ������� a
	}
		public abstract String getStr();
	
}

//�߻� Ŭ���� ���� ��Ӱ��迡���� �߻� �޼ҵ带 �ݵ�� ������ �� �ʿ�� ����. �׳� ��ӹ��� ä�� ������ �ִٰ� ���߿� �Ϲ� Ŭ������ ��Ӱ��谡 �̷�� ����, �Ϲ� Ŭ�������� �����Ǹ� �ϸ� �ȴ�.


public class abstractEx extends AbsEx2 {
	public String getStr() {
		return STR;
	}
	public static void main(String[] args) {
		abstractEx ae = new abstractEx();
		System.out.println("ae.getA():" + ae.getA());
		System.out.println("ae.getStr():" + ae.getStr());
	}
}
