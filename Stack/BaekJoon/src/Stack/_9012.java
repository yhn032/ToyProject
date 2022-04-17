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
			
			else if(count ==0){//스택에 아무것도 없는 경우 pop을 시도하면 에러가 나온다. 즉, 닫는 괄호를 받았는데 pop할 원소가 없을 경우
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
