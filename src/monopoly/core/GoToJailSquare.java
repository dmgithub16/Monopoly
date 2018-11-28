package monopoly.core;

public class GoToJailSquare extends Square {

	Square jailSquare;

	public GoToJailSquare(String name, int index, Square jailSquare) {
		super(name, index);
		this.jailSquare = jailSquare;
	}

	public void landedOn(Player p) {
		p.setLocation(jailSquare);
	}

}
