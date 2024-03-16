/*
 *   @author: Prashant Karki
 * CS241
 * Spring 2024
 */

package password;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.System.out;

public class Milestone2 {
    public static void main(String[] args) throws IOException {
        File file = null;
        try {
            Scanner input = new Scanner(System.in);
            // read from the file
            file = new File("src\\resources\\password.bank.txt");
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

            // for collection of passwords of the length given by the user
            PasswordBank passwordBank = new PasswordBank(passwordCollection);
            Collection<Password> passwordListOfLength = passwordBank.passwordOfLength(passwordLength);
            for(Password password: passwordListOfLength) {
                out.println(password);
            }

            // let user create a secret password
            out.print("Please enter one of the passwords: ");
            Password secretPassword = new Password(input.next());
            out.println("secret password: " + secretPassword);  // display the password on the console

            // creating eliminationBreaker and creating an ArrayList for the passwords that are not eliminated
            EliminationBreaker eliminationBreaker = new EliminationBreaker(passwordListOfLength);

            int attempt = 1;
            int similarity = 0;
            do {
                int numOfPossiblePass = eliminationBreaker.possiblePasswordCount();
                out.println("Possible passwords: " + numOfPossiblePass);

                Password AIGuess = eliminationBreaker.nextGuess();
                out.println("Breaker's guess(" + attempt + "): " + AIGuess);
                similarity = secretPassword.similarity(AIGuess);
                out.println("similarity: " + similarity);
                eliminationBreaker.guessResults(AIGuess, similarity);
                attempt++;
            } while(passwordLength != similarity);

            out.println("Game over!");


        } catch (IOException e) {
            throw new IOException(String.format("Could not open %s", file));
        }    }
}

