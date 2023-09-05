package Controller;

import Model.Email;
import Model.Gmail;
import Model.Mail_ru;
import java.util.Scanner;
/**
 * The `Browser` class represents a simple command-line browser application for searching and accessing email services.
 * It provides a text-based interface for searching and interacting with supported email services such as Mail.ru and Gmail.
 * Users can search for their favorite email service and perform actions like registration and login within the chosen service.
 * This class utilizes ANSI color codes for a colorful console output.
 *
 * Supported Email Services:
 * - Mail.ru
 * - Gmail
 *
 * Usage:
 * 1. Create an instance of the `Browser` class.
 * 2. Call the `search` method to initiate a search for an email service.
 * 3. Follow the on-screen instructions to select an email service and perform actions within it.
 * 4. After completing actions, the user can choose to search again or exit the browser.
 *
 * ANSI Color Codes:
 * - ANSI_RESET: Reset text color to the default.
 * - ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE:
 *   Color codes for text output to the console.
 *
 * Example Usage:
 * ```java
 * Browser browser = new Browser();
 * browser.search();
 * ```
 *
 * @see Email
 * @see Mail_ru
 * @see Gmail
 */
public class Browser {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";


    public void search() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_CYAN + "Search your favorite email service... " + ANSI_RESET);
        String searchResult = scanner.nextLine().toLowerCase();

        if (searchResult.contains("mail.ru") || searchResult.contains("mail.bk")) {
            Mail_ru mailRu = new Mail_ru();
            handleEmailService(mailRu);

        } else if (searchResult.contains("gmail.com") || searchResult.contains("gmail")) {
            Gmail gmail = new Gmail();
            handleEmailService(gmail);

        } else {
            System.out.println(ANSI_RED + "Unsupported email service." + ANSI_RESET);
        }

        System.out.println(ANSI_CYAN+"Do you want to search again? (yes/no)"+ANSI_RESET);
        String again = scanner.next().toLowerCase();
        if (again.contains("yes")) {
            search();
        }

    }


    public void handleEmailService(Email service) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_GREEN + "Choose an action:");
            System.out.println("1 - Registration");
            System.out.println("2 - Login");
            System.out.println("3 - Exit" + ANSI_RESET);


            int choice = in.nextInt();


            switch (choice) {
                case 1 -> service.registration();
                case 2 -> service.login();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Incorrect choice. Try again.");
            }
        }
    }

    }



