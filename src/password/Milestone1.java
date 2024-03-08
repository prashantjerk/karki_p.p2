/*
*   @author: Prashant Karki
* CS241
* Spring 2024
*/

package password;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

// Use this class to provide a console interface for Milestone 1

//Your milestone 1 program will pit the AI password maker against a human password
//breaker. The program should use the longer password bank. The UI should match the
//specifications above. The focus of this milestone is the UI and similarity score.

public class Milestone1 {
    public static void main(String[] args) throws IOException {
        File file = null;
        try {
            Scanner input = new Scanner(System.in);
            // read from the file
            file = new File("src\\resources\\short.password.bank.txt");
            Scanner fileInput = new Scanner(file, StandardCharsets.UTF_8);
            Collection<Password> passwordCollection = new ArrayList<>();

            // add passwords from file to the collection
            while (fileInput.hasNextLine()) {
                Password password = new Password(fileInput.next());
                passwordCollection.add(password);
            }
            fileInput.close();

            // ask length from user and filter out possible passwords of that length
            out.print("Select password length: ");
            int passwordLength = input.nextInt();
            PasswordBank passwordBank = new PasswordBank(passwordCollection);
            Collection<Password> passwordListOfLength = passwordBank.passwordOfLength(passwordLength);

            // Iterator<Password> itr = passwordListOfLength.iterator();
            // displays the list of possible passwords of the length # in the console
//            out.println("Possible Passwords: ");
//            while(itr.hasNext()) {
//                out.println(itr.next());
//            }


            // Random Password Maker generates a random password
            RandomPasswordMaker randomPasswordMaker = new RandomPasswordMaker();
            Password randomPassword = randomPasswordMaker.selectPassword(passwordListOfLength);
            out.println("secret password: " + randomPassword);

            // Console Password Breaker asks user to guess a password
            ConsolePasswordBreaker consolePasswordBreaker = new ConsolePasswordBreaker(new Scanner(System.in), new PrintStream(out));
            boolean passwordMatched = false;

            do {
                Password userGuess = consolePasswordBreaker.nextGuess();



                out.println("Similarity: " + randomPassword.similarity(userGuess));
                if(randomPassword.similarity(userGuess) == passwordLength) {
                    passwordMatched = true;
                    out.println("Game Over!");
                }
            } while(passwordMatched == false);
        } catch (IOException e) {
            throw new IOException(String.format("Could not open %s", file));
        }
    }
}
