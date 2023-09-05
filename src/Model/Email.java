package Model;

/**
 * The {@code Email} class is an abstract base class that represents the basic functionality
 * of an email service. This class defines two abstract methods: {@code registration()} and {@code login()},
 * which must be implemented by concrete subclasses to provide specific functionality for email services.
 *
 * <p>Subclasses of this class should implement the registration and login logic based on the
 * requirements of the specific email service they represent, such as Gmail, Yahoo Mail, or Outlook.
 *
 * <p>Typically, the {@code registration()} method would handle the process of creating a new email
 * account with the email service, while the {@code login()} method would handle the authentication
 * and login process for existing users.
 */
 public abstract class Email {

    public abstract void registration();
    public abstract void login();
}
