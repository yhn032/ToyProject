package mymain;

import java.util.ArrayList;

import myutil.MyArrayList;

public class _05_예외처리throws {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Collection <- 자바의 모든 객체를 저장할 수 있다.
		//
		
		//ArrayList(동적배열)
		ArrayList list = new ArrayList();
		
		System.out.printf("list's size()=%d\n", list.size());
		
		//list.add(10); //index : 0
		list.add(new Integer(10)); //AutoBoxing
		System.out.printf("list's size()=%d\n", list.size());

		list.add(20); //index : 1
		System.out.printf("list's size()=%d\n", list.size());

		list.add(30); //index : 2
		System.out.printf("list's size()=%d\n", list.size());

		//Down-casting
		int a = (int)list.get(1);
		System.out.println(a);
		
		//전체데이터 읽어오기 
		for(int i=0; i<list.size();i++) {
			int m = (int)list.get(i);
			System.out.printf("index -> %d : %d\n",i, m);
		}
		
		//삭제 : index -> 1
		list.remove(1);
		System.out.printf("list's size()=%d\n", list.size());
		System.out.println("---삭제후 데이터---");
		//중간 값이 삭제되면 능동적으로 빈자리를 채우도록 인덱스가 변한다.
		for(int i=0; i<list.size();i++) {
			int m = (int)list.get(i);
			System.out.printf("index -> %d : %d\n",i, m);
		}
		
		
		System.out.println();
		//--------------------------------------------------------------------------
		//내가 만든 어레이리스트 
		MyArrayList list1 = new MyArrayList();
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(10);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(20);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(30);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		try {
			//index 1의 객체정보 얻어온다
			int index = 2;
			int n1 = (int) list1.get(index);
			System.out.println(n1);
			
			
			
			System.out.println("---삭제전 데이터---");
			for(int i=0; i <list1.size(); i++) {
				int m1 = (int)list1.get(i);
				System.out.printf("index:%d -> %d\n", i, m1);
			}
			
			
			
			index = 1; //삭제
			list1.remove(index);
			
			
			System.out.println("---삭제후 데이터---");
			for(int i=0; i <list1.size(); i++) {
				int m1 = (int)list1.get(i);
				System.out.printf("index:%d -> %d\n", i, m1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
