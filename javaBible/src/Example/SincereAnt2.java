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
		arr[row][col] = 9; // ������ �������

		while (true) {

			// ��, �� ��� ���� ����
			if (arr[row][col + 1] == 1) {
				if (arr[row + 1][col] == 1) {
					arr[row][col] = 9;
					break; // ������ �����Ƿ� ���ڸ����� ����
				} else
					row++;// �����ʸ� ���� ���� �Ʒ��� �̵�

			} else if (arr[row][col + 1] != 1) // �������� �շ��ִٸ�
				col++; // ���������� �̵�

			if (arr[row][col] == 2) {
				arr[row][col] = 9; // ���̸� ã�Ҵٸ� 9�� ��� ����
				break;
			}
			arr[row][col] = 9; // ���̰� �̵��ϴ� ������ ��� 9�� ����
		}
		

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}


}
