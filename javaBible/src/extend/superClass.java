package extend;
//this -> 특정 객체에서 자기 자신을 참조할 수 있는 유일한 참조변수 
//super -> 현재 객체의 바로 위의 super 클래스를 참조할 수 있는 참조변수

//슈퍼 클래스의 특정 메소드를 서브 클래스에서 똑같이 재정의함으로써 슈퍼클래스의 메서드는 사용할 수 없는 "멤버 은폐"상태가 된다. 
// 이처럼 은폐된 멤버를 서브클래스에서 호출 하거나 혹은 슈퍼 클래스 자체를 참조하고자 할때 super 예약어를 사용한다. 

//super()는 super클래스의 생성자이다.
class Parent1 {
	public Parent1(int var) {
		System.out.println("Parent 클래스");
	}
}
class SuperEx extends Parent1 {
	public SuperEx() {
		super(1);
		System.out.println("SuperEx 클래스");
	}
}
public class superClass {
	public static void main(String[] args) {
		SuperEx se = new SuperEx();
	}
	
}
