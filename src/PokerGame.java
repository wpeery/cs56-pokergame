import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import java.util.ArrayList;

/**
   Class that represents a Poker 5 card draw game.
*/

public class PokerGame {

    private JPanel panel;
    private JFrame mainFrame,mainFrame2;
    private JFrame playButtonFrame;
    private JButton playButton, playAgainButton, infoButton;
    private JLabel winnerLabel;
    private JPanel dealerPanel,playerPanel, centerPanel;
    private Hand dealerHand; //playerHand;
    private ArrayList<Hand> playerHands;
    private Deck deck;
    private int numPlayers;
	
    /**
       No arg constructor that initializes a new deck.
    */
    public PokerGame(){
	deck=new Deck();
    }
	
    /**
       Main method of PokerGame class.
    */
    public static void main(String[] args)
    {
	PokerGame gui=new PokerGame();
	gui.go();
    }
	
    /**
       Creates a window with a Play button.
    */
    public void go(){
		
	playButtonFrame = new JFrame();
	playButtonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
                
		
	panel=new JPanel();
		
	playButton=new JButton("Click to Play");
	playButton.addActionListener(new playButtonListener());
	panel.add(playButton,BorderLayout.CENTER);
		
	infoButton = new JButton("Rules");
	infoButton.addActionListener(new infoButtonListener());
	panel.add(infoButton, BorderLayout.SOUTH);
		
		
	panel.setBackground(Color.darkGray);
		
	playButtonFrame.add(BorderLayout.CENTER, panel);
	playButtonFrame.setSize(200,200);
		
	playButtonFrame.setVisible(true);
    }
	
    /**
       Sets up the player's and dealer's hand.
    */	
    public void playerSetUp(int numPlayers){
	for(int i = 0; i< numPlayers; i++){
	    Hand playerHand = new Hand(deck, 2);
	    playerHands.add(playerHand);
	}
	dealerHand=new Hand(deck, 0);
    }
	
    /**
       Returns an ImageIcon by using the URL class in order to make the 
       ImageIcon web compatible.
       @param c card whose image is to be retrieved.
    */
    public ImageIcon getCardImage(Card c){
	String dir="Cards/";
	String cardFile=c.toString()+".png";
	URL url=getClass().getResource(dir+cardFile);
	return new ImageIcon(url);
    }
	
    /**
       Method that sets up a new Poker game with new hands.
    */
    public void replay(){
	playerSetUp(numPlayers);
	dealerPanel=new JPanel();
	playerPanel = new JPanel();
	centerPanel=new JPanel();
	playAgainButton=new JButton("Play Again");
	playAgainButton.addActionListener(new playAgainListener());
	for(int i=0;i<dealerHand.size();i++)
	    dealerPanel.add(new JLabel(getCardImage(dealerHand.get(i))));
	
	/**
	for(int i = 0; i < numPlayers; i++){
	    playerPanels[i].add(new JLabel(getCardImage(playerHands.get(i))));
	    } **/	
	    dealerPanel.add(new JLabel("DEALER"));
	    playerPanel.add(new JLabel("PLAYER"));
	    centerPanel.add(playAgainButton);
	    /**
	       if(playerHand.compareHands(dealerHand)==1)
	       winnerLabel=new JLabel("YOU WON!");
	       else
	       winnerLabel=new JLabel("Dealer won");
	       centerPanel.add(winnerLabel);
	       deck.reShuffle();
	    **/
  		
	    mainFrame=new JFrame();
	    mainFrame.setSize(1800,1800);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.getContentPane().add(BorderLayout.NORTH, dealerPanel);
	    mainFrame.getContentPane().add(BorderLayout.SOUTH, playerPanel);
	    mainFrame.getContentPane().add(BorderLayout.CENTER, centerPanel);
	    mainFrame.setVisible(true);
	}

	class infoButtonListener implements ActionListener{
	
	    public void actionPerformed(ActionEvent event){
		TabbedPane infoPane = new TabbedPane();
		infoPane.setVisible( true );
	    }
	}
	
	/**
	   Sets up the Poker game when the client clicks the Play button.
	*/
	class playButtonListener implements ActionListener{
	    public void actionPerformed(ActionEvent event){
		
		JFrame selectFrame = new JFrame();	
		Object[] possibilities = {"2", "3", "4"};
		String s = (String)JOptionPane.showInputDialog(
							       selectFrame,
							       "Select number of players",
							       "Number of Players",
							       JOptionPane.PLAIN_MESSAGE,
							       null,
							       possibilities,
							       "2");

		numPlayers = Integer.parseInt(s);
		playerHands = new ArrayList<Hand>();
		playerSetUp(numPlayers);
		dealerPanel=new JPanel();
		playerPanel=new JPanel();
		centerPanel=new JPanel();
		playAgainButton=new JButton("Play Again");
		playAgainButton.addActionListener(new playAgainListener());

		for(int i=0;i<dealerHand.size();i++){
		    dealerPanel.add(new JLabel(getCardImage(dealerHand.get(i))));
		}
  			
		for(int i=0;i<playerHands.size();i++){
		    Hand currentPlayer = playerHands.get(i);
		    for(int j = 0; j < currentPlayer.size(); j++)
			playerPanel.add(new JLabel(getCardImage(currentPlayer.get(j))));
		}
		dealerPanel.add(new JLabel("DEALER"));
		playerPanel.add(new JLabel("PLAYER"));
		/**
		   centerPanel.add(BorderLayout.CENTER,playAgainButton);
		   if(playerHand.compareHands(dealerHand)==1)
		   winnerLabel=new JLabel("YOU WON!");
		   else
		   winnerLabel=new JLabel("Dealer won");
		   centerPanel.add(winnerLabel);
		   deck.reShuffle();
		**/
  		

		JPanel main = new JPanel( new FlowLayout(FlowLayout.CENTER, 30, 0) );

	
		//	main.add( dealerPanel);
		main.add( playerPanel );
		
		mainFrame=new JFrame();
		mainFrame.setSize(1800,1800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(BorderLayout.NORTH, dealerPanel);
		//mainFrame.getContentPane().add(BorderLayout.SOUTH, playerPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, main);
		playButtonFrame.dispose();
		mainFrame.setVisible(true);
	    }	
  	}
  
    /**
       Sets up the Poker game when the Client clicks the PlayAgain button.
    */
    class playAgainListener implements ActionListener{
  	public void actionPerformed(ActionEvent event){
	    mainFrame.dispose();
	    PokerGame gui2=new PokerGame();
	    gui2.replay();
  	}
    }
}
















