package Example;

import java.util.Scanner;

public class LoveCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String love = sc.nextLine();
		int count = 0;
		
		for(int i=0; i<love.length()-4;i++) {
			if(love.substring(i,i+4).equalsIgnoreCase("love")) {
				count += 1;
			}
		}
		System.out.println(count);
	}

}
