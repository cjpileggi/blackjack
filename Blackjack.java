package blackjack;

/*  Christopher Pileggi
 *  CISC 3150
 *  Term Project: Blackjack
 * 
 */



/*******************************************************************************
* Blackjack Class:                                                             *
* Creates a BlackjackFrame object                                              *
*******************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Blackjack 
{
  public static void main(String[] args) 
      {Frame bj = new BlackjackFrame();}    
}

/*******************************************************************************
* BlackjackFrame Class:                                                        *
* Creates a frame that contains the game of traditional Blackjack. It sets up  *
* a user friendly GUI environment. A series of buttons and labels are used to  *
* help the user move through the GUI with ease. To create a simulation of the  *
* card game, card images are stored in an array and are used to represent the  *
* Cards in each player's hand. The user could progress through the game using  *
* indicated keys on the keyboard, or can use buttons that correspond to those  *
* keys. Contains an array of the Card image references.                        *
*******************************************************************************/
class BlackjackFrame extends JFrame implements ActionListener
{ 
 Table game = new Table(); //Creates the game's deck
 Player you = new Player(); //The user
 Player dealer = new Player(); //The dealer
 
 //Array used to store Card image references stored in the program.
 // Images used throughout the program are displayed 
 ImageIcon cardImages[];
    
 int maxPlay; //maximum games in a selected series
 int maxPlayer; //maximum games a player may win to win a series
 int bestYou; //games the user has won in a current series
 int bestDealer; //games the dealer has won in a current series  
      
 //This panel contains buttons that allow the user to create and play a game   
 JPanel buttonsPanel = new JPanel();
    
    //Hit: pressed when user wants another card during a game 
    private JButton buttonHit = new JButton("Hit (h)");
 
    //Stay: pressed when user is satisfied with his/her hand. The dealer proceeds
     private JButton buttonStay = new JButton("Stay (s)");
 
     //Deal: the deck is shuffled and a new game begins
     private JButton buttonDeal = new JButton("Deal (d)");
 
     //New: displays the buttons that the user presses to begin a series of games
     private JButton buttonNew = new JButton("New Game (n)");
    
//This panel is used to diplay labels that have instructions ang game statuses  
JPanel statusPanel = new JPanel();
     
    //Status: explains events and instructions
    private JLabel labelStatus = new JLabel(" ", JLabel.CENTER);
    
    //Score: shows the amount of games currently won by each player in a series 
    private JLabel labelPlayerScore = new JLabel(" ", JLabel.CENTER);
    private JLabel labelDealerScore = new JLabel(" ", JLabel.CENTER);
 
    //Diplays the series currently being played
    private JLabel currentGame = new JLabel(" ", JLabel.CENTER);
    
//This panel displays the user's hand and sum of those Cards 
JPanel playerPanel = new JPanel();
    
    //Displays the sum of all the users Card's
    private JLabel labelPlayerSum = new JLabel(" ", JLabel.CENTER);
 
//This panel displays the dealer's hand and the sum of those Cards
 JPanel dealerPanel = new JPanel();   
    
    //Displays the sum of all the dealer's Card's
    private JLabel labelDealerSum = new JLabel(" ", JLabel.CENTER);
 
//This panel is for displaying miscellaneous objects at specific times, such as the title,
// victory status, game deck image, and the series buttons
JPanel miscPanel = new JPanel();
    
