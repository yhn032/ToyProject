package mymain;

import java.io.FileReader;
import java.io.IOException;

public class _04_����ó��finally {

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
			//������ ����Ǵ� ����
			
			try {
				//ȭ���� ���������� �ݾƶ�
				if(fr!=null) fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
