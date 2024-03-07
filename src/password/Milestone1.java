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
import java.util.Iterator;
import java.util.Scanner;

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
            file = new File("src\\resources\\password.bank.txt");
            Scanner fileInput = new Scanner(file, StandardCharsets.UTF_8);
            Collection<Password> passwordCollection = new ArrayList<>();

            // add passwords from file to the collection
            while (fileInput.hasNextLine()) {
                Password password = new Password(fileInput.next());
                passwordCollection.add(password);
            }
            fileInput.close();

            // Random Password Maker
            RandomPasswordMaker randomPasswordMaker = new RandomPasswordMaker();
            Password randomPassword = randomPasswordMaker.selectPassword(passwordCollection);

            // ask length from user and filter out the password of that length
            System.out.print("Select password length: ");
            PasswordBank passwordBank = new PasswordBank(passwordCollection);
            Collection<Password> passwordListOfLength = passwordBank.passwordOfLength(input.nextInt());
            Iterator<Password> itr = passwordListOfLength.iterator();
            while(itr.hasNext()) {
                // DO SOMETHING
            }
        } catch (IOException e) {
            throw new IOException(String.format("Could not open %s", file));
        }

//        RandomPasswordMaker randomPassword = new RandomPasswordMaker();
//        System.out.println(randomPassword.selectPassword(passwordCollection));


//        // display the passwords in the file to the console
//        for (Password password : passwordCollection) {
//            System.out.println(password);
//        }
    }
}
