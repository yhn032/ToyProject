package Stack;

import java.util.Scanner;

public class _10773 {
	public static int [] moneyClip;
	public static int size;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		moneyClip = new int[K];
		int sum = 0;
		
		//������ 0�� ��쿡 ���� ���� �ִ� ���� ������ ������ �� �ִ�.(�Է°��� ������ ������ ������� ����Ǽ��� ���ٴ� ��)
		while(K --> 0) {
			int number = sc.nextInt();
			if(number == 0) {
				pop();
			}else {
				push(number);
			}
		}
		
		for(int i=0; i<size; i++) {
			sum += moneyClip[i];
		}
		
		if(size==0) {
			System.out.println("0");
		}else {
			System.out.println(sum);
		}
		
		sc.close();
	}

	private static void push(int number) {
		// TODO Auto-generated method stub
		moneyClip[size] = number;
		size++;
	}

	private static void pop() {
		// TODO Auto-generated method stub
		moneyClip[size-1] = 0;
		size--;
	}
}
