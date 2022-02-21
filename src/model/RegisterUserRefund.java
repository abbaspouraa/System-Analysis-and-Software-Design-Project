package model;

/**
 * Class representing concrete implementation for a refund to a registered user.
 * @author Amir Abbaspour , Brandon Attai
 */
public class RegisterUserRefund implements UserRefundInterface{

    /**
     * Overridden refund method to return full amount to registered user.
     * @param amount The amount to be refunded.
     * @return double - representing the amount to be refunded.
     */
    @Override
    public double refund(double amount) {
            return amount;
        }
}
