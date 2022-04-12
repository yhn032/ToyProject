package Example;

import java.util.Scanner;

public class SugarSnack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int row, col, n =0;
		row = sc.nextInt();
		col = sc.nextInt();
		n = sc.nextInt();
		int l[] = new int[n];
		int d[] = new int[n];
		int x[] = new int[n];
		int y[] = new int[n];
		for(int i=0; i<n;i++) {
			l[i] = sc.nextInt();
			d[i] = sc.nextInt();
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}

		int pan[][] = new int[row][col];
		for(int i=0 ;i<row; i++) {
			for(int k=0; k<col; k++) {
				pan[i][k] = 0;
			}
		}
		
		
		for(int i=0; i<n ;i++) {
			//°¡·Î
			if(d[i]==0) {
				for(int k =0; k<l[i];k++) {
					pan[x[i]-1][y[i]-1] = 1;
					y[i]++;
				}
			}else if(d[i] == 1) {
				for(int t=0; t<l[i] ;t++) {
					pan[x[i]-1][y[i]-1] = 1;
					x[i]++;
				}
			}
		}
		
		for(int i=0; i<pan.length; i++) {
			for(int k=0; k<pan[i].length; k++) {
				System.out.printf("%d ", pan[i][k]);
			}
			System.out.println();
		}
		
		sc.close();
	}
}
