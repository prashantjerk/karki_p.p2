//package test;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import password.EliminationBreaker;
//import password.MinimaxBreaker;
//import password.Password;
//import password.PasswordBank;
//
///**
// * This class is designed to help you test whether your code will compile in my JUnit tests.
// * Be sure that you can compile this class, unmodified.
// * If your code does not compile with this class, I will not be able to test your code.
// * If your code does compile with this class, odds are good it will compile in my JUnit tests.
// * Your code compiling with this class is not a sufficient level of testing.
// * @author Joe Meehean
// *
// */
//public class P2CompilationStub {
//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//
//		/**
//		 * Password
//		 */
//		Password secretPassword = new Password("cheese");
//		Password other = new Password("other");
//		secretPassword.toString();
//		secretPassword.length();
//		int sim = secretPassword.similarity(other);
//		int comp = secretPassword.compareTo(other);
//		boolean eq = secretPassword.equals(other);
//
//		/**
//		 * Password Bank
//		 *
//		 */
//		Password[] passwords = new Password[]{new Password("cheese")};
//		Collection<Password> passwordCollection = Arrays.asList(passwords);
//		PasswordBank bank = new PasswordBank(passwordCollection);
//		Collection<Password> reducedPasswordCollection = bank.passwordsOfLength(secretPassword.length());
//
//		/**
//		 *  EliminationBreaker
//		 */
//		// make a breaker
//		EliminationBreaker eBreaker = new EliminationBreaker(passwordCollection);
//		int count = eBreaker.possiblePasswordCount();
//		Password guess = eBreaker.nextGuess();
//		eBreaker.guessResults(guess, guess.length());
//
//		/**
//		 * Minimax Breaker
//		 */
//		MinimaxBreaker mBreaker = new MinimaxBreaker(passwordCollection);
//		count = mBreaker.possiblePasswordCount();
//		guess = mBreaker.nextGuess();
//		mBreaker.guessResults(guess, guess.length());
//
//	}
//}
