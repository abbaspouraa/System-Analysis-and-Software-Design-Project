package model;

/**
 * Registered user that extends the User class.
 * @author Amir Abbaspour , Brandon Attai
 */
public class RegisteredUser extends User{

    /**
     * Constructor to generate a user with a username and password
     * @param userName The username of the user
     * @param password The password of the user
     */
    public RegisteredUser(String userName, String password, boolean hasNotPaidFee) {
        super(userName, password);
        setUserType("Registered"); //Added to every registered user to determine type
        setAnnualFee(hasNotPaidFee);
    }

    /**
     * Overridden equals method for comparision
     * @param o Other user
     * @return Boolean - true if the user object is the same else false
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    
}
