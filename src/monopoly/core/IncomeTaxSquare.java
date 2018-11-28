package monopoly.core;

public class IncomeTaxSquare extends Square {

	public IncomeTaxSquare(String name, int index) {
		super(name, index);
	}

	public void landedOn(Player p) {
		long netWorth = p.getNetWorth();
		long tax = netWorth / 10;
		if (tax > 200) {
			tax = 200;
		}
		p.reduceCash(tax);
	}

}
