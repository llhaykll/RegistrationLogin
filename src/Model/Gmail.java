package Model;

import java.io.*;
import java.util.*;

/**
 * The Gmail class extends the Email class and represents a simplified Gmail-like email system.
 * It allows users to register, login, and perform basic email-related operations.
 *
 * This class includes methods for user registration, user login, and file operations to store user data.
 * It also contains ANSI escape codes for colored console output.
 *
 * Gmail users are stored in a HashMap called "gmailUsers" using their email addresses as keys.
 * User data is serialized and saved to files on the local file system.
 */

public class Gmail extends Email {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";



    private static Map<String,User> gmailUsers = new HashMap<>();


    /**
     *  Performs user registration, gathering user information, and storing it in memory and as a serialized file.
     *      * The method collects the user's name, lastname, gender, birthdate, phone number, email, and password.
     *      * After successful registration, the user data is added to the "gmailUsers" map and saved to a file.
     *
     */

    @Override
    public void registration() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = in.next();

        System.out.print("Enter lastname: ");
        String lastname = in.next();

        String gender = readValidGender(in);

        System.out.print("Enter birthday year: ");
        int year = in.nextInt();

        int month = readValidMonth(in);
        int day = readValidDay(in, year, month);
        GregorianCalendar birthDate = new GregorianCalendar(year, month - 1, day); //  <---- birthday date

        String phone = phoneNumberCheck(in);

        String email = correctEmailAddressForReg(in);
        String password = correctPassword(in);


        User user = new User(name,lastname,gender,birthDate,phone,email,password);

        gmailUsers.put(user.getUserEmailAddress(),user);

        writeObjectToFile(user); // writing object to file


    }


    /**
     * Performs user login by verifying the provided email and password.
     * The method prompts the user for their email and password, checks if the user exists in the system,
     * and verifies the provided password.
     * It then provides appropriate feedback based on the login result.
     */
    @Override
    public void login() {


        Scanner in = new Scanner(System.in);


        String email = correctEmailAddressForLogin(in);

        File userFile = new File("C:\\Users\\Hayk\\Desktop\\Users\\" + email + ".txt");

        if (userFile.exists()) {

                System.out.print("Enter password: ");
                String password = in.next();

                User user = readUserData(email);

                if (user != null && user.getUserPassword().equals(password)) {
                    System.out.println(ANSI_YELLOW + "Login successful!" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Wrong password or email. Try again." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "User with this email is not registered." + ANSI_RESET);
            }


        }


    /**
     * Writes a User object to a file on the local file system.
     *
     * @param user The User object to be serialized and saved.
     */
    private void writeObjectToFile(User user){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Hayk\\Desktop\\Users\\"+user.getUserEmailAddress()+".txt"))) {

            out.writeObject(user);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Reads user data from a file based on their email address.
     *
     * @param email The email address of the user to retrieve data for.
     * @return A User object containing the user's data, or null if the user does not exist.
     */
    public User readUserData(String email) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\Hayk\\Desktop\\Users\\"+email+".txt"))) {


            User user = (User) in.readObject();
            if (user != null) {
                return user;
            }


        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        }



    private static String phoneNumberCheck(Scanner in) {
        String phone;

        while (true) {
            System.out.print("Enter phone number (example +37496280886): ");
            phone = in.next();

            if (phone.startsWith("+") && phone.length() >= 10 && phone.substring(1).matches("[0-9]+")) {
                return phone;
            } else {
                System.out.println("Please enter valid phone number");
            }
        }
    }

    private static String correctEmailAddressForLogin(Scanner in){
        String email;

        while (true) {
            System.out.print("Enter your email: ");
            email = in.next();


           if (!(email.endsWith("@gmail.com"))) {
                System.out.println(ANSI_RED+"Invalid domain. Please enter valid domain (@gmail.com)"+ANSI_RESET);

            } else {
                return email;
            }
        }
    }

    private static String correctEmailAddressForReg(Scanner in) {
        String email;

        while (true) {
            System.out.print("Enter your email: ");
            email = in.next();

            File userFile = new File("C:\\Users\\Hayk\\Desktop\\Users\\" + email + ".txt");

            if (gmailUsers.containsKey(email) || userFile.exists()) {
                System.out.println(ANSI_RED+"User with this email is already registered."+ANSI_RESET);

            } else if (!(email.endsWith("@gmail.com"))) {
                System.out.println(ANSI_RED+"Invalid domain. Please enter valid domain (@gmail.com)"+ANSI_RESET);

            } else {
                return email;
            }
        }
    }

    private static String correctPassword(Scanner in) {
        String password;

        while (true) {
            System.out.print("Enter password: ");
            password = in.next();

            if (password.length() >= 8) {
                System.out.println(ANSI_CYAN+"Registration successful!"+ANSI_RESET);
                return password;

            } else {
                System.out.println(ANSI_RED+"Password is too short. Enter a longer password min 8 symbols!"+ANSI_RESET);
            }
        }
    }



    private static String readValidGender(Scanner in) {
        String gender;
        do {
            System.out.print("Please choose a valid gender (male/female): ");
            gender = in.next().toLowerCase();
        } while (!gender.equals("male") && !gender.equals("female"));
        return gender;
    }

    private static int readValidMonth(Scanner in) {
        int month;
        do {
            System.out.print("Please enter a valid month (1-12): ");
            month = in.nextInt();
        } while (month < 1 || month > 12);
        return month;
    }

    private static int readValidDay(Scanner in, int year, int month) {
        int maxDays = new GregorianCalendar(year, month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        int day;
        do {
            System.out.print("Please enter a valid day (1-" + maxDays + "): ");
            day = in.nextInt();
        } while (day < 1 || day > maxDays);
        return day;
    }
}
