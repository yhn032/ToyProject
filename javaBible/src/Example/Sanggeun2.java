package Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Sanggeun2 {

	public static void main(String[] args) throws Exception {
		//키보드 버퍼 -> 인풋스트림 -> 버퍼리더 필터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		//상근이가 가지고 있는 카드장 수
		int N = Integer.parseInt(br.readLine());
		
		//카드 목록을 공백을 기준으로 분해
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			//key는 내가 찾고자 하는 값이다. 
			int key = Integer.parseInt(st.nextToken());
			
			/*
				key에는 사용자가 입력하는 원소 즉, 비교 대상이 될 상근이가 가지고 있는 카드의 목록이다.(위에서 분해한 것)
				value에는 중복으로 입력된 원소 즉, 상근이가 가지고 있는 카드중에서 중복된 카드
		
			*/
			//					key값에 해당하는 value가 있으면 그 value를 넣고 + 1(중복 카운팅이므로 한 번들어갈때마다 +1)
			//					만일 없으면, 0을 반환한다. 값이 없었고, 최초로 들어갔으므로 첫 카운팅으로 +1
			map.put(key, map.getOrDefault(key, 0)+1);
		}
		
		//주어진 카드의 수
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		//위에서 사용한 상근이가 가진 카드의 목록은 이미 map에 다 저장했으니 주어진 M장의 카드 목록을 분해해서 저장해야 하므로 st를 새롭게 초기화한다.
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<M; i++) {
			int key = Integer.parseInt(st.nextToken());
			
			sb.append(map.getOrDefault(key, 0));
			sb.append(" ");
			
		}
		System.out.println(sb);
	}
	
}
