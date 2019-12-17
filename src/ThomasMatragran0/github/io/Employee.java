package ThomasMatragran0.github.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private String name;
    private String username;
    private String password;
    private String email;

    public Employee(String name, String password) {
        this.password = password;
        this.name = name;
        checkName();
        isValidPassword();
    }

    public static boolean containsWhiteSpace(final String testCode) {
        if (testCode != null) {
            for (int i = 0; i < testCode.length(); i++) {
                if (Character.isWhitespace(testCode.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

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

    private void setUsername() {

        char space;
        //  username = "userTEST";
        //  username = name.substring(0,1)+name.substring();
        for (int i = 0; i < name.length(); i++) {

            space = name.charAt(i);
            if (space == ' ') {
                username = name.substring(0, 1).toLowerCase() + name.substring(i + 1).toLowerCase();
            }
        }
    }

    private boolean checkName() {
        String last;
        char space;
        for (int i = 0; i < name.length(); i++) {

            space = name.charAt(i);
            if (space == ' ') {
                // username = name.substring(0,1)+name.substring(i+1);
                // email = name.substring(0,i)+"."+name.substring(i+1)+"@oracleacademy.Test";
                setUsername();
                setEmail();
                return true;
            } else username = "default";
            email = "user@oracleacademy.Test";
        }

        return false;
    }

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
            }

            else if(!matcher.matches())
                hasSpecial = true;
            if (hasCap && hasLow && hasSpecial) {
                return true;
            } else password = "pw";
        }
        return false;
    }

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
