package model;

/**
 * Interface representing the Refund Strategy to be executed.
 * @author Amir Abbaspour , Brandon Attai
 */
public interface UserRefundInterface {

    /**
     * Method to perform the refund.
     * @param amount The amount to be refunded.
     * @return The amount.
     */
    double refund(double amount);
}
