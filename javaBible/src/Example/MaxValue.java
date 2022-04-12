package Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MaxValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> value =  new ArrayList<Integer>();
		Map<Integer, Integer> gap = new HashMap<Integer, Integer>();
		
		value = makeValue();
		gap   = findMax(value);
		
		System.out.println(value);
		System.out.println();
		
		Set<Integer> keys = gap.keySet();
		for(Integer n : keys) {
			System.out.printf("ÃÖ´ñ°ª        : %d\n", n);
			System.out.printf("ÃÖ´ñ°ªÀÇ À§Ä¡ : %d", gap.get(n));	
		}
		
	}																							
																				 //value//4 3 10 3 5 2
	private static Map<Integer, Integer> findMax(List<Integer> value) {		 //index//0 1 2  3 4 5
		// TODO Auto-generated method stub
		Map<Integer, Integer> mm = new HashMap<Integer, Integer>();
		
		Iterator<Integer> it = value.iterator();
		int max =0;
		int count = 0, targetCount = 0;
		while(it.hasNext()) {
			int temp = it.next();	
			if (max < temp) {
				max = temp;
				targetCount = count;
			}
			count++;
		}
		mm.put(max, targetCount+1);
		return mm;
	}
	
	private static List<Integer> makeValue() {
		// TODO Auto-generated method stub
		Random rd = new Random();
		List<Integer> value =  new ArrayList<Integer>();
		
		OUT:
		for(int i=0; i<9;i++) {
				
			int arr =  rd.nextInt(99) + 1;
			for(int j=0; j<i;j++) {
				if(arr == value.get(j)) {
					i--;
					continue OUT;
				}
			}
			value.add(arr);
		}
		return value;
	}

}
