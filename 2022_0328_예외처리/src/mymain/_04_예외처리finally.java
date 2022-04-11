package mymain;

import java.io.FileReader;
import java.io.IOException;

public class _04_예외처리finally {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fr = null;
		
		
		try {
			System.out.println("---Try---");
			fr = new FileReader("a.txt");
			
			//int res = 10/0;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("---Catch---");
			e.printStackTrace();
		} finally {
			System.out.println("---Finally---");
			//무조건 실행되는 구문
			
			try {
				//화일이 열려있으면 닫아라
				if(fr!=null) fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
