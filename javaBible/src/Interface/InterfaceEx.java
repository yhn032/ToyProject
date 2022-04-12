package Interface;

interface InterTest{
	int A = 100;
	int getA();
}
public class InterfaceEx  implements InterTest{
	public int getA() {
		return A;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterTest it1 = new InterfaceEx();
		System.out.println("getA():" + it1.getA());
	}

}
