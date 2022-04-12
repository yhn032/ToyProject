package Example;

import java.util.Scanner;

public class FstGrade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int data = sc.nextInt();
		String[][] student = new String[data][4];
		// enter 값 삭제
		sc.nextLine();

		// 데이터를 2차원 배열로 set
		for (int i = 0; i < data; i++) {
			String rowIndex = sc.nextLine();
			String[] colIndex = rowIndex.split(" ");
			for (int k = 0; k < 4; k++) {
				student[i][k] = colIndex[k];
			}
		}

		/*
		 * System.out.println(data); for(int i=0; i<data; i++) { for(int k=0; k<4 ; k++)
		 * { System.out.print(student[i][k] + " "); } System.out.println(); }
		 */

		// 1등한 학생의 이름 찾기 (최댓값 찾기)
		int max = 0;
		int row = 0;
		for (int i = 0; i < data; i++) {
			if (Integer.parseInt(student[i][1]) > max) {
				max = Integer.parseInt(student[i][1]);
				row = i;
			}
		}
		String first = student[row][0];
		System.out.print(first + " ");

		// 2과목의 등수 구하기
		int [] secondScore = new int[data];
		int [] rank1 = new int[data];
		for(int i=0; i<data ; i++) {
			rank1[i] =1;
		}
		
		for(int i=0; i<secondScore.length;i++) {
			secondScore[i] = Integer.parseInt(student[i][2]);
			//System.out.println(secondScore[i]);

		}
		
		for(int i=0; i<secondScore.length; i++) {
			rank1[i] = 1;
			for(int k=0; k<secondScore.length; k++) {
				if(secondScore[i] < secondScore[k]) {
					rank1[i] += 1;
				}
			}
			//System.out.println(rank1[i]);
		}
		/*
		 * for(int i=0; i<rank1.length;i++) { System.out.println(rank1[i]); }
		 */
		
		
		// 3과목의 등수 구하기
		int [] thirdScore = new int[data];
		int [] rank2 = new int[data];
		
		for(int i=0; i<thirdScore.length;i++) {
			thirdScore[i] = Integer.parseInt(student[i][3]);
			//System.out.println(secondScore[i]);
			
		}
		
		for(int i=0; i<thirdScore.length; i++) {
			rank2[i] = 1;
			for(int k=0; k<thirdScore.length; k++) {
				if(Integer.parseInt(student[i][3]) < Integer.parseInt(student[k][3])) {
					rank2[i] += 1;
				}
			}
		}
		/*
		 * for(int i=0; i<rank2.length;i++) { System.out.println(rank2[i]); }
		 */
		
		System.out.print(rank1[row] + " ");
		System.out.print(rank2[row] + " ");
	}

}
