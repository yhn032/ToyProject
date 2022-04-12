package extend;

class Parent {
	String msg = "Parent 클래스";
	public String getMsg() {
		return msg;
	}
}

class Child extends Parent{
	String msg = "Child 클래스";
	public String getMsg() { //메소드 오버라이딩
		return msg;
	}
}
public class overriddingEx {
	public static void main(String[] args) {
		Child cd = new Child();
		System.out.println("cd : " + cd.getMsg());
		
		Parent pt = new Child();
		System.out.println("pt : " + pt.getMsg());
	}
}

/*
 * 메소드 오버라이딩 -> 서브 클래스에서 슈퍼클래스에 선언된 메소드를 중복 작성하여 수퍼클래스에 작성된 메소드를 무력화 시키고, 자신이 객체의 주인 노릇을 한다. 
 * 덮어쓰기로 이해하면 편함
 * 어떤 레퍼런스를 이용하던 항상 서브클래스에 오버라이딩한 메소드가 실행된다. 
 * 하나의 인터페이스(같은 이름)에 서로 다른 내용 구현이라는 객체 지향의 다형성을 실현하는 도구이다.
 * 동적바인딩을 통해 실행된다. -> 실행하는 메소드를 컴파일 시가 아니라 실행 시에 결정하는 것
 * 동적바인딩을 통해서 항상 오버라이딩된 메소드가 실행되도록 보장하지만, super키워드를 이용하면 정적바인딩을 통해 수퍼클래스에 멤버에 접근할 수 있다.
 * */



/*
				오버라이딩(재정의) 															VS																	오버로딩(다중 정의)
				상속관계																	적용																특정 클래스
				슈퍼클래스의 메서드 <= 서브 클래스의 메서드									접근제한															상관x
				같다																		리턴형																상관x
				슈퍼 클래스의 메서드명 = 서브클래스의 메서드 명								메서드명															반드시 같아야 한다
				반드시 같아야 한다.															인자(타입, 개수)													반드시 달라야 한다.
 */