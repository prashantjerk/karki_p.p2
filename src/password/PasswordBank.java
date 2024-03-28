// @author: Prashant Karki
// CS241
//Spring 2024

package password;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PasswordBank {
    private Collection<Password> passwords;
    // Constructor that takes in a collection of passwords.
    public PasswordBank(Collection<Password> passwords) {
        this.passwords = passwords;
    }

    // This method will create and return a Collection of passwords of the given length.
    public Collection<Password> passwordsOfLength(int length) {
        List<Password> collectionOfPasswords = new ArrayList<>();
        for(Password password: passwords) {
            if(password.length() == length) {
                collectionOfPasswords.add(password);
            }
        }
        return collectionOfPasswords;
    }
}
