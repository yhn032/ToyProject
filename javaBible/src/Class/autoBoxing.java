package Class;

public class autoBoxing {
	Integer var;
	
	public Integer getInt() {
		return var;
	}
	/*래퍼 클래스 타입의 멤버변수인 var에 인자로 넘어온 정수값을 넣어주는 부븐이 이해하기 힘들다.
	 *JDK 5.0이후로 컴파일러가 내부적으로 Integer라는 wrapper 객체를 생성하여 대입하는 과정까지 대신해주므로 코드의 내용이 간편해진것이다.
	 */
	public void setInt(int i) {
		//자동 박싱 
		//var = Integer.valueOf(i);
		//컴파일러가 기본 자료형 변수 i를 자동으로 객체화 시켜줌 -> 자동 박싱
		var = i;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		autoBoxing a1 = new autoBoxing();
		a1.setInt(10000);
		//int res = var.intValue();
		//컴파일러가 객체 자료형 변수 var를 자동으로 기본자료형으로 바꿔줌 -> 자동 언박싱
		int res = a1.getInt();
		System.out.println("res : " + res);
		
		//박싱
		//기본 타입의 값을 객체로 변환하는 것; 
		Integer ten = Integer.valueOf(10);
		
		//언박싱
		//Wrapper 객체를 기본 타입의 값으로 되돌리는 것
		int n = ten.intValue();
	}

}
