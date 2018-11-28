package monopoly.core;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private static final int SIZE = 40;
	private List<Square> squares = new ArrayList<Square>(SIZE);
	Square jailSquare;

	public Board() {
		buildSquares();
		linkSquares();
	}

	public Square getSquare(Square start, int distance) {
		int endIndex = (start.getIndex() + distance) % SIZE;
		return (Square) squares.get(endIndex);
	}

	public Square getStartSquare() {
		return (Square) squares.get(0);
	}

	private void buildSquares() {
		for (int i = 1; i <= SIZE; i++) {
			build(i);
		}
	}

	private void build(int i) {
		Square s;
		switch (i) {
		case 1:
			s = new GoSquare("go", 0);
			break;
		case 5:
			s = new IncomeTaxSquare("income tax", 4);
			break;
		case 11:
			s = new RegularSquare("Jail", 10);
			jailSquare = s;
			break;
		case 31:
			s = new GoSquare("GOTO JAIL", 30);
			break;
		default:

			s = new RegularSquare("Square " + i, i - 1);
		}
		squares.add(s);
	}

	private void linkSquares() {
		for (int i = 0; i < (SIZE - 1); i++) {
			link(i);
		}

		Square first = (Square) squares.get(0);
		Square last = (Square) squares.get(SIZE - 1);
		last.setNextSquare(first);
	}

	private void link(int i) {
		Square current = (Square) squares.get(i);
		Square next = (Square) squares.get(i + 1);
		current.setNextSquare(next);
	}
}
