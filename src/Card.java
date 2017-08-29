
/**
 *<p><b>Card</b></p>
 *<p>
 * Stores point value, face value, and suit as would be found in a deck of cards
 * @author Nathan Van Hogen - vanhonat@gmail.com
 *
 */
class Card {
    
    private FaceValue faceValue;
    private int value;
    private Suit suit;
    private String cardName;
    private Boolean faceUp;
    
    
    /**
     * Constructor
     * @param faceValue
     * @param suit
     * @throws ValueNotFoundException
     */
    Card(FaceValue faceValue, Suit suit) throws CardCreationException{
        if( faceValue == null || suit == null){
            throw new CardCreationException();
        }
        this.faceValue = faceValue;
        this.suit = suit;
        try{
            this.value = findValue(faceValue);
        }catch(IllegalArgumentException e){
            throw new CardCreationException();
        }
        this.faceUp = false;
    }
    
    /**
     * Returns the point value as integer of the faceValue of the card. Sets card name based on type.
     * @param faceValue
     * @return value corresponding to faceValue
     * @throws ValueNotFoundException
     */
    private int findValue(FaceValue faceValue) throws IllegalArgumentException{
        switch(faceValue){
            case CARD2:
                this.cardName = "2";
                return 2;
            case CARD3:
                this.cardName = "3";
                return 3;
            case CARD4:
                this.cardName = "4";
                return 4;
            case CARD5:
                this.cardName = "5";
                return 5;
            case CARD6:
                this.cardName = "6";
                return 6;
            case CARD7:
                this.cardName = "7";
                return 7;
            case CARD8:
                this.cardName = "8";
                return 8;
            case CARD9:
                this.cardName = "9";
                return 9;
            case CARD10:
                this.cardName = "10";
                return 10;
            case CARDJ:
                this.cardName = "J";
                return 10;
            case CARDQ:
                this.cardName = "Q";
                return 10;
            case CARDK:
                this.cardName = "K";
                return 10;
            case CARDA:
                this.cardName = "A";
                return 10;
            default: 
                throw new IllegalArgumentException();
        }
    }
    
    /**
     * Getter for suit
     * @return suit of card
     */
    Suit getSuit(){
        if(faceUp){
            return this.suit;
        }
        return null;
        
    }
    /**
     * Getter for face value
     * @return face value of card
     */
    FaceValue getFaceValue(){
        if(faceUp){
            return this.faceValue;
        }
        return null;
    }
    /**
     * Getter for point value
     */
    Integer getPointValue(){
        if(faceUp){
            return this.value;
        }
        return null;
    }
    
    public String toString(){
        if(faceUp){
            return this.cardName + " " + this.suit;
        }
        return null;
    }
    
    public Boolean isFaceUp(){
        return faceUp;
    }
    
    public void setFaceUp(Boolean faceUp){
        this.faceUp = faceUp;
    }
    
    public void flip(){
        this.faceUp = !this.faceUp;
    }
}
