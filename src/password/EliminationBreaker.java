// @author: Prashant Karki
// CS241
//Spring 2024

package password;

public class EliminationBreaker implements PasswordBreaker{
    // Creates an elimination breaker that uses the given collection of passwords as its initial collection of possible passwords.
    public EliminationBreaker(java.util.Collection<Password> passwords){

    }

    @Override
    public void guessResults(Password guess, int similarity) {

    }

    @Override
    public Password nextGuess() {
        return null;
    }

    @Override
    public int possiblePasswordCount() {
        return 0;
    }
}