    //Series buttons: sets the amount of games a player must win to achieve 
    // victory
    private JButton best2of3 = new JButton("Best 2 of 3 (1)");
    private JButton best3of5 = new JButton("Best 3 of 5 (2)");
    private JButton best4of7 = new JButton("Best 4 of 7 (3)");
    private JButton best5of9 = new JButton("Best 5 of 9 (4)");
    private JButton best6of11 = new JButton("Best 6 of 11 (5)");
    private JButton best7of13 = new JButton("Best 7 of 13 (6)");             
    
/*******************************************************************************
* Constructor:                                                                 *
* Opens the frame and displays the welcome screen, where the user is prompted  *
* to begin a game. The environment is created by setting background color,     *
* fonts of labels and specific alignments of the objects in their individual   *
* panels. These panels are placed in a BorderLayout. The buttons are added to  *
* the Action Listener and are individually given key events for user           *
* interaction. The necessary Card image references are stored in the           *
* CardImages array                                                             *
*******************************************************************************/
BlackjackFrame()
{       
 //fills array with Card images
 cardImages = new ImageIcon[54];
 setCardImages(cardImages); 
 
 JFrame bjFrame = new JFrame("BlackJack - by Christopher Pileggi"); //Title on title bar
 Toolkit kit = Toolkit.getDefaultToolkit();
 bjFrame.setIconImage(kit.getImage(this.getClass().getResource("imgs/312.png"))); //Picture on title bar
 bjFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 bjFrame.setVisible(true);
 bjFrame.setExtendedState(Frame.MAXIMIZED_BOTH); //Frame starts out completely maximized
        
 
 
 //Fonts and colors of different labels        
 currentGame.setFont(new Font("Serif", 1, 30));
 currentGame.setForeground(Color.BLACK);
 labelPlayerScore.setFont(new Font("Serif",1,20));
 labelPlayerScore.setForeground(Color.BLACK);
 labelDealerScore.setFont(new Font("Serif",1,20));
 labelDealerScore.setForeground(Color.BLACK);
 labelPlayerSum.setForeground(Color.BLACK);
 labelDealerSum.setForeground(Color.BLACK);
 labelStatus.setForeground(Color.BLACK);
 labelStatus.setFont(new Font("Serif",1,30));
         
 //Adds the buttons to the action listener
 buttonHit.addActionListener(this);
 buttonStay.addActionListener(this);
 buttonDeal.addActionListener(this);
 buttonNew.addActionListener(this);       
 best2of3.addActionListener(this);
 best3of5.addActionListener(this);
 best4of7.addActionListener(this);
 best5of9.addActionListener(this);
 best6of11.addActionListener(this);
 best7of13.addActionListener(this);

 //Gives the buttons their individual key events
 buttonHit.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, true),
								  JComponent.WHEN_IN_FOCUSED_WINDOW);
 buttonStay.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true),
								   JComponent.WHEN_IN_FOCUSED_WINDOW);
 buttonDeal.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true),
								   JComponent.WHEN_IN_FOCUSED_WINDOW);
 buttonNew.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_N, 0, true),
								  JComponent.WHEN_IN_FOCUSED_WINDOW);
 best2of3.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);
 best3of5.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);
 best4of7.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);
 best5of9.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);
 best6of11.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);
 best7of13.registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, true),
								 JComponent.WHEN_IN_FOCUSED_WINDOW);

 //Disables focus for all buttons to prevent any from being activated by the space bar
 buttonHit.setFocusable(false);
 buttonStay.setFocusable(false);
 buttonDeal.setFocusable(false);
 buttonNew.setFocusable(false);
 best2of3.setFocusable(false);
 best3of5.setFocusable(false);
 best4of7.setFocusable(false);
 best5of9.setFocusable(false);
 best6of11.setFocusable(false);
 best7of13.setFocusable(false);

 //The objects in these panels are put in a BoxLayout and are indivcated to be 
 // placed vertically instead of horizontally
 miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
 buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
 statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        
 //Gives each button the property to align to the center of the panel they belong in
 buttonHit.setAlignmentX(CENTER_ALIGNMENT);
 buttonStay.setAlignmentX(CENTER_ALIGNMENT);
 buttonDeal.setAlignmentX(CENTER_ALIGNMENT);
 buttonNew.setAlignmentX(CENTER_ALIGNMENT);
 best2of3.setAlignmentX(Component.CENTER_ALIGNMENT);
 best3of5.setAlignmentX(Component.CENTER_ALIGNMENT);            
 best4of7.setAlignmentX(Component.CENTER_ALIGNMENT);
 best5of9.setAlignmentX(Component.CENTER_ALIGNMENT);
 best6of11.setAlignmentX(Component.CENTER_ALIGNMENT);
 best7of13.setAlignmentX(Component.CENTER_ALIGNMENT);
  
 //Disables all buttons but the New button
 best2of3.setEnabled(false);
 best3of5.setEnabled(false);           
 best4of7.setEnabled(false);
 best5of9.setEnabled(false);
 best6of11.setEnabled(false);
 best7of13.setEnabled(false);
 buttonHit.setEnabled(false);
 buttonStay.setEnabled(false);
 buttonDeal.setEnabled(false);
            
 //Adds objects to their respective panels
 buttonsPanel.add(buttonHit);
 buttonsPanel.add(buttonStay);
 buttonsPanel.add(buttonDeal);
 buttonsPanel.add(buttonNew);
 statusPanel.add(labelStatus);
        
 /*
  * Welcome Screen
  */
 
 JLabel labelWelcome1 = new JLabel("WELCOME TO");
 JLabel labelWelcome2 = new JLabel("BLACKJACK");
 JLabel labelWelcome3 = new JLabel(cardImages[51]);
 JLabel labelWelcome4 = new JLabel("Press (n) to continue");
        
 labelWelcome1.setFont(new Font("Monotype Corsiva",1,80));
 labelWelcome1.setForeground(Color.BLACK);
 labelWelcome1.setAlignmentX(CENTER_ALIGNMENT);
 labelWelcome2.setFont(new Font("Monotype Corsiva",1,80));
 labelWelcome2.setForeground(Color.BLACK);
 labelWelcome2.setAlignmentX(CENTER_ALIGNMENT);
 labelWelcome3.setAlignmentX(CENTER_ALIGNMENT);
 labelWelcome4.setFont(new Font("Serif",1,30));
 labelWelcome4.setForeground(Color.BLACK);
 labelWelcome4.setAlignmentX(CENTER_ALIGNMENT);
        
  //Add To miscPanel (center)      
  miscPanel.add(labelWelcome1);
  miscPanel.add(labelWelcome2);
  miscPanel.add(labelWelcome3);
  miscPanel.add(labelWelcome4);
       
