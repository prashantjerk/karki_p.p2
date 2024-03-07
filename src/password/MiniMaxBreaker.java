/*
 * @author: Prashant Karki
 * CS241
 * Spring 2024
 */


package password;

public class MiniMaxBreaker implements PasswordBreaker{
    // Creates a minimax breaker that uses the given collection of passwords as the collection of all the potential passwords.
    public MiniMaxBreaker(java.util.Collection<Password> passwords){

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
