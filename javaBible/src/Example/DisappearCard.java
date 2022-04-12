package Example;

import java.util.Scanner;

public class DisappearCard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int missingCard[] = new int[N-1];
		for(int i=0; i<N-1; i++) {
			missingCard[i] = sc.nextInt();
		}
		
		int hap1=0;
		for(int k=1; k<=N ; k++) {
			hap1 += k;
		}
		
		int missingHap = 0;
		for(int c : missingCard) {
			missingHap += c;
		}
		int finalHap = hap1 - missingHap;
		
		System.out.println(finalHap);
		
		sc.close();
	}

}
