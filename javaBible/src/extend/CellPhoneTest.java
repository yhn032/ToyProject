package extend;

public class CellPhoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		D_caPhone dca = new D_caPhone("IN-7600","011-9XXX-9XXXX", 60, "400만");
		MP3Phone mp = new MP3Phone("KN-600","011-9XXX-9XXXX", 60, 256);
		
		//D_caPhone과 MP3Phone클래스에는 게터 세터 메소드가 없지만, 최상위 클래스인 CellPhone클래스에서 상속받은 메소드를 공유하여 사용한 것이다. 
		// 이처럼 상속관계에 있는 클래스들은 자신에게 없는 멤버이더라도 수퍼 클래스의 변수와 메소드를 사용할 수 있다. 
		// 단, 수퍼 클래스는 서브 클래스의 멤버에 접근할 수 없다. 
		// 단일 상속 : 하나의 클래스는 하나의 수퍼 클래스만 가질 수 있다. 
		// 다중 상속 : 하나의 클래스가 두 개이상의 수퍼 클래스를 가질 수 있는 것이다.
		System.out.println(dca.getModel() + "," + dca.getChord() + "," + dca.getNubmer());
	}

}
