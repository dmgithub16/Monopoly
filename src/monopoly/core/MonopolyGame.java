package monopoly.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonopolyGame {
	public static final long START_CASH = 1500;
	private int roundsPlayed = 0;
	private int totalRounds;
	private List<Player> players;
	private Board board = new Board();
	private Die[] dice = { new Die(), new Die() };

	public MonopolyGame(String[] inPlayers, int totalRounds) {
		players = new ArrayList<Player>(inPlayers.length);

		for (int i = 0; i < inPlayers.length; i++) {
			Player p;
			p = new Player(inPlayers[i], dice, board);
			players.add(p);
		}

		this.totalRounds = totalRounds;
	}

	public void addRounds(int addRounds) {
		totalRounds += addRounds;
	}

	public void playGame() {
		while (roundsPlayed < totalRounds) {
			playRound();
			roundsPlayed++;
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	private void playRound() {
		for (Iterator<Player> iter = players.iterator(); iter.hasNext();) {
			Player player = (Player) iter.next();
			player.takeTurn();
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Rounds Played: " + roundsPlayed + "/" + totalRounds + "\n");
		for (Iterator<Player> iter = players.iterator(); iter.hasNext();) {
			Player player = (Player) iter.next();
			sb.append("Player: ");
			sb.append(player.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
