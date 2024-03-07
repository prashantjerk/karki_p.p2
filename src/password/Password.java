/*
 *   @author: Prashant Karki
 * CS241
 * Spring 2024
 */

package password;

import static java.lang.System.exit;

public class Password implements Comparable<Password>{
    private final String password;
    public Password(String word) {
        password = word;
    }

    // Compares the String representation of these Passwords.
    public int compareTo(Password other) {
            return this.password.compareTo(other.password);
    }

    // Returns whether these two passwords are equal.
    // using the recipe in 'Core Java'
    public boolean equals(Object otherObject) {
        // if this and otherObject are in the same address, they are equal
        if(this == otherObject) return true;

        // if otherObject is null, that's not worth comparing
        if(otherObject == null) return false;

        // if the classes do not match, they are not equal as well
        if(getClass() != otherObject.getClass()) return false;

        // after every previous condition, we finally whether the chars match
        return this.password.equals(((Password) otherObject).password);
    }

    // Returns the length of the password.
    public int length() {
        return this.password.length();
    }

    // Computes the similarity score between this and other.
    public int similarity(Password other) {
        String thisPass = this.password;
        String otherPass = other.password;

        // go through both passwords if the lengths of the passwords are equal AND until we reach the end of passwords
        int similarityScore = 0;
         for(int i = 0; i < thisPass.length() && thisPass.length() == otherPass.length(); i++) {
            if(thisPass.charAt(i) == otherPass.charAt(i))
                similarityScore++;
        }
        return similarityScore;
    }

    // Returns a lower-case string representation of this password
    public String toString() {
        return this.password.toLowerCase();
    }
}
