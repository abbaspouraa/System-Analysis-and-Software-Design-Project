package model;

/**
 * Strategy Pattern - Concrete Strategy that implements Payment Strategy to handle
 * Credit Card transactions. This is the default payment strategy for the movie reservation
 * app implementation. Accepts an account ID and an amount and processes payment.
 * @author Amir Abbaspour , Brandon Attai
 */
public class CreditCardStrategy implements PaymentStrategy{


    /**
     * Method to make payment, checks that the accountID is not 0 and the
     * amount for payment is greater than 0.
     *
     * @param accountID User's account ID
     * @param amount Payment amount
     * @return Boolean true/false if processed.
     */
    @Override
    public boolean makePayment(long accountID, double amount) {
        //Simple checks for account id and amount
        if(accountID != 0 && amount > 0) {
            // Send a boolean to payment object
            return true;
        }else{
            return false;
        }
    }
}
