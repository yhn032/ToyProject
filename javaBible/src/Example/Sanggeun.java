package Example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sanggeun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//����̰� ���� ī�� ���
		int sangCard = sc.nextInt();
		int arr[] = new int[sangCard];
		
		//����̰� ���� ī�� ���
		for(int i=0; i<sangCard; i++) {
			arr[i] = sc.nextInt();
		}
		
		//�̺�Ž���� ���� ������ �ʼ�
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();

		//�־��� ī���� ���
		int givenCard = sc.nextInt();
		
		for(int i=0; i < givenCard; i++) {
			int key = sc.nextInt(); //�־��� ī���� ���� ���. ����̰� ���� ī�� ���(Ž������)���� ã�ƾ� �� key���̴�.
			
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
		
		//lb�� ub�� key���� ã�ư��鼭 mid�� �̵�
		while(lb < ub) {
			int mid = lb + (ub-lb)/2;
			
			//key���� arr[mid]���� ���� ��� ������ ��������
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
		
		//lb�� ub�� key���� ã�ư��鼭 mid�� �̵�
		while(lb < ub) {
			int mid = lb + (ub-lb)/2;
			
			//key���� arr[mid]���� �۰ų� ���� ��� ������ ��������
			if(key <= arr[mid]) {
				ub = mid;
			} else {// key���� arr[mid]���� ū ��� ������ �ø����� mid���� ũ�ų� ���� ���� �ƴ϶� ũ�� ������ + 1����� �Ѵ�.
				lb = mid + 1;
			}
		}
		
		return lb;
	}
}
