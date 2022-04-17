package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _4949 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String msg;
		
		while(true) {
			msg = br.readLine();
			
			if(msg.equals(".")) {
				break;
			}
			
			sb.append(VSP(msg)).append('\n');
		}
		System.out.println(sb);
	}

	private static Object VSP(String msg) {
		// TODO Auto-generated method stub
		Stack<Character> st = new Stack<Character>();
		
		for(int i=0; i<msg.length();i++) {
			char c = msg.charAt(i);
			
			if(c=='(' || c=='[') {
				st.push(c);
			}
			
			//���� ��ȣ�� �ƴ϶�� �����̰ų�, �����̰ų�, �ݴ� ��ȣ
			else if(c==')') {
				//¦�� �̷�� �� ��ȣ�� ���� ��, �� ���̿� �ִ� ���ڿ��� ������ ������ �Ѵ�. -> ¦�� �̷� �� �ִ� ������ ��ȣ�� �ԷµǾ������� �ݵ�� ������ �ֻ��(���� �ֱٿ� push��)���� ¦�� �´� ���ʰ�ȣ�� �־�� �Ѵ�.
				if(st.empty() || st.peek() != '(') {
					return "no";
				}else {
					st.pop();
				}
			}
			
			else if(c==']') {
				if(st.empty() || st.peek() != '[') {
					return "no";
				}else {
					st.pop();
				}
			}
		
		}
		if(st.empty()) {
			return "yes";
		}else {
			return "no";
		}

	}

}
