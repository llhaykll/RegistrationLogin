package Model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 The User class represents a user entity with various personal information and credentials.
 * This class implements the Serializable interface, allowing objects of this class to be serialized.
 *
 * The User class has the following attributes:
 * - userName: The first name of the user.
 * - userLastname: The last name of the user.
 * - userGender: The gender of the user, which is a final field.
 * - birthDate: The date of birth of the user as a GregorianCalendar instance.
 * - userPhoneNumber: The phone number of the user.
 * - userEmailAddress: The email address of the user.
 * - userPassword: The password associated with the user's account.
 *
 * This class provides methods to access and manipulate these attributes, as well as overriding
 * the equals, hashCode, and toString methods for proper object comparison and string representation.
 */

public class User implements Serializable {

    private String userName;
    private String userLastname;
    private final String userGender;
    private final GregorianCalendar birthDate;
    private String userPhoneNumber;
    private String userEmailAddress;
    private String userPassword;

    public User(String userName, String userLastname, String userGender, GregorianCalendar birthDate,
                String userPhoneNumber, String userEmailAddress, String userPassword) {
        this.userName = userName;
        this.userLastname = userLastname;
        this.userGender = userGender;
        this.birthDate = birthDate;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmailAddress = userEmailAddress;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public String getUserGender() {
        return userGender;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userLastname, user.userLastname) &&
                Objects.equals(userGender, user.userGender) && Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(userPhoneNumber, user.userPhoneNumber) &&
                Objects.equals(userEmailAddress, user.userEmailAddress) && Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userLastname, userGender, birthDate, userPhoneNumber, userEmailAddress, userPassword);
    }

    @Override
    public String toString() {
        return "User {" +
                "userName = '" + userName + '\'' +
                ", userLastname = '" + userLastname + '\'' +
                ", userGender = '" + userGender + '\'' +
                ", birthDate = " + birthDate.toString() +
                ", userPhoneNumber = '" + userPhoneNumber + '\'' +
                ", userEmailAddress = '" + userEmailAddress + '\'' +
                ", userPassword = '" + userPassword + '\'' +
                '}';
    }
}