/**/
       
  
  //Set the color for all panel backgrounds to be green like a card table
  dealerPanel.setBackground(new Color(0, 100, 0));
  playerPanel.setBackground(new Color(0, 100, 0));
  buttonsPanel.setBackground(new Color(0, 100, 0));
  miscPanel.setBackground(new Color(0, 100, 0));
  statusPanel.setBackground(new Color(0, 100, 0));
        
        
  //All panels in the frame are placed in a BorderLayout
  bjFrame.setLayout(new BorderLayout());
  bjFrame.add(dealerPanel, BorderLayout.NORTH);
  bjFrame.add(playerPanel, BorderLayout.SOUTH);
  bjFrame.add(miscPanel, BorderLayout.CENTER);
  bjFrame.add(buttonsPanel, BorderLayout.EAST);
  bjFrame.add(statusPanel, BorderLayout.WEST);
  
  bjFrame.repaint();

}//end Constructor     

/*******************************************************************************
* setCardImages():                                                             *
* Passes an ImageIcon array to create references to Card images.               *
* To allow the program to run anywhere as a JAR                                *
* file and still use images, the images are stored into the program.           *
* This is done with .getResource(). The image path for each card is obtained   *
* using the same method used to create the array in the Deck object. Each      *
* image is put in the array in order using the counter.                        *
*******************************************************************************/
private void setCardImages(ImageIcon[] pics)
{
 int count = 0;
 for(int i = 0; i < 4; i++)
        for(int j = 0; j < 13; j++)
        {   
         ImageIcon temp = new ImageIcon(this.getClass().getResource("imgs/" + i + "" + j + ".png"));
         pics[count] = temp; 
         count++;
        }

 //miscellaneous images
 pics[52] = new ImageIcon(this.getClass().getResource("imgs/back.png")); //back of card
 pics[53] = new ImageIcon(this.getClass().getResource("imgs/deck.png")); //deck image
}


        
/*******************************************************************************
* setGame():                                                                   *
* Creates a new game after a series has been chosen by the user. It receives   *
* the maximum plays in that series and assigned to maxPlay and maxPlayer.      *
* The display is then set and the cards are dealt for the first game in the    *
* series.                                                                      *
*******************************************************************************/
private void setGame(int play, int player)
{
 maxPlay = play;
 maxPlayer = player;
 
 //scores for user and dealer are reset
 bestYou = 0;
 bestDealer = 0;
        
 deal(); //cards are dealt
        
 //Labels are set up and/or added to their repective panels
 currentGame.setText("Best " + maxPlayer + " of "+ maxPlay  );
 labelPlayerScore.setText("Player Score: " + bestYou);
 labelDealerScore.setText("Dealer Score: " + bestDealer);
 statusPanel.add(new JLabel(" ", JLabel.CENTER));
 statusPanel.add(new JLabel(" ", JLabel.CENTER));
 statusPanel.add(currentGame);
 statusPanel.add(labelPlayerScore);
 statusPanel.add(labelDealerScore);
        
 miscPanel.removeAll(); //clears center panel
        
 //puts deck image in center panel for decoration
 miscPanel.add(new JLabel(" ", JLabel.CENTER));       
 JLabel deck = new JLabel(cardImages[53]); //image of the deck
 deck.setAlignmentX(CENTER_ALIGNMENT);
 miscPanel.add(deck);
        
 miscPanel.updateUI(); //updates GUI
        
//enable required buttons and disable those unused at this point
 buttonHit.setEnabled(true);
 buttonStay.setEnabled(true);
 buttonDeal.setEnabled(false);
 best2of3.setEnabled(false);
 best3of5.setEnabled(false);           
 best4of7.setEnabled(false);
 best5of9.setEnabled(false);
 best6of11.setEnabled(false);
 best7of13.setEnabled(false);
}

