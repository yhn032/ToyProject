package Class;

public class wrapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//기본 자료형인 변수의 값이 객체가 되어 객체로서 작업을 수행하게 한다.
		//객체형만 저장할 수 있는 Vector에 기본자료형을 저장하려면 Wrapper클래스로 객체화를 시켜주어야 한다. 
		//하지만 이미 객체화 한 것에 대한 수정작업은 할 수 없다. 수정한 기본자료형을 다시 객체화 해주어야 하는 것이다.
		boolean b = true; 
		//변수 b의 객체화
		//Boolean wrap_b = new Boolean(b); //java 9부터 Wrapper 객체를 생성할 때, 생성자를 이용하는 방법을 폐기하고 작은 메모리와 빠른 속도의 valueOf() 정적 메소드를 이용한다.
		Boolean wrap_b = Boolean.valueOf(true);
		//											객체 내부의 값 문자열로 출력
		System.out.println("문자열의 값 :" + wrap_b.toString());
		
		char c = 'A';
		Character wrap_c = new Character(c);
		System.out.println("문자 값 :" + wrap_c.toString());
		
		Integer wrap_i = new Integer("10000");
		int i = wrap_i.intValue();
		System.out.println("정수 값 :" + i);
		
		double d = 3.14;
		Float wrap_f = new Float(d);
		float f = wrap_f.floatValue();
		System.out.println("실수 값 : "+f);

		System.out.println("정수와 실수의 연산 값 : " + (i+f));
		
		
	}

}
