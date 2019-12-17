package ThomasMatragran0.github.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Thomas Matragrano
 * @brief This class creates an Employee and gives them a username, password, and email based on
 *     their name.
 */
public class Employee {
  private String name;
  private String username;
  private String password;
  private String email;
  /**
   * This Employee constructor takes two parameters and sets the initial password and name.
   *
   * @param name,password
   */
  public Employee(String name, String password) {
    this.password = password;
    this.name = name;
    checkName();
    isValidPassword();
  }

  /** This void method sets an Employee's email. */
  private void setEmail() {
    char space;
    for (int i = 0; i < name.length(); i++) {
      space = name.charAt(i);
      if (space == ' ') {
        String firstName = name.substring(0, i).toLowerCase();
        String lastName = name.substring(i + 1).toLowerCase();
        String address = "@oracleacademy.Test";
        email = firstName + "." + lastName + address;
      }
    }
  }
  /** This void method sets an Employee's username. */
  private void setUsername() {
    char space;
    for (int i = 0; i < name.length(); i++) {
      space = name.charAt(i);
      if (space == ' ') {
        username = name.substring(0, 1).toLowerCase() + name.substring(i + 1).toLowerCase();
      }
    }
  }
  /**
   * This boolean method checks a name input and creates a username if there are not spaces entered.
   *
   * @return false
   */
  private boolean checkName() {
    char space;
    for (int i = 0; i < name.length(); i++) {

      space = name.charAt(i);
      if (space == ' ') {
        setUsername();
        setEmail();
        return true;
      } else username = "default";
      email = "user@oracleacademy.Test";
    }

    return false;
  }
  /**
   * This boolean method determines whether a password is valid which includes a lowercase letter,
   * uppercase letter, and a special character
   *
   * @return false
   */
  private boolean isValidPassword() {

    boolean hasSpecial = true;
    boolean hasCap = false;
    boolean hasLow = false;
    char c;
    for (int i = 0; i < password.length(); i++) {

      Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
      Matcher matcher = pattern.matcher(password);

      boolean b = matcher.find();

      c = password.charAt(i);
      if (Character.isUpperCase(c)) {
        hasCap = true;
      } else if (Character.isLowerCase(c)) {
        hasLow = true;
      } else if (!matcher.matches()) hasSpecial = true;
      if (hasCap && hasLow && hasSpecial) {
        return true;
      } else password = "pw";
    }
    return false;
  }
  /**
   * This String method displys an employee's information
   *
   * @return a concatenated String with name, username, email, and password.
   */
  public String toString() {
    return "Employee Details\n"
        + "Name : "
        + name
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "\nInitial Password : "
        + password;
  }
}
