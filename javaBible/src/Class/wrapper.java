package Class;

public class wrapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�⺻ �ڷ����� ������ ���� ��ü�� �Ǿ� ��ü�μ� �۾��� �����ϰ� �Ѵ�.
		//��ü���� ������ �� �ִ� Vector�� �⺻�ڷ����� �����Ϸ��� WrapperŬ������ ��üȭ�� �����־�� �Ѵ�. 
		//������ �̹� ��üȭ �� �Ϳ� ���� �����۾��� �� �� ����. ������ �⺻�ڷ����� �ٽ� ��üȭ ���־�� �ϴ� ���̴�.
		boolean b = true; 
		//���� b�� ��üȭ
		//Boolean wrap_b = new Boolean(b); //java 9���� Wrapper ��ü�� ������ ��, �����ڸ� �̿��ϴ� ����� ����ϰ� ���� �޸𸮿� ���� �ӵ��� valueOf() ���� �޼ҵ带 �̿��Ѵ�.
		Boolean wrap_b = Boolean.valueOf(true);
		//											��ü ������ �� ���ڿ��� ���
		System.out.println("���ڿ��� �� :" + wrap_b.toString());
		
		char c = 'A';
		Character wrap_c = new Character(c);
		System.out.println("���� �� :" + wrap_c.toString());
		
		Integer wrap_i = new Integer("10000");
		int i = wrap_i.intValue();
		System.out.println("���� �� :" + i);
		
		double d = 3.14;
		Float wrap_f = new Float(d);
		float f = wrap_f.floatValue();
		System.out.println("�Ǽ� �� : "+f);

		System.out.println("������ �Ǽ��� ���� �� : " + (i+f));
		
		
	}

}
