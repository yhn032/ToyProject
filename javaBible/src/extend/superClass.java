package extend;
//this -> Ư�� ��ü���� �ڱ� �ڽ��� ������ �� �ִ� ������ �������� 
//super -> ���� ��ü�� �ٷ� ���� super Ŭ������ ������ �� �ִ� ��������

//���� Ŭ������ Ư�� �޼ҵ带 ���� Ŭ�������� �Ȱ��� �����������ν� ����Ŭ������ �޼���� ����� �� ���� "��� ����"���°� �ȴ�. 
// ��ó�� ����� ����� ����Ŭ�������� ȣ�� �ϰų� Ȥ�� ���� Ŭ���� ��ü�� �����ϰ��� �Ҷ� super ���� ����Ѵ�. 

//super()�� superŬ������ �������̴�.
class Parent1 {
	public Parent1(int var) {
		System.out.println("Parent Ŭ����");
	}
}
class SuperEx extends Parent1 {
	public SuperEx() {
		super(1);
		System.out.println("SuperEx Ŭ����");
	}
}
public class superClass {
	public static void main(String[] args) {
		SuperEx se = new SuperEx();
	}
	
}
