package Example;

import java.util.Random;

public class SincereAnt {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[][] = new int[10][10];
		
		arr = make_Miro();
		for(int i=0;i<10; i++) {
			for(int j=0; j<10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int row=1, col=1;
		//arr[row][col] = 9;						//������ �������
		
		while(true) {
			
			if(arr[row][col] == 2) {// ������������ ���̸� ã�Ҵٸ� 9�� ��� ����
				arr[row][col] = 9;	
				break;
			}
			//��, �� ��� ���� ����
			if(arr[row][col+1] == 1) {
				if(arr[row+1][col]==1) {
					arr[row][col] = 9;
					break; //������ �����Ƿ� ���ڸ����� ����
				} else
					row++;//�����ʸ� ���� ���� �Ʒ��� �̵�
				
			}else if(arr[row][col+1] != 1) //�������� �շ��ִٸ�
				col++;						//���������� �̵�
			
			if(arr[row][col] == 2) {
				arr[row][col] = 9;			//���̸� ã�Ҵٸ� 9�� ��� ����
				break;
			}
			arr[row][col] = 9;				//���̰� �̵��ϴ� ������ ��� 9�� ����
		}
		
		
		for(int i=0;i<10; i++) {
			for(int j=0; j<10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int[][] make_Miro() {
		Random rd = new Random();
		int [][] arr = new int[10][10];
		int count = 0;
		//�迭�� ��� ��Ҹ� 1�� ��
		for(int i=0;i<10; i++) {
			for(int j=0; j<10; j++) {
				arr[i][j] = 1;
			}
			System.out.println();
		}
		
		//�׵θ��� ������ ���δ� 0�Ǵ� 1�� ���� ���� 
		for(int i=1;i<9; i++) {
			for(int j=1; j<9; j++) {
				int a = rd.nextInt(2);
				arr[i][j] = a;
				int colIndex = rd.nextInt(8) + 1;
				arr[i][colIndex] = 0; //��ֹ� �ּ�ȭ
			}		
			System.out.println();
		}
		
		
		//������ ��ġ�� ���̸� �α� 
		int rowRandom = rd.nextInt(8) + 1;
		int colRandom = rd.nextInt(8) + 1;
		arr[rowRandom][colRandom] = 2;
				
		return arr;
	}
}
