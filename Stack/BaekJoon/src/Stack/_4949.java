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
			
			//여는 괄호가 아니라면 문자이거나, 공백이거나, 닫는 괄호
			else if(c==')') {
				//짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다. -> 짝을 이룰 수 있는 오른쪽 괄호가 입력되었을때는 반드시 스택의 최상단(가장 최근에 push된)에는 짝이 맞는 왼쪽괄호가 있어야 한다.
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
