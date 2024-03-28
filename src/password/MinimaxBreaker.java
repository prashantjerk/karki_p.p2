/*
 * @author: Prashant Karki
 * CS241
 * Spring 2024
 */

package password;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class MinimaxBreaker implements PasswordBreaker{
    Collection<Password> passwords = new ArrayList<>();
    Collection<Password> afterElimination = new ArrayList<>();
    Iterator<Password> allItr;
    Iterator<Password> itrForEliminated;
    private int count;
    boolean firstCall = true;

    // Creates a minimax breaker that uses the given collection of passwords(of length selected by user)
    // as the collection of all the potential passwords.
    public MinimaxBreaker(java.util.Collection<Password> passwords){
        this.passwords = passwords;
        count = passwords.size();
        Collections.sort((ArrayList<Password>) passwords);
        afterElimination = passwords;       // initialize afterElimination same as passwords
    }

    // this is used to eliminate the passwords that no longer qualifies for the list
    @Override
    public void guessResults(Password guess, int similarity) {
        count = 0;
        itrForEliminated = afterElimination.iterator();
        Collection<Password> tempPassCollection = new ArrayList<>();
        tempPassCollection.clear();
        while (itrForEliminated.hasNext()) {
            Password nextPass = itrForEliminated.next();
            if (guess.similarity(nextPass) == similarity) {
                tempPassCollection.add(nextPass);
                count++;
            }
        }
        afterElimination = tempPassCollection;
    }


    // Returns a password that will eliminate the most possible passwords in the worst case.
    // in case of tie with the miniscore, chose the one that comes first alphabetically
    @Override
    public Password nextGuess() {
        // set iterators to the start
        allItr = passwords.iterator();
        itrForEliminated = afterElimination.iterator();
        Password nextPass = null;
        Password passInEliminated = null;

        Password nextGuess = null;
        if(afterElimination.size() == 1) {   // if the single password is left in the list, just return the password
            itrForEliminated = afterElimination.iterator();
            nextGuess = itrForEliminated.next();
            firstCall = false;
        } else if(firstCall) {              // if it is the first guess, return alphabetically first password
            nextGuess = allItr.next();
            firstCall = false;
        } else {
            ArrayList<Integer> arrayOfLowest = new ArrayList<>();
            int storeCount = 0;
            int eliminationCount;

            // runs through all the password and the possible similarity score and find number of elimination for each
            while(allItr.hasNext()) {
                nextPass = allItr.next();
                for (int i = 0; i <= nextPass.length(); i++) {
                    eliminationCount = 0;
                    itrForEliminated = afterElimination.iterator();
                    while (itrForEliminated.hasNext()) {
                        passInEliminated = itrForEliminated.next();
                        if (nextPass.similarity(passInEliminated) != i)
                            eliminationCount++;
                    }

                    // record min-score from each possible set of a password
                    if(i == 0 || storeCount > eliminationCount)
                        storeCount = eliminationCount;
                }
                arrayOfLowest.add(storeCount);
            }

            int indexToReturn = 0;
            int maxScore = arrayOfLowest.get(0);

            // finds the max score among the min scores
            for(int i = 1; i < arrayOfLowest.size(); i++) {
                if(arrayOfLowest.get(i) > maxScore) {
                    maxScore = arrayOfLowest.get(i);
                    indexToReturn = i;
                }
            }

            int i = 0;
            allItr = passwords.iterator();
            // finds the nextGuess among the passwords
            while (allItr.hasNext() && i <= indexToReturn) {
                nextGuess = allItr.next();
                i++;
            }
        }
        return nextGuess;
    }

    // Returns the total number of passwords that have not been eliminated by previous guesses.
    @Override
    public int possiblePasswordCount() {
        return count;
    }
}