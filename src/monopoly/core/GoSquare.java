package monopoly.core;

public class GoSquare extends Square {

	public GoSquare(String name, int index) {
		super(name, index);
	}

	public void landedOn(Player p) {
		p.addCash(200);
	}

}
