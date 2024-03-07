/*
*   @author: Prashant Karki
* CS241
* Spring 2024
*/

package password;

public interface PasswordMaker {
    // Asks the maker to choose a password from the given collection of passwords
    public Password selectPassword(java.util.Collection<Password> possiblePasswords);
}
