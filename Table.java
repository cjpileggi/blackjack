package blackjack;
/*******************************************************************************
* Table Class:                                                                 *
* Constructs the deck for a game. It creates an array of Card objects by       *
* using the Deck class as a template by randomly picking a Card object from    * 
* the Deck class, then adding it to the array.                                 *
*******************************************************************************/

import java.util.Random;

public class Table 
{
  Random rndNumbers = new Random(); //random generator
   
  private Deck stdDeck = new Deck(); //Creates a Deck object
  private Card tableDeck[]; //Game deck array
  private int counter = 0; //indicates top Card in tableDeck[] 
      

/*******************************************************************************
* Constructor:                                                                 *
* Immediately calls the shuffle() function                                     *
*******************************************************************************/
  public Table() {this.shuffle();}
    
    
/*******************************************************************************
* shuffle():                                                                   *
* Creates the deck that is used for each individual game. It                   *
* creates a randomized version of the Deck class. To ensure that no copy of    *
* a unique Card is placed in the tableDeck[], a temporary array of 52 boolean  *
* values is created, which corresponds to the indices of the Deck object.      *
* When the random generator picks a value, the if statement checks if the      *
* index in the array is true or false. If its false, the Card in the           *
* corresponding index of the Deck object is copied to the game deck, and then  *
* the boolean value is switched to true. When set to true, the corresponding   *
* Card in the Deck object cannot be put in the game deck. This process         *
* finishes after 52 unique Cards are placed into the game deck.                *
*******************************************************************************/  
  public void shuffle()
  {
    counter = 0; //counter is reset to the beginning of the game deck array
    int current = 0; //assigned a random number
    boolean flag; //marks whether or not the index being worked on has been filled
        
    tableDeck = new Card[52];
        
    boolean noCopy[] = new boolean[52];
        
    for(int i = 0; i < 52; i++)
         noCopy[i] = false;
        
    for(int i = 0; i < 52; i++) //for all 52 Cards in tableDeck[]
       {
        do
        { 
         flag = true;                     
         current = rndNumbers.nextInt(52); //random index number
         
         //the card in index "current" of the Deck is not used (false) so
         // that Card is copied into the ith position of tableDeck[]. noCopy[]
         // indicates its use (true).
         if (noCopy[current] == false)
            { 
             tableDeck[i] = stdDeck.getDeckIndex(current);
             noCopy[current] = true; 
            }
         else
            flag = false;  //current index not assigned so the for loop cannot
                           //continue            
        
        }while(flag == false); //when index i of tableDeck[] has a Card
       }
  }//end shuffle
    
    
/*******************************************************************************
* draw():                                                                      *
* Top card is drawn from the game deck and returns its Card object. "counter"  *
* is then incremented to indicate that the next Card in tableDeck[] is the     *
* next card to be drawn.                                                       *
*******************************************************************************/
  public Card draw()
    {
     Card temp = new Card(0,0,0); 
     temp = tableDeck[counter];
     counter++;
     return temp;
    }

}//end Table Class    