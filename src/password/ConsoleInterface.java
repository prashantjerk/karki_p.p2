// @author: Prashant Karki
// CS241
//Spring 2024
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

public class ConsoleInterface {
    public static void main(String[] args) throws IOException {
        File file;
        String errorMsg = null;
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
            errorMsg = String.format("Could not open %s", file);
            fileInput.close();

            PasswordMaker passwordMaker = null;
            PasswordBreaker passwordBreaker = null;
            Password secretPassword = null;

            int attempt = 1;
            int similarity;

            // ask length from user and filter out possible passwords of that length
            out.print("Select password length: ");
            int passwordLength = input.nextInt();

            // collection of passwords of the length given by the user
            PasswordBank passwordBank = new PasswordBank(passwordCollection);
            Collection<Password> passwordListOfLength = passwordBank.passwordsOfLength(passwordLength);

            // ask the password maker type
            out.print("Select a human(h) or computer(c) password maker: ");
            char maker = input.next().charAt(0);

            // ask the password breaker type
            out.print("Select a human(h) or computer(c) password breaker: ");
            char breaker = input.next().charAt(0);

            // display possible passwords to the console
            out.println("Secret Passwords: ");
            for (Password password : passwordListOfLength) {
                out.println(password);
            }

            // conditions for password maker
            if(maker == 'c') {
                passwordMaker = new RandomPasswordMaker();
                secretPassword = passwordMaker.selectPassword(passwordListOfLength);
                out.println("Secret Password: " + secretPassword);
            } else if(maker == 'h') {
                    int i = 0;
                    boolean passwordExists = false;
                    Iterator<Password> tempItr = null;
                    // keep asking for the user input if the input does not match one in the bank of possible passwords
                    while(i < passwordListOfLength.size() && !passwordExists) {
                        tempItr = passwordListOfLength.iterator();
                        out.print("Please enter one of the passwords: ");
                        secretPassword = new Password(input.next().toLowerCase());
                        out.println("secret password: " + secretPassword);

                        // run through passwordListOfLength if the password exists anywhere in the list
                        while(tempItr.hasNext() && !passwordExists) {
                            Password temp = tempItr.next();
                            if (temp.equals(secretPassword))
                                passwordExists = true;
                        }
                        if(!passwordExists)
                            out.println("Invalid Password! Type again!");
                    }
            } else {
                errorMsg = "invalidMakerType!";
                throw new IOException(errorMsg);
            }


            // conditions for password breaker
            if(breaker == 'c') {
                passwordBreaker = new MinimaxBreaker(passwordListOfLength);
                do {
                    int numOfPossiblePass = passwordBreaker.possiblePasswordCount();
                    out.println("Possible passwords: " + numOfPossiblePass);

                    Password AIGuess = passwordBreaker.nextGuess();
                    out.println("Breaker's guess(" + attempt + "): " + AIGuess);
                    similarity = secretPassword.similarity(AIGuess);
                    out.println("similarity: " + similarity);
                    passwordBreaker.guessResults(AIGuess, similarity);
                    attempt++;
                } while (passwordLength != similarity);
            } else if(breaker == 'h') {
                boolean passwordMatched = false;
                passwordBreaker = new ConsolePasswordBreaker(new Scanner(System.in), new PrintStream(out));
                do {
                    Password userGuess = passwordBreaker.nextGuess();
                    out.println("Similarity: " + secretPassword.similarity(userGuess));
                    if(secretPassword.similarity(userGuess) == passwordLength)
                        passwordMatched = true;
                } while(!passwordMatched);
            } else {
                errorMsg = "invalidBreakerType!";
                throw new IOException(errorMsg);
            }
            out.println("Game over!");


        } catch (IOException e) {
            throw new IOException(errorMsg);
        }
    }
}