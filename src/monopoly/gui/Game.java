package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.*;

import monopoly.core.MonopolyGame;        

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {


	private static final String STARTSIMULATION = "STARTSIMULATION";
	private static final String CONTINUESIMULATION = "CONTINUESIMULATION";
	private JButton startButton;
	private JButton continueButton; 
	private JTextArea textArea;
	private JTextField roundsField;
	private JTextField player1Field;
	private JTextField player2Field;
	private MonopolyGame monopolyGame;



	public Game() {
		setLayout(new BorderLayout());
	
		roundsField = new JTextField(10);
		roundsField.setActionCommand("ROUNDS");
		roundsField.addActionListener(this);
		
		player1Field = new JTextField(10);
		player1Field.setActionCommand("PLAYER1");
		player1Field.addActionListener(this);
		
		player2Field = new JTextField(10);
		player2Field.setActionCommand("PLAYER2");
		player2Field.addActionListener(this);
		
		
		JLabel roundsFieldLabel = new JLabel("ROUNDS : ");
		roundsFieldLabel.setLabelFor(roundsField);
		
		JLabel player1FieldLabel = new JLabel("PLAYER1 : ");
		player1FieldLabel.setLabelFor(player1Field);

		JLabel player2FieldLabel = new JLabel("PLAYER2 : ");
		player2FieldLabel.setLabelFor(player2Field);

		
		JPanel textPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		@SuppressWarnings("unused")
		GridBagConstraints c = new GridBagConstraints();
		
		textPane.setLayout(gridbag);
		
		JLabel[] labels = {roundsFieldLabel, player1FieldLabel, player2FieldLabel};
		JTextField[] textFields = {roundsField, player1Field, player2Field};
		addLabelTextRows(labels, textFields, gridbag, textPane);
		
		textPane.setBorder(
		BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("Monopoly Input"),
		BorderFactory.createEmptyBorder(5,5,5,5)));
		
		//Create a text area.
		textArea = new JTextArea();
		
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(1000, 500));
		areaScrollPane.setBorder(
		BorderFactory.createCompoundBorder(
		BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("Output"),
		BorderFactory.createEmptyBorder(5,5,5,5)),
		areaScrollPane.getBorder()));
		
		
		
		startButton = new JButton("Start Simulation"); 
		startButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        startButton.setHorizontalTextPosition(AbstractButton.CENTER);
        startButton.setActionCommand(STARTSIMULATION);
        startButton.addActionListener(this);

		continueButton = new JButton("Continue Simulation"); 
		continueButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		continueButton.setHorizontalTextPosition(AbstractButton.CENTER);
		continueButton.setActionCommand(CONTINUESIMULATION);
		continueButton.addActionListener(this);
        continueButton.setEnabled(false);
        
        JPanel buttonPane = new JPanel();
		
		buttonPane.add(startButton, BorderLayout.CENTER);
		buttonPane.add(continueButton, BorderLayout.EAST);
		
		buttonPane.setBorder(
		BorderFactory.createEmptyBorder(5,5,5,5));

        
		
		//Put everything together.
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(textPane, 		BorderLayout.NORTH);
		panel.add(buttonPane,       BorderLayout.CENTER); 
		panel.add(areaScrollPane,		BorderLayout.SOUTH);
		
		add(panel, BorderLayout.LINE_START);
		
		}


	private void addLabelTextRows(JLabel[] labels,
            JTextField[] textFields,
            GridBagLayout gridbag,
            Container container) {
GridBagConstraints c = new GridBagConstraints();
c.anchor = GridBagConstraints.EAST;
int numLabels = labels.length;

for (int i = 0; i < numLabels; i++) {
c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
c.fill = GridBagConstraints.NONE;      //reset to default
c.weightx = 0.0;                       //reset to default
container.add(labels[i], c);

c.gridwidth = GridBagConstraints.REMAINDER;     //end row
c.fill = GridBagConstraints.HORIZONTAL;
c.weightx = 1.0;
container.add(textFields[i], c);
}
}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (STARTSIMULATION.equals(actionCommand)) {
			 startButton.setEnabled(false); 
			 continueButton.setEnabled(false);
			 try { 
				   int rounds = Integer.parseInt(roundsField.getText()); 
				   String[] players = new String[2];
				   players[0] = player1Field.getText(); 
				   players[1] = player2Field.getText(); 

				   monopolyGame = new MonopolyGame(players, rounds);
				   textArea.append("GAME START STATE\n");
				   textArea.append(monopolyGame.toString());
				   textArea.append("\nRunning\n");
				   monopolyGame.playGame();
				   textArea.append("GAME END STATE\n");
				   textArea.append(monopolyGame.toString());
				   textArea.append("\n------------------------\n");
				   
				 
			 } catch (Exception ex) { 
				  textArea.append("\n--- EXCEPTION  -------------------\n");
				 StringWriter sw = new StringWriter(); 
				 PrintWriter pw = new PrintWriter(sw); 
				  ex.printStackTrace(pw);
				  textArea.append(sw.toString());
				  textArea.append("\n-----------------------------------\n");
			 }
			 startButton.setEnabled(true); 
		     continueButton.setEnabled(true);
	 
		} else 		if (CONTINUESIMULATION.equals(actionCommand)) {
			 startButton.setEnabled(false); 
			 continueButton.setEnabled(false);
			 try { 
				   int rounds = Integer.parseInt(roundsField.getText()); 
				   monopolyGame.addRounds(rounds); 
				   textArea.append("GAME CONTINUE STATE\n");
				   textArea.append(monopolyGame.toString());
				   textArea.append("\nRunning\n");
				   monopolyGame.playGame();
				   textArea.append("GAME END STATE\n");
				   textArea.append(monopolyGame.toString());
				   textArea.append("\n------------------------\n");
				   
				 
			 } catch (Exception ex) { 
				  textArea.append("\n--- EXCEPTION  -------------------\n");
				 StringWriter sw = new StringWriter(); 
				 PrintWriter pw = new PrintWriter(sw); 
				  ex.printStackTrace(pw);
				  textArea.append(sw.toString());
				  textArea.append("\n-----------------------------------\n");
			 }
			 startButton.setEnabled(true); 
		     continueButton.setEnabled(true);
	 
		}

		
	}

	
	
    private static void goGUI() {
        JFrame frame = new JFrame("Monopoly");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        @SuppressWarnings("unused")
		JLabel label = new JLabel("Monopoly");
        frame.add(new Game());
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                goGUI();
            }
        });
    }



}


