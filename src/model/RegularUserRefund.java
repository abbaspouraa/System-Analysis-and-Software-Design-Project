package model;

/**
 * Class representing concrete implementation for a refund to a regular user.
 * @author Amir Abbaspour , Brandon Attai
 */
public class RegularUserRefund implements UserRefundInterface{

    /**
     * Regular user refund amount, with a 15% penalty attached.
     * @param amount The amount to be refunded.
     * @return double representing the amount to be refunded.
     */
    @Override
    public double refund(double amount) {
            return amount*0.85; //Applying 15% penalty
    }
}
