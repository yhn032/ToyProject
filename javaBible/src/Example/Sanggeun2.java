package Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Sanggeun2 {

	public static void main(String[] args) throws Exception {
		//Ű���� ���� -> ��ǲ��Ʈ�� -> ���۸��� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		//����̰� ������ �ִ� ī���� ��
		int N = Integer.parseInt(br.readLine());
		
		//ī�� ����� ������ �������� ����
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			//key�� ���� ã���� �ϴ� ���̴�. 
			int key = Integer.parseInt(st.nextToken());
			
			/*
				key���� ����ڰ� �Է��ϴ� ���� ��, �� ����� �� ����̰� ������ �ִ� ī���� ����̴�.(������ ������ ��)
				value���� �ߺ����� �Էµ� ���� ��, ����̰� ������ �ִ� ī���߿��� �ߺ��� ī��
		
			*/
			//					key���� �ش��ϴ� value�� ������ �� value�� �ְ� + 1(�ߺ� ī�����̹Ƿ� �� ���������� +1)
			//					���� ������, 0�� ��ȯ�Ѵ�. ���� ������, ���ʷ� �����Ƿ� ù ī�������� +1
			map.put(key, map.getOrDefault(key, 0)+1);
		}
		
		//�־��� ī���� ��
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		//������ ����� ����̰� ���� ī���� ����� �̹� map�� �� ���������� �־��� M���� ī�� ����� �����ؼ� �����ؾ� �ϹǷ� st�� ���Ӱ� �ʱ�ȭ�Ѵ�.
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<M; i++) {
			int key = Integer.parseInt(st.nextToken());
			
			sb.append(map.getOrDefault(key, 0));
			sb.append(" ");
			
		}
		System.out.println(sb);
	}
	
}
