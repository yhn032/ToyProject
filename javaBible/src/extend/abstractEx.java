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
	public int getA() {			//메소드 오버라이딩
		return a;					//상속받은 슈퍼클래스의 멤버변수 a
	}
		public abstract String getStr();
	
}

//추상 클래스 간의 상속관계에서는 추상 메소드를 반드시 재정의 할 필요는 없다. 그냥 상속받은 채로 가지고만 있다가 나중에 일반 클래스와 상속관계가 이루어 질때, 일반 클래스에서 재정의를 하면 된다.


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
