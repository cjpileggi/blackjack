package blackjack;
/*******************************************************************************
* Deck Class:                                                                  *
* Creates a standard 52 card deck. It creates an array of Card objects, each   *
* corresponding to the 52 possible unique Card values in a standard Poker deck.* 
* The cards are put in order by suit and value. Only one Deck object is created* 
* and is used as a template for the "Table" Class.                             *
*******************************************************************************/


public class Deck 
{
 private Card deck[]; //Standard Poker deck
   
/*******************************************************************************
* Constructor:                                                                 *
* Uses a nested for loop to assign Card objects into the deck[]. The values of *
* the Cards are determined by using the indices of the for loops for each      *
* Card's parameter and are added into the array in ascending order. The inner  *
* loop assigns the Card's value("value") and the outer loop assigns the Cards  *
* suit("face"). This creates an array of 52 unique Cards in order. "count" is  *
* used to move through the deck[] array and to store each Card's appropriate   *
* "index" value                                                                *
*******************************************************************************/
  public Deck()
  {
   deck = new Card[52];
   int count = 0; //Used to move through all 52 spaces in the array.
       
   for(int i = 0; i < 4; i++)
        for(int j = 0; j < 13; j++)
        {   
         deck[count] = new Card(i, j, count); //
         count++;
        }
  }
    
/*******************************************************************************
* getDeckIndex():                                                              *
* Receives an integer value "num" and returns a copy of the Card object in     *
* index "num" of the deck                                                      *
*******************************************************************************/
  public Card getDeckIndex(int num) {return deck[num];}
    
}//end of Deck Class
