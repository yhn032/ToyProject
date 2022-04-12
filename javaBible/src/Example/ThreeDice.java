package Example;

import java.util.Scanner;

public class ThreeDice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int dice[] = new int[3];
		int temp=0;
		int count=0;
		int sameNumber=0;
		int reward=0;
		
		for(int i=0; i<dice.length;i++) {
			dice[i] = sc.nextInt();
		}
		
		for(int i=0; i<dice.length;i++) {
			for(int k=i+1 ; k<dice.length ; k++) {
				if(dice[i] > dice[k]) {
					temp = dice[k];
					dice[k] = dice[i];
					dice[i] = temp;
				}
			}
		}
		
		for(int i=0; i<dice.length;i++) {
			for(int k=i+1 ; k<dice.length ; k++) {
				if(dice[i] == dice[k]) {
					sameNumber = dice[i];
					count++;
				}
			}
		}

		switch(count) {
		case 0:
			reward = dice[2] * 100;
			break;
		case 1:
			reward = sameNumber * 100 + 1000;
			break;
		default:
			reward = sameNumber * 1000 + 10000;
			break;
		}
		
		System.out.println(reward);

		
		sc.close();
	}

}
