package monopoly.commandLine;

import monopoly.core.MonopolyGame; 

public class Game {

	public static void usage() {
		System.out.println("java edu.winona.cs.CS471.monopoly.commandLine.Game ROUNDS PLAYER1 PLAYER2 [PLAYER]*\n");
	}
	
	public static void main(String[] args) {
	   if (args.length < 3) {
		   usage();
	   } else {
		   int rounds = Integer.parseInt(args[0]); 
		   String[] players = new String[args.length - 1];
		   for (int i = 0; i < args.length-1; i++) {
			   players[i] = args[i+1];
		   }
		   
		   MonopolyGame monopolyGame = new MonopolyGame(players, rounds);
		   System.out.println("GAME START STATE");
		   System.out.println(monopolyGame);
		   System.out.println("Running");
		   monopolyGame.playGame();
		   System.out.println("GAME END STATE");
		   System.out.println(monopolyGame);
		   
		   
	   }

	}

}
