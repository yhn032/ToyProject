package Example;

import java.util.Scanner;

public class MidiumValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int value[] = new int[3];
		int temp;
		
		for(int i =0; i< value.length;i++) {
			value[i] = sc.nextInt();
		}
		for(int i=0; i<value.length;i++) {
			for(int k=i+1 ; k<value.length ; k++) {
				if(value[i] > value[k]) {
					temp = value[k];
					value[k] = value[i];
					value[i] = temp;
				}
			}
		}
		
		System.out.println(value[1]);
		sc.close();
	}



}
