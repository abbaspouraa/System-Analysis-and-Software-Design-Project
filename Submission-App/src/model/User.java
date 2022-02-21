package model;

import java.util.Objects;

/**
 * General users class.
 * @author Amir Abbaspour , Brandon Attai
 */
public class User {

    private String userName;
    private String userPass;
    private String userType;
    private boolean annualFee;

    //Constructor for regular user

    /**
     * Constructor with all the params
     * @param userName username of the user
     * @param userPass password of the user
     * @param userType type of the user ("guest" or "Registered")
     */
    public User(String userName, String userPass, String userType) {
        this.userName = userName;
        this.userPass = userPass;
        this.userType = userType;
        
        //ADDED
    }

    /**
     * Constructor for guest users only
     * @param username username of the user
     * @param password password of the user
     */
    public User(String username, String password) {
        this.userName = username;
        this.userPass = password;
        this.userType = "guest";
        this.setAnnualFee(false);
    }

    //Getters and setters
    public String getUserInfo(){
        return getUserName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Two users are equal if their username and password are matched
     * @param o the object to compare with "this" object
     * @return tru if the two objects have the same username and pass
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() && getClass() != RegisteredUser.class) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userPass, user.userPass);
    }

    /**
     * Hash code of the object parameters
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass, userType);
    }

    /**
     * Overridden toString to represent the user as a
     * string.
     *
     * @return String representing the user object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    /**
     * Method to determine if the user has an annual fee due.
     * @return boolean - false if not required to pay, else true
     */
	public boolean isAnnualFee() {
		return annualFee;
	}

    /**
     * Method to set if the user is required to pay an annual
     * fee.
     * @param annualFee The boolean if the user has to pay a fee, i.e. true
     */
	public void setAnnualFee(boolean annualFee) {
		this.annualFee = annualFee;
	}
}
