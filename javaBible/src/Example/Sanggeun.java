package Example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sanggeun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//상근이가 가진 카드 장수
		int sangCard = sc.nextInt();
		int arr[] = new int[sangCard];
		
		//상근이가 가진 카드 목록
		for(int i=0; i<sangCard; i++) {
			arr[i] = sc.nextInt();
		}
		
		//이분탐색을 위해 정렬은 필수
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();

		//주어진 카드의 장수
		int givenCard = sc.nextInt();
		
		for(int i=0; i < givenCard; i++) {
			int key = sc.nextInt(); //주어진 카드의 숫자 목록. 상근이가 가진 카드 목록(탐색구간)에서 찾아야 할 key값이다.
			
			//upper - lower
			sb.append(upperBound(arr, key) - lowerBound(arr, key));
			sb.append(" ");
		}
		System.out.println(sb);
	}

	private static int upperBound(int[] arr, int key) {
		// TODO Auto-generated method stub
		int lb = 0;
		int ub = arr.length;
		
		//lb와 ub를 key값을 찾아가면서 mid로 이동
		while(lb < ub) {
			int mid = lb + (ub-lb)/2;
			
			//key값이 arr[mid]보다 작은 경우 상한을 내리도록
			if(key < arr[mid]) {
				ub = mid;
			} else {
				lb = mid + 1;
			}
		}
		
		return lb;
	}
	

	private static int lowerBound(int[] arr, int key) {
		// TODO Auto-generated method stub
		int lb = 0;
		int ub = arr.length;
		
		//lb와 ub를 key값을 찾아가면서 mid로 이동
		while(lb < ub) {
			int mid = lb + (ub-lb)/2;
			
			//key값이 arr[mid]보다 작거나 같은 경우 상한을 내리도록
			if(key <= arr[mid]) {
				ub = mid;
			} else {// key값이 arr[mid]보다 큰 경우 하한을 올리도록 mid보다 크거나 같은 것이 아니라 크기 때문에 + 1해줘야 한다.
				lb = mid + 1;
			}
		}
		
		return lb;
	}
}
