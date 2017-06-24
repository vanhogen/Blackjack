import java.util.ArrayList;
import java.util.List;

/**
 *<p><b>Player</b></p>
 *<p>
 * Player class contains and handles information regarding state of player and their hand
 *
 * @author vanhonat
 *
 */
public class Player {
    List<Card> hand;
    Boolean isDealer;
    double wallet;
    double moneyInPot;
    
    public Player(){
        this.hand = new ArrayList<Card>();
        this.isDealer = false;
        this.wallet = 0.0;
    }
    public Player(Boolean isDealer){
        this();
        this.isDealer = isDealer;
    }
    public Player(Boolean isDealer, double wallet){
        this(isDealer);
        this.wallet = wallet;
    }
    public Player(double wallet){
        this();
        this.wallet = wallet;
    }
    
    void bet(double betAmount) throws OutOfMoneyException{
        if(betAmount > (wallet)){
            throw new OutOfMoneyException();
        }
        this.wallet -= betAmount;
        this.moneyInPot += betAmount;
    }
    
    /**
     * Clear money in pot, and add share of pot to wallet.
     * @param walletChange
     */
    void resolveBet(double walletChange){
        if (walletChange < 0.0){
            throw new IllegalArgumentException();
        }
        
        this.moneyInPot = 0.0;
        this.wallet += walletChange;       
    }
    
    void setDealer(Boolean isDealer){
        this.isDealer = isDealer;
    }
    void cashIn(double amount){
        if (amount < 0){
            throw new IllegalArgumentException();
        }
        wallet += amount;
    }
    /**
     * Returns the contents of the wallet, emptying the player's wallet
     * @return 
     */
    double cashOut(){
        double r = this.wallet;
        this.wallet = 0.0;
        return r;
    }
    /**
     * Will throw OutOfMoneyException if amount is greater than currently in wallet
     * @param amount
     * @return
     */
    double cashOut(double amount) throws OutOfMoneyException{
        if (amount > this.wallet){
            throw new OutOfMoneyException();
        }
        //If cashing out whole wallet use no args cashOut to return everything
        if(amount == this.wallet){
            return this.cashOut();
        }
        this.wallet -= amount;
        return amount;
    }
    
    
    

}
