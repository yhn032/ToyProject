package Example;

import java.util.Scanner;

public class SincereAnt2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int arr[][] = new int[10][10];

		for (int i = 0; i < 10; i++) {
			String rowIndex = sc.nextLine();
			String [] colIndex = rowIndex.split(" ");
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(colIndex[j]);
			}
		}


		int row = 1, col = 1;
		arr[row][col] = 9; // 개미의 출발지점

		while (true) {

			// 우, 하 모두 막힌 상태
			if (arr[row][col + 1] == 1) {
				if (arr[row + 1][col] == 1) {
					arr[row][col] = 9;
					break; // 갈곳이 없으므로 그자리에서 종료
				} else
					row++;// 오른쪽만 막힌 상태 아래로 이동

			} else if (arr[row][col + 1] != 1) // 오른쪽이 뚫려있다면
				col++; // 오른쪽으로 이동

			if (arr[row][col] == 2) {
				arr[row][col] = 9; // 먹이를 찾았다면 9를 찍고 종료
				break;
			}
			arr[row][col] = 9; // 개미가 이동하는 공간에 모두 9를 저장
		}
		

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}


}
