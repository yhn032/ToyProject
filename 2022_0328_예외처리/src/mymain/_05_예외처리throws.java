package mymain;

import java.util.ArrayList;

import myutil.MyArrayList;

public class _05_����ó��throws {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Collection <- �ڹ��� ��� ��ü�� ������ �� �ִ�.
		//
		
		//ArrayList(�����迭)
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
		
		//��ü������ �о���� 
		for(int i=0; i<list.size();i++) {
			int m = (int)list.get(i);
			System.out.printf("index -> %d : %d\n",i, m);
		}
		
		//���� : index -> 1
		list.remove(1);
		System.out.printf("list's size()=%d\n", list.size());
		System.out.println("---������ ������---");
		//�߰� ���� �����Ǹ� �ɵ������� ���ڸ��� ä�쵵�� �ε����� ���Ѵ�.
		for(int i=0; i<list.size();i++) {
			int m = (int)list.get(i);
			System.out.printf("index -> %d : %d\n",i, m);
		}
		
		
		System.out.println();
		//--------------------------------------------------------------------------
		//���� ���� ��̸���Ʈ 
		MyArrayList list1 = new MyArrayList();
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(10);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(20);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		list1.add(30);
		System.out.printf("list1's size()=%d\n", list1.size());
		
		try {
			//index 1�� ��ü���� ���´�
			int index = 2;
			int n1 = (int) list1.get(index);
			System.out.println(n1);
			
			
			
			System.out.println("---������ ������---");
			for(int i=0; i <list1.size(); i++) {
				int m1 = (int)list1.get(i);
				System.out.printf("index:%d -> %d\n", i, m1);
			}
			
			
			
			index = 1; //����
			list1.remove(index);
			
			
			System.out.println("---������ ������---");
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
