import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <p><b>
 * Project: Blackjack
 * </b></p>
 * <P>Write a program that allows a human user to play a simplified version
 * of Blackjack against a computer opponent.  Make it as cool as you can.</p>
 * 
 * <P>The simplified blackjack rules are as follows:</P>
 * 
 * <UL>
 * <LI>Don't worry about suits or face cards; "cards" will have values from
 * 2-11, and all values are equally likely (that is, unlike a real blackjack
 * game, there's no greater chance of drawing a card with value 10).
 * <LI>Draw two cards for the player and display them.
 * <LI>Draw two cards for the "dealer" and display one of them, keeping the
 * other one hidden.
 * <LI>Allow the player to "hit" as many times as he would like.
 * <LI>If the player "busts" (gets a total over 21), the dealer
 * automatically wins.
 * <LI>Allow the dealer to hit as many times as he would like.  Dealer
 * should probably hit on sixteen or lower.
 * <LI>If the dealer busts, the player automatically wins.
 * <LI>Assuming no one has busted, the player with the highest total wins.
 * Dealer wins all ties.
 * </UL>
 *
 *
 *<P>As will be the case with all projects, this is not an assignment with a
 *fixed goal.  Programs that merely do what is listed above will be
 *passing, but will certainly not be worth 100.  In order to score a high
 *grade, programs must go above and beyond the specifications.  Here are
 *suggested additional features to add:</P>
 *
 *<UL>
 *<LI>Use realistic card values, with suits and faces from ace to king.
 *<LI>Incorporate wagering.
 *<LI>Display some sort of graphical cards.
 *<LI>Anything else interesting you can think of.
 *</UL>
 *
 *
 *<P>Be aware that you won't get any extra points for adding additional
 *features if the basic program doesn't work.  That is, if your program
 *can't successfully do the basics listed above, no amount of bells and
 *whistles will save your grade.  Programs will be graded on the
 *following criteria:</P>
 *
 *<UL>
 *<LI>Functionality - Does your program fulfill the basic requirements?  Is it
 *done?  And what else does it do? (50%)
 *<LI>Overall Impression - Is your program efficiently organized, or is there
 *a lot of duplicated code?  Does it look well-written, or barely finished? (25%)
 *<LI>Bugs - does it compile?  Are there obvious errors? Are there subtle
 *errors? (10%)
 *<LI>Internal Documentation - How easy is your code to understand?  Are
 *you using good variable names?  Are there any comments? (10%)
 *<LI>Readability - Is your program consistently indented in a manner
 *that reflects the structure of your code?  Is it easy to read?
 *Are there blank lines which break up the major sections of your code?
 *(5%)
 *</UL>
 * 
 * @author Nathan Van Hogen - vanhonat@gmail.com
 *
 */
public class Blackjack {
    
    static final char PROMPT_CHAR = '>';

    public static void main(String[] args) throws SuitDeckCreationException {

        if (args.length != 0) {
            System.out.println("This program does not accept arguments");
        }

        //New deck and shuffle
        Deck playDeck = null;
        try{
            playDeck = new Deck();
        }catch(DeckCreationException e){
            e.printStackTrace();
            System.exit(1);
        }
        playDeck.shuffle();
        playDeck.shuffle();
        List<Player> playerList = new LinkedList<Player>();
        playerList.add(new Player());
        Player dealer = new Player(true);
        
        Scanner sysInScanner= new Scanner(System.in);
        
        Boolean stillPlaying = promptDeal(sysInScanner);
        
        while(stillPlaying){
            promptBet(sysInScanner, playerList);
            try{
                dealPlayers(playerList, playDeck, true);
                dealer.draw(playDeck, true);
                dealPlayers(playerList, playDeck, true);
                dealer.draw(playDeck);
            }catch(DeckEmptyException e){
                System.out.println("Error in deck of cards during play");
                e.printStackTrace();
                sysInScanner.close();
                System.exit(1);
            }
            printField(playerList);
            resolveNaturals(playerList, dealer);
            
            stillPlaying = promptDeal(sysInScanner);
            
        }
        

    }
    
    private static void resolveNaturals(List<Player> playerList, Player dealer){        
        if(playerList.equals(null) || dealer.equals(null) || playerList.isEmpty()){
            throw new IllegalArgumentException();
        }
        List<Card> dealerHand = dealer.getHand();
        for(int i = 0; i < dealerHand.size(); i++){
            if(dealerHand.get(i).getPointValue() == 10){
            }
        }
        if(dealer.getHand())
        
        for(int i = 0; i < playerList.size(); i++){
            if(has21(playerList.get(i))){
                
            }
        }
        

    }
    
    private static void dealPlayers(List<Player> playerList, Deck playDeck, Boolean faceUp) throws DeckEmptyException{
        if(playDeck.equals(null)){
            throw new DeckEmptyException();
        }
        if(faceUp.equals(null) || playerList.equals(null)){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < playerList.size(); i++){
            playerList.get(i).draw(playDeck, faceUp);
        }
    }

    private static void printField(List<Player> playerList) {
        for(int i = 0; i < playerList.size(); i++){
            playerList.get(i).printHand();
        }
    }

    /**
     * prompts the list of users for bet amounts
     * @param sysInScanner
     * @param playerList
     * @return true if all bets were placed
     */
    private static void promptBet(Scanner sysInScanner, List<Player> playerList) {
        Boolean r = false;
        for(int i = 0; i < playerList.size(); i++){
            r = promptBet(sysInScanner, playerList.get(i));
            while(!r){
                promptBet(sysInScanner, playerList.get(i));
            }
        }
    }

    /**
     * prompts the user for a bet amount and verifies
     * @param sysInScanner
     * @param tarPlayer
     * @return
     */
    private static Boolean promptBet(Scanner sysInScanner, Player tarPlayer){
        System.out.println("Enter Bet Amount");
        System.out.print(PROMPT_CHAR);
        String userInput = sysInScanner.nextLine().trim();
        return validBet(userInput, tarPlayer);
    }

    /**
     * Prompts the user as to whether another game should be dealt
     * @param sysInScanner
     * @return
     */
    private static Boolean promptDeal(Scanner sysInScanner) {
        System.out.println("Deal? (y/n)");
        System.out.print(PROMPT_CHAR);
        String userInput = sysInScanner.nextLine().trim();
        if(!userInput.equals(null) && userInput.length() == 1 && (userInput.charAt(0) == 'y')){
            return false;
        }
        return true;
    }

    /**
     * Verifies a given string is a valid double value, that does not exceed the player's wallet, and places bet. 
     * @param userInput
     * @return
     */
    private static boolean validBet(String userInput, Player bettingPlayer) {
        if(userInput.equals(null)){
            return false;
        }
        try{
            Double bet = Double.parseDouble(userInput);
            bettingPlayer.bet(bet);
        }catch(NumberFormatException e){
            System.out.println("Invalid bet format");
            return false;
        }catch(OutOfMoneyException e){
            System.out.println("Insufficient funds");
            return false;
        }
        return true;
    }
    
    

}
