package model;

/**
 * Interface representing the Payment Strategy to be executed.
 * @author Amir Abbaspour , Brandon Attai
 */
public interface PaymentStrategy {

    /**
     * Method to make payment
     * @param accountID User's account ID
     * @param amount Amount to be paid
     * @return boolean - true/false if processed.
     */
    boolean makePayment(long accountID, double amount);
}
