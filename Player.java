package blackjack;
/*******************************************************************************
* Player Class:                                                                *
* Creates a Player object. The Player contains a hand of Card objects, has     *
* the ability to draw a Card from the game deck, and determines whether or     *
* not the ace is considered soft or hard valued                                *
********************************************************************************/  



public class Player 
{
 private Card playerHand[]; //player's hand of Cards
 private int sum = 0; //sum of the game play values of all the Cards in the player's hand
 private int empty = 0; //Indicates which index in the player's hand is to be given a card object
  
/*******************************************************************************
* Constructor:                                                                 *
* Creates an array of 20 Card objects                                          *
*******************************************************************************/
 Player() {playerHand = new Card[20];}
    
/*******************************************************************************
* playerDraw:                                                                  *
* A Card from the game deck is passed into the player's hand. If the drawn card*
* is an ace, this function determines whether or not its value is soft or hard,*
* depending on the sum of the Card's play values in the player's hand. If the  *
* sum still exceeds 21, checkSoftAce() is called                               *                                                      
*******************************************************************************/
 public void playerDraw(Card add)
 {
  boolean checked = false; //indicated whether or not the player's entire hand
                           // was checked for hard aces if necessary 
  
  playerHand[empty] = new Card(0,0,0);
  playerHand[empty] = add;
        
  if(playerHand[empty].returnValue() == 12) //checks if the drawn card is an ace
  { 
   if(sum + 11 > 21)
       playerHand[empty].hardAce();
   else
       playerHand[empty].softAce();
  }  
            
  sum += playerHand[empty].returnplayVal();//adds new value to the overall sum
  empty++; //moves the counter to the next position in playerHand[]
       
  do{  
     if (sum > 21)
          checked = checkSoftAce();
     else
          checked = true;
        
    }while(checked != true);        
 }
    
/*******************************************************************************
* checkSoftAce():                                                              *
* This function checks to see if any soft aces exist in the player's hand.     *
* If there are, they are changed to a hard ace. Returns true or false,         *
* depending whether or not an ace changed                                      *
*******************************************************************************/
 public boolean checkSoftAce()
 {
  for(int i = 0; i < empty; i++)
  {
   if (playerHand[i].returnValue() == 12 ) 
      if (playerHand[i].isHardAce() == false)
      {
       if (playerHand[i].returnplayVal() == 11)
            sum -= 10;
                    
       playerHand[i].hardAce();
       
       return false;
      }
  }
  return true;
 }
 
/*******************************************************************************
* reset():                                                                     *
* Resets the player's hand. The "sum" is changed to 0 and the "empty" counter  *
* is brought back to the first index of playerHand[] and the Cards that        *
* remain from the previous game are replaced.                                  *
*******************************************************************************/
 public void reset()
 {
  sum = 0;
  empty = 0;
 }
    
 /* returnSum(): returns the sum of the player's card values */
 public int returnSum() {return sum;}
    
 /* returnSize(): returns "empty" to determine the size of the player's hand */   
 public int returnSize() {return empty;}
    
 /* displayPlayerCard(): Receives an integer that corresponds to a position in 
  * the player's hand. Returns the "index" in the corresponding Card to reference
  * its corresponding image in CardImage[]
  */
 public int displayPlayerCard(int index) {return playerHand[index].picture();}   

}//end Player class
