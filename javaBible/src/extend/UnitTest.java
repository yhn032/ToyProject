package extend;

abstract class Unit {
	protected String name;
	protected int energy;
	abstract public void decEnergy();
	public int getEnergy() {
		return energy;
	}
}

class Terran extends Unit{
	boolean fly;
	public Terran(String n, boolean b) {
		name = n;
		energy = 100;
		fly = b;
	}
	public void decEnergy() {
		energy -= 3;
	}
}

class Zerg extends Unit{
	boolean fly;
	public Zerg(String n, boolean b) {
		name = n;
		energy = 100;
		fly = b;
	}
	public void decEnergy() {
		energy -= 6;
	}
}

class Protoss extends Unit{
	boolean fly;
	public Protoss(String n, boolean b) {
		name = n;
		energy = 100;
		fly = b;
	}
	public void decEnergy() {
		energy --;
	}
}
public class UnitTest {
	public static void main(String[] args) {
		Zerg z1 = new Zerg("Hydralisk", false);
		z1.decEnergy();
		System.out.println("z1's Energy : " + z1.getEnergy());
		
		Protoss p1 = new Protoss("Corsair", true);
		p1.decEnergy();
		System.out.println("p1's Energy : " + p1.getEnergy());
		
		Terran t1 = new Terran("Marine", false);
		t1.decEnergy();
		System.out.println("t1's Energy : " + t1.getEnergy());
	}
}
