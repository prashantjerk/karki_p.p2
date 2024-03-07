// @author: Prashant Karki
// CS241
//Spring 2024

package password;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomPasswordMaker implements PasswordMaker{
    // Uses java.util.Random to randomly select a password from the given collection.
    @Override
    public Password selectPassword(Collection<Password> possiblePasswords) {
        Random rand = new Random();
        List<Password> possiblePasswordsList = (List<Password>) possiblePasswords;
        return possiblePasswordsList.get(rand.nextInt(possiblePasswordsList.size()));
    }
}