/*******************************************************************************
* deal():                                                                      *
* Sets up a game. The deck is shuffled, the player's hands are reset, and      *
* the initial cards are dealt to each player                                   *  
*******************************************************************************/
private void deal()
{
 playerPanel.removeAll(); // player and dealer panels are cleared
 dealerPanel.removeAll();
 playerPanel.updateUI();
 dealerPanel.updateUI();
        
 you.reset();
 dealer.reset();
 game.shuffle();
        
 //sets up and adds labels to show your play value sum. Dealer's sum is kept hidden 
 labelPlayerSum.setText("Sum: " + you.returnSum());
 labelDealerSum.setText("Sum: ?");
 playerPanel.add(labelPlayerSum);
 dealerPanel.add(labelDealerSum);
        
 //initial cards are dealt
 hitPlayer();
 hitDealerFaceDown(); //dealer's first card is always face down
 hitPlayer();
 hitDealer();
 labelStatus.setText("Press (h) to hit or (s) to stay");
}


/*******************************************************************************
* hitPlayer():                                                                 *
* Player draws a Card from the game deck. That card is displayed on the        *
* player's panel and the play value of that card is added to the user's        * 
* overall sum                                                                  *
*******************************************************************************/  
private void hitPlayer() 
{
 Card drawnCard = new Card(0,0,0);
 drawnCard = game.draw();
 you.playerDraw(drawnCard);
 playerPanel.add(new JLabel(cardImages[drawnCard.picture()])); //adds picture of drawn card
 labelPlayerSum.setText("Sum: " + you.returnSum()); //adds Card value to sum
 playerPanel.updateUI();
}
       
/*******************************************************************************
* hitDealerFaceDowm():                                                         *
* Dealer draws from the game deck and places the Card face down                *
*******************************************************************************/
private void hitDealerFaceDown() 
{
 Card drawnCard = new Card (0,0,0);
 drawnCard = game.draw();
 dealer.playerDraw(drawnCard);
 dealerPanel.add(new JLabel(cardImages[52])); //displays a facedown card
 dealerPanel.updateUI();
}
    
/*******************************************************************************
* hitDealer():                                                                 *
* Dealer draws from the game deck. The Card image is displayed in the dealer's *
* panel                                                                        *
*******************************************************************************/
private void hitDealer() 
{
 Card drawnCard = new Card (0,0,0);
 drawnCard = game.draw();
 dealer.playerDraw(drawnCard);
 dealerPanel.add(new JLabel(cardImages[drawnCard.picture()])); //displays card image
 dealerPanel.updateUI();
}
    
/*******************************************************************************
* checkWinner():                                                               *
* The winner of a game is checked and displays in the center panel who the won * 
* or lost. Whoever wins has their overall score increased by one. The dealer's * 
* sum is calculated and all of its cards are revealed. It also checks to see if* 
* a player won the series. If a player's score matches maxPlayer, then         *
* checkGameWinner() is called.                                                 *
*******************************************************************************/
private void checkWinner() 
{
 //enables the deal button to start a game
 buttonHit.setEnabled(false);
 buttonStay.setEnabled(false);
 buttonDeal.setEnabled(true);
 dealerPanel.removeAll(); //removes existing objects
 
 labelDealerSum.setText("Sum: " + dealer.returnSum()); //reveals dealer's sum
 dealerPanel.add(labelDealerSum);
        
 //each of the players cards are revealed
 for (int i = 0; i < dealer.returnSize(); i++) 
        dealerPanel.add(new JLabel(cardImages[dealer.displayPlayerCard(i)]));
            
 //checks for winner of game. The status label is updated and the winner's score is incremented               
 if (you.returnSum() > 21) {
      labelStatus.setText("Player Busts. (Press (d) to deal)");
      bestDealer++;
      labelDealerScore.setText("Dealer: " + bestDealer);
            
 } else if (dealer.returnSum() > 21) {
            labelStatus.setText("Dealer Busts. (Press (d) to deal)");
            bestYou++;
            labelPlayerScore.setText("Player: " + bestYou);
            
 } else if (dealer.returnSum() == you.returnSum()) {
            labelStatus.setText("Push. (Press (d) to deal)");
        
 } else if (dealer.returnSum() < you.returnSum()) {
            labelStatus.setText("Player Wins. (Press (d) to deal)");
            bestYou++;
            labelPlayerScore.setText("Player: " + bestYou);
            
 } else {
      labelStatus.setText("Dealer Wins. (Press (d) to deal)");
      bestDealer++;
      labelDealerScore.setText("Dealer: " + bestDealer);
        }
        
 //a player wins the series if their score matches the score required to win the match      
 if (bestYou == maxPlayer)
      checkGameWinner(true);
 else if (bestDealer == maxPlayer) 
            checkGameWinner(false);
}
    
/*******************************************************************************
* checkGameWinner():                                                           *
* Displays the name of winner of the series in the center panel. This is       *
* determined by what is passed into the boolean variable "win." All other      *
* options are then disabled and the user is prompted to begin a new game       *
*******************************************************************************/
public void checkGameWinner(boolean win)
{
 //creates labels to display winner
 labelStatus.setText(" ");
 JLabel winner = new JLabel(" ", JLabel.CENTER);
 
 //prompt for new game
 JLabel next = new JLabel("Press (n) to start a new game.", JLabel.CENTER);
        
 winner.setFont(new Font("Serif",1,80));
 winner.setForeground(Color.BLACK);
 winner.setAlignmentX(CENTER_ALIGNMENT);
        
 next.setFont(new Font("Serif",1,30));
 next.setForeground(Color.BLACK);
 next.setAlignmentX(CENTER_ALIGNMENT);

 //sets the winning display message depending on the value of "win"
 if(win == true)
     winner.setText("YOU WIN!");
 else if (win == false)
           winner.setText("DEALER WINS");
        
 //set up for display
 miscPanel.removeAll();
 miscPanel.add(new JLabel(" ", JLabel.CENTER));
 miscPanel.add(new JLabel(" ", JLabel.CENTER));
 miscPanel.add(new JLabel(" ", JLabel.CENTER));
 miscPanel.add(new JLabel(" ", JLabel.CENTER));
        
 //shows winner and asks to start a new game
 miscPanel.add(winner);
 miscPanel.add(next);
            
 miscPanel.updateUI();
        
 //option for new game is enabled
 buttonNew.setEnabled(true);
 buttonDeal.setEnabled(false);
}

