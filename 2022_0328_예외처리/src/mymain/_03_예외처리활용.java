package mymain;

import java.util.Scanner;

public class _03_����ó��Ȱ�� {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int dan;
		
		while(true) {
			try {
				System.out.printf("��:");
				dan = sc.nextInt();
				
				if(dan <2 || dan >9) {
					System.out.println("2~9������ ���ڸ� �Է��ϼ���");
					continue;
					//throw new Exception();
				}
				
				for(int i=1; i<=9 ; i++) {
					System.out.printf("%d x %d = %d\n", dan, i , dan*i);	
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("2~9������ ���ڸ� �Է��ϼ���!");
				sc.nextLine();
				continue;
			}
			
			
			
			
			
			
		}
	}
}
