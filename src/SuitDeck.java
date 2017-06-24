import java.util.Stack;

class SuitDeck extends Deck{
    private Suit suit;
    
    
    
    public SuitDeck (Suit suit) throws SuitDeckCreationException{
        super(suit);
        if(suit == null){
            throw new SuitDeckCreationException();
        }
        this.deck = new Stack<Card>();
        this.wasteDeck = new Stack<Card>();
        this.numItems = 0;
        this.suit = suit;
        try{
            for(int i = 0; i < FaceValue.values().length; i++){
                this.deck.push(new Card(FaceValue.values()[i],suit));
                this.numItems++;
            }
        }catch(CardCreationException e){
            this.deck = null;
            throw new SuitDeckCreationException();
        }
        
    }
    
    Suit getSuit(){
        return this.suit;
    }
    
}
