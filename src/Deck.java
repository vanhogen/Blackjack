import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * <p><b>Deck</b></p>
 * <p>
 * Contains cards 2-Ace in four suits. Allows drawing from the deck and shuffling.
 * @author Nathan Van Hogen - vanhonat@gmail.com
 *
 */
public class Deck {
    Stack<Card> deck;
    Stack<Card> wasteDeck;
    int numItems;
    
    /**
     * No arg constructor builds a brand new deck
     * @throws DeckCreationException
     */
    public Deck() throws DeckCreationException{
        this.deck = new Stack<Card>();
        this.wasteDeck = new Stack<Card>();
        this.numItems = 0;
        for(int i = 0; i < Suit.values().length; i++){
            try{
                SuitDeck currSuit = new SuitDeck(Suit.values()[i]);
                while(!(currSuit.isEmpty())){
                    this.deck.push(currSuit.draw());
                    this.numItems++;
                }
            }catch(SuitDeckCreationException e){
                System.out.println(e);
                throw new DeckCreationException();
            }catch(DeckEmptyException f){
                System.out.println(f);
                throw new DeckCreationException();
            }
        }
    }
    
    /**
     * Creates a deck from a single suit
     * @param suit
     */
    public Deck(Suit suit){
    }
    
    /**
     * Replaces drawn cards and shuffles deck.
     * @throws DeckEmptyException
     */
    void shuffle(){
        //Replace waste Cards
        while(!wasteDeck.isEmpty()){
            this.deck.push(this.wasteDeck.pop());
            this.numItems++;
        }
        Collections.shuffle(this.deck, new Random(System.nanoTime()));    
    }
    
    /**
     * Draws a card from the deck
     * @return Drawn Card
     * @throws DeckEmptyException
     */
    Card draw() throws DeckEmptyException{
        if(this.deck.isEmpty()){
            throw new DeckEmptyException();
        }
        Card r = this.deck.pop();
        this.numItems--;
        this.wasteDeck.push(r);
        return r;
    }
    
    Card draw(Boolean faceUp) throws DeckEmptyException{
        if(this.deck.isEmpty()){
            throw new DeckEmptyException();
        }
        Card r = this.deck.pop();
        this.numItems--;
        this.wasteDeck.push(r);
        r.setFaceUp(faceUp);
        return r;
    }
    
    /**
     * Number of items currently in the deck
     * @return numItems
     */
    int size(){
        return numItems;
    }
    
    Boolean isEmpty(){
        if(numItems == 0){
            return true;
        }
        return false;
    }
}