/*
 * Button events
 */

public void actionPerformed(ActionEvent e) {
        
/*
* buttonNew:
* Sets up the center panel to display the buttons that give the user the
* option to select a series to play
*/
if (e.getSource() == buttonNew)
{
  //panels are cleared       
  miscPanel.removeAll();
  playerPanel.removeAll();
  dealerPanel.removeAll();
         
  //labels are cleared
  labelPlayerScore.setText(" ");
  labelDealerScore.setText(" ");
  currentGame.setText(" ");
  labelStatus.setText(" ");

  //prompt for user       
  JLabel select = new JLabel("Select Your Game (Press 1 to 6)");
  select.setFont(new Font("Serif",1,50));
  select.setForeground(Color.BLACK);
  select.setAlignmentX(CENTER_ALIGNMENT);
  miscPanel.add(select);
            
  //adds and enables the series selection butons to the center panel                
  miscPanel.add(best2of3);
  miscPanel.add(best3of5);
  miscPanel.add(best4of7);
  miscPanel.add(best5of9);
  miscPanel.add(best6of11);
  miscPanel.add(best7of13);
  best2of3.setEnabled(true);
  best3of5.setEnabled(true);           
  best4of7.setEnabled(true);
  best5of9.setEnabled(true);
  best6of11.setEnabled(true);
  best7of13.setEnabled(true);
            
  miscPanel.updateUI();
            
  buttonNew.setEnabled(false);
 }
    

/* Series Buttons: each button passes specific values to setGame() to indicate
 * the games in the series
 */
if (e.getSource() == best2of3){setGame(3, 2);}
if (e.getSource() == best3of5){setGame(5, 3);}
if (e.getSource() == best4of7){setGame(7, 4);} 
if (e.getSource() == best5of9){setGame(9, 5);}
if (e.getSource() == best6of11){setGame(11, 6);}
if (e.getSource() == best7of13){setGame(13, 7);}
    
        
/*******************************************************************************
* buttonHit:                                                                   *
* calls hitPlayer(). If the user's sum is greater than 21, checkWinner() is    *
* called                                                                       *
*******************************************************************************/
if (e.getSource() == buttonHit) 
{
  hitPlayer();
  if (you.returnSum() > 21) { checkWinner();}
}

/*******************************************************************************
* buttonStay:                                                                  *
* The user presses this when he does not want to draw anymore cards for the    *
* rest of the game. The hitDealer() is then called until either the sum of     *
* the dealer's Cards are less than 17, or, if the user's sum is greater than   *
* 17, hitDealer() is called until the dealer's sum is greater than the user's  *
*******************************************************************************/
if (e.getSource() == buttonStay)
{
 while (dealer.returnSum() < 17 || you.returnSum() > dealer.returnSum()) {hitDealer();}
 checkWinner();
}

/*******************************************************************************
* buttonDeal:                                                                  *
* Calls the deal() function. Then enables the Hit and Stay buttons             *
*******************************************************************************/
if (e.getSource() == buttonDeal) 
{
 deal();
 buttonHit.setEnabled(true);
 buttonStay.setEnabled(true);
 buttonDeal.setEnabled(false);
}

}//end of actionPerformed

}//end BlackjackFrame
