package myutil;

public class MyArrayList {
	
	//�ڹ��� ��� ��ü�� ������ �� �ִ� Ÿ��
	Object [] data = null;
	public int size() {
		//�迭�� ������ ���̳�?
		return (data==null)? 0 : data.length; 
	}
	
	public void add(Object ob) {
		//�⺻����+1
		Object [] in = new Object[this.size()+1];
		
		//���������� in�迭�� �ű�� 
		for(int i=0; i<size(); i++) {
			in[i] = data[i];
		}
		
		//in�迭�� ������ ĭ�� �߰��� �����͸� �ִ´�.
		in[size()] = ob;
		
		//in�迭 = �����迭(data) + 1
		data = in;
	}
	
	//get�޼ҵ� ������ ������ ���ܿ� ���� ó���� �ش� �޼ҵ带 ����ϴ� ����ڰ� 
	//ó���ض��� ����ó�� �絵�� �ǹ��̴�,(throws Exception)
	//�߻��� ���� ������ �ٱ������� �˷��ִ� �ǵ�
	//���ο����� �߻��� ������ �˰� ������ ���ο��� ó���ؼ� �������� ����ó���� ���Ѱ� �ȴ�.
	//���� ������ ���� ������ ����� ���� �˸����ν� ����ڰ� ���� ������ �� �� �ֵ���  �Ѵ�.
	public Object get(int index) throws Exception{
		//�ȵǴ� ����
		if(index < 0 || index >=size()) {
			String message = String.format("������:0-%d ���� index:%d", size()-1, index);
			throw new Exception(message);
		}
			
		
		return data[index];
	}
	
	
	public void remove(int index) throws Exception{
		//�ȵǴ� ����
		if(index < 0 || index >=size()) {
			String message = String.format("������:0-%d ���� index:%d", size()-1, index);
			throw new Exception(message);
		}
		
		//�����Ͱ� 1���� ���� ���
		if(size()==1) {
			data = null;
			return;
		}
		
		//���� �����ͺ��� 1�� ���� ����
		Object [] im = new Object[size()-1];
		
		for(int i=0; i <im.length; i++) {
			if(i<index) //������ index���� ������ �״�� ����
				im[i] = data[i];
			else
				im[i] = data[i+1]; //index �ǳʶ� ��
		}
		data = im;
	}
}
