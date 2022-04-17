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
		
		//정수가 0일 경우에 지울 수가 있는 수가 있음을 보장할 수 있다.(입력값의 정보가 스택이 비어있을 경우의수는 없다는 것)
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
