package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9012 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		
		while(T --> 0) {
			sb.append(VSP(br.readLine())).append('\n');
		}
		
		System.out.println(sb);
	}

	private static String VSP(String next) {
		// TODO Auto-generated method stub
		int count = 0;
		
		for(int i=0 ; i<next.length(); i++) {
			char c = next.charAt(i);
			if(c == '(') {
				count++;
			}
			
			else if(count ==0){//���ÿ� �ƹ��͵� ���� ��� pop�� �õ��ϸ� ������ ���´�. ��, �ݴ� ��ȣ�� �޾Ҵµ� pop�� ���Ұ� ���� ���
				return "NO";
			}else {
				count--;
			}
		}
		
		
		if(count==0) {
			return "YES";
		}else {
			return "NO";
		}

	}

}
