package mymain;

public class _02_예외처리방식 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 1;
		int res;
		
		String str = "ABC";
		int len = 0;
		int [] ar = new int[5];
		
		//예외처리방식 
		try {
			//수행해야할 본 코드
			//자바 내부에서 0일때 경우... 내부적으로 예외를 던집니다.
			if (b==0) throw new ArithmeticException("0으로 던지지 말라니까..."); 
			res = a/b;
			
			//자바 내부에서 null일 경우 예외를 던진다.
			if(str == null) throw new NullPointerException("str is null : 참조하는 객체가 없음");
			len = str.length();
			
			//자바 내부에서 예외가 발생하면 예외를 던진다
			int index = 10;
			if(index <0 || index >= ar.length) throw new ArrayIndexOutOfBoundsException("배열범위를 벗어났습니다.");
			
			ar[index] = 100;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			//예측되는 에러처리 구문
			e.printStackTrace();
			//System.out.println("0으로 나눌 수 없습니다.");
		} catch (NullPointerException e ) {
			e.printStackTrace();
			//System.out.println("str is null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("---End---");
	}

}
