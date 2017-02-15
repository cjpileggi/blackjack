package blackjack;
/*******************************************************************************
* Card Class:                                                                  *
* Creates a card object. Card suite and value is indicated using integers      *
* in the "face" and "suit" variables. An integer is stored in "index" to       *
* correspond to its image in cardImages[] in the BlackjackFrame class          *
*******************************************************************************/


public class Card 
{
  private int face; //The card's suit (Hearts, Clubs, Spaces, Diamonds)
  private int value; //The card's value (2-10, J, Q, K, A)
  private int playVal = 0; //The cards gameplay value
  private boolean hardAce = false; //If the Card has an ace value, this determines whether its soft or hard
  private int index; // index of the Card's corresponding image in cardImages[]
  
  

/******************************************************************************* 
* Constructor:                                                                 *
* Uses parameters to assign values and "index" value to the unique card        *
* image. Calls trueValue()                                                     *
*******************************************************************************/
 public Card(int f, int v, int in ) 
 {
  value = v;
  face = f;
  index = in;
  this.trueValue();
 }
    
/*******************************************************************************
* trueValue():                                                                 *
* Uses the cards "value" to determine what the Card's point value is in        *
* traditional BlackJack and is stored in "playVal" (If the card is an ace,     *
* then its value is determined by the Player Class.                            *
*******************************************************************************/
 public final void trueValue()
 {
  if(value >= 0 && value <= 8)
      playVal = value + 2;
  else if(value >= 9 && value <= 11)
           playVal = 10;
 }
    
/*******************************************************************************
* hardAce():                                                                   *
* If the card is an ace, this turns it into a hard ace by setting "hardAce"    *
* true. The Card's play value is set to 1 for the game                         *
*******************************************************************************/
 public void hardAce()
 {
  hardAce = true;
  playVal = 1;
 }
    
 
/*******************************************************************************
* softAce():                                                                   *
* If the card is an ace, this makes it a soft ace by changing its play value   *
* to 11                                                                        *
*******************************************************************************/
 public void softAce() {playVal = 11;} 
 
 
 /* returnFace(): returns card's suit value */
 public int returnFace() {return face;}
 
 /* returnValue(): returns card's value */ 
 public int returnValue() {return value;}
    
 /* returnplayVal(): returns card's play value */   
 public int returnplayVal() {return playVal;}  
 
 /* isHardAce(): returns value of "hardAce" */
 public boolean isHardAce() {return hardAce;}
      
 /* picture(): returns the integer in "index" */    
 public int picture() {return index;}
 
}//end Card Class
