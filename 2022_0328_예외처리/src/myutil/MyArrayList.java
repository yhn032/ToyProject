package myutil;

public class MyArrayList {
	
	//자바의 모든 객체를 저장할 수 있는 타입
	Object [] data = null;
	public int size() {
		//배열의 개수가 몇이냐?
		return (data==null)? 0 : data.length; 
	}
	
	public void add(Object ob) {
		//기본갯수+1
		Object [] in = new Object[this.size()+1];
		
		//기존데이터 in배열로 옮기기 
		for(int i=0; i<size(); i++) {
			in[i] = data[i];
		}
		
		//in배열의 마지막 칸에 추가할 데이터를 넣는다.
		in[size()] = ob;
		
		//in배열 = 기존배열(data) + 1
		data = in;
	}
	
	//get메소드 내에서 던져진 예외에 대한 처리는 해당 메소드를 사용하는 사용자가 
	//처리해라라는 예외처리 양도의 의미이다,(throws Exception)
	//발생된 내부 문제를 바깥쪽으로 알려주는 의도
	//내부에서는 발생된 오류를 알고 있지만 내부에서 처리해서 내보내면 예외처리가 안한게 된다.
	//따라서 에러에 대한 내용을 사용자 측에 알림으로써 사용자가 오류 정보를 알 수 있도록  한다.
	public Object get(int index) throws Exception{
		//안되는 조건
		if(index < 0 || index >=size()) {
			String message = String.format("사용범위:0-%d 사용된 index:%d", size()-1, index);
			throw new Exception(message);
		}
			
		
		return data[index];
	}
	
	
	public void remove(int index) throws Exception{
		//안되는 조건
		if(index < 0 || index >=size()) {
			String message = String.format("사용범위:0-%d 사용된 index:%d", size()-1, index);
			throw new Exception(message);
		}
		
		//데이터가 1개만 있을 경우
		if(size()==1) {
			data = null;
			return;
		}
		
		//기존 데이터보다 1개 적게 생성
		Object [] im = new Object[size()-1];
		
		for(int i=0; i <im.length; i++) {
			if(i<index) //삭제할 index보다 적으면 그대로 복사
				im[i] = data[i];
			else
				im[i] = data[i+1]; //index 건너띈 값
		}
		data = im;
	}
}
