// @author: Prashant Karki
// CS241
//Spring 2024

package password;

import java.util.Scanner;

public class ConsolePasswordBreaker implements PasswordBreaker{
    private java.util.Scanner scanner;
    private java.io.PrintStream out;
    // Constructor that takes the necessary I/O classes to read user input and print output.
    public ConsolePasswordBreaker(java.util.Scanner scanner, java.io.PrintStream out){
        this.scanner = scanner;
        this.out = out;
    }

    // In this class, this method does not need to do anything.
    @Override
    public void guessResults(Password guess, int similarity) {

    }

    // Uses the console (standard in and out) to ask the user for their next guess.
    @Override
    public Password nextGuess() {
        out.print("Please enter one of the passwords: ");
        String nextGuess = scanner.nextLine();
        return new Password(nextGuess);
    }

    // In this class, this method does not need to do anything.
    @Override
    public int possiblePasswordCount() {
        return 0;
    }
}
