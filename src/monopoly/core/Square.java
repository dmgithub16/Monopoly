package monopoly.core;

public abstract class Square {

	private String removeMe;
	private String name;
	private Square nextSquare;
	private int index;

	public Square(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public void setNextSquare(Square s) {
		nextSquare = s;
	}

	public Square getNextSquare() {
		return nextSquare;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public String toString() {
		return "Square#" + index;
	}

	public abstract void landedOn(Player p);

}
