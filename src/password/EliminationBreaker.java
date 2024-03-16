// @author: Prashant Karki
// CS241
//Spring 2024

package password;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class EliminationBreaker implements PasswordBreaker{
    private Collection<Password> passwords = new ArrayList<>();
    int count;

    // Creates an elimination breaker that uses the given collection of passwords as its initial collection of possible passwords.
    public EliminationBreaker(java.util.Collection<Password> passwords){
        this.passwords = passwords;
        count = passwords.size();

        // sorting passwords
        Collections.sort((ArrayList<Password>) passwords);
    }

    // Use these results to eliminate passwords which can no longer be the secret password.
    @Override
    public void guessResults(Password guess, int similarity) {
        Iterator<Password> iter = passwords.iterator();
        Collection<Password> otherPossiblePassword = new ArrayList<>();
        count = 0;
        while(iter.hasNext()) {
            Password nextPass = iter.next();
            if(guess.similarity(nextPass) == similarity) {
                otherPossiblePassword.add(nextPass);
                count++;
            }
        }
        passwords = otherPossiblePassword;
    }

    @Override
    public Password nextGuess() {
        Iterator<Password> iter = passwords.iterator();
        return iter.next();
    }

    @Override
    public int possiblePasswordCount() {
        return count;
    }
}
