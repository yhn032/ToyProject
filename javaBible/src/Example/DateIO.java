package Example;

import java.util.Scanner;

public class DateIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String date = sc.nextLine();
		
		String [] io = date.split("[.]");
		//String [] io = date.split("\\.");
		int [] dateOfNumber = new int[io.length];
		
		for(int i=0; i<io.length; i++) {
			dateOfNumber[i] = Integer.parseInt(io[i]);
		}
		
		System.out.printf("%04d.%02d.%02d", dateOfNumber[0], dateOfNumber[1], dateOfNumber[2]);

		
		
	}

}
