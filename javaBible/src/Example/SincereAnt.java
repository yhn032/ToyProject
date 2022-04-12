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
		//arr[row][col] = 9;						//개미의 출발지점
		
		while(true) {
			
			if(arr[row][col] == 2) {// 시작지점에서 먹이를 찾았다면 9를 찍고 종료
				arr[row][col] = 9;	
				break;
			}
			//우, 하 모두 막힌 상태
			if(arr[row][col+1] == 1) {
				if(arr[row+1][col]==1) {
					arr[row][col] = 9;
					break; //갈곳이 없으므로 그자리에서 종료
				} else
					row++;//오른쪽만 막힌 상태 아래로 이동
				
			}else if(arr[row][col+1] != 1) //오른쪽이 뚫려있다면
				col++;						//오른쪽으로 이동
			
			if(arr[row][col] == 2) {
				arr[row][col] = 9;			//먹이를 찾았다면 9를 찍고 종료
				break;
			}
			arr[row][col] = 9;				//개미가 이동하는 공간에 모두 9를 저장
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
		//배열의 모든 요소를 1로 셋
		for(int i=0;i<10; i++) {
			for(int j=0; j<10; j++) {
				arr[i][j] = 1;
			}
			System.out.println();
		}
		
		//테두리를 제외한 내부는 0또는 1의 값을 저장 
		for(int i=1;i<9; i++) {
			for(int j=1; j<9; j++) {
				int a = rd.nextInt(2);
				arr[i][j] = a;
				int colIndex = rd.nextInt(8) + 1;
				arr[i][colIndex] = 0; //장애물 최소화
			}		
			System.out.println();
		}
		
		
		//임의의 위치에 먹이를 두기 
		int rowRandom = rd.nextInt(8) + 1;
		int colRandom = rd.nextInt(8) + 1;
		arr[rowRandom][colRandom] = 2;
				
		return arr;
	}
}
