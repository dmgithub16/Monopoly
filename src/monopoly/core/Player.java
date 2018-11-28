package monopoly.core;

public class Player {
	private String name;
	private long cash;
	private Square square;
	private Board board;
	private Die[] dice;

	public Player(String name, Die[] dice, Board board) {
		this.name = name;
		this.dice = dice;
		this.board = board;
		square = board.getStartSquare();
		cash = MonopolyGame.START_CASH;
	}

	public void takeTurn() {
		// roll dice
		int rollTotal = 0;
		for (int i = 0; i < dice.length; i++) {
			dice[i].roll();
			rollTotal += dice[i].getFaceValue();
		}

		setLocation(board.getSquare(square, rollTotal));

	}

	public void setLocation(Square square) {
		square.landedOn(this);
		this.square = square;
	}

	public Square getLocation() {
		return square;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + " at " + square + " with $" + cash;
	}

	public void addCash(long amount) {
		cash += amount;
	}

	public long getNetWorth() {
		return cash;
	}

	public void reduceCash(long tax) {
		cash -= tax;
	}

}
