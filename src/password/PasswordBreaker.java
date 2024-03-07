// @author: Prashant Karki
// CS241
//Spring 2024

package password;

public interface PasswordBreaker {
    // Tells the breaker the results of the given guess (the result of the previous call to nextGuess()).
    public void guessResults(Password guess, int similarity);

    // Calculates and returns this breaker's next guess.
    public Password nextGuess();

    // Returns how many passwords this breaker thinks are still possibly the secret password.
    public int possiblePasswordCount();

}
