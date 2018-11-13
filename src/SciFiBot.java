
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Loris Jautakas
 * @version November 2018
 */
public class SciFiBot
{
	String[] positiveResponses = {"yes", " yeah ", "ok", "okay", "alright", "affirmative", "o.k.", "o.k", "fine"};
	String[] negativeResponses = {"no", "nope", "no way", "not a chance", "nah", "i decline", "negative"};
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	// int emotion = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		String name = "";
		int luck = 0;
		int debt = 0;
		int money = 0;
		int quest = (int)(randomNumber(0, requests.length));
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());
		System.out.println (askName());
		statement = in.nextLine();
		name = statement;
		System.out.println("Nice to meet you " + name + "!");
		System.out.println("It is the year 2100. A man walks up to you and says:");
		System.out.println("My ship broke! " + requests[quest]);
		statement = in.nextLine();
		if (isPositive(statement))
		{
			System.out.println("Thanks!");
			String goal = goals[quest];
			luck++;
		}
		if (isNegative(statement))
		{
			System.out.println(convince(1) + "!");
			luck--;
			statement = in.nextLine();
			if (isPositive(statement))
			{
				System.out.println("Thanks!");
				String goal = goals[quest];
			}
			if (isNegative(statement))
			{
				System.out.println("What if " +convince(2) + "?");
				statement = in.nextLine();
				if (isPositive(statement))
				{
					System.out.println("Thanks!");
				}
			}

		}
	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	private String getGreeting()
	{
		return "Welcome to sci-fiBot, the number 1 science fiction chat bot!";
	}
	private String askName ()
	{
		return "What is your name?";
	}
	


	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}

	private String convince (int i)
	{
		String [] offers = {"100", "150", "200", "300"};
		return "I'll give you " + offers [i] + " credits";
	}
	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private boolean isPositive(String statement)
	{
		for (int i = 0; i < positiveResponses.length; i++) {
			if (findKeyword(statement, positiveResponses[i]) != -1)
			{
				return true;
			}
		}
		return false;
	}
	private boolean isNegative(String statement)
	{
		for (int i = 0; i < negativeResponses.length; i++) {
			if (findKeyword(statement, negativeResponses[i]) != -1)
			{
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	private int randomNumber(int lowerlim, int upperlim)
	{
		return (int)(Math.random()*(upperlim-lowerlim)+lowerlim);
	}
	
	private String [] requests =
			{
					"Can you get me a hypercube?",
					"Can you find me an intergalactic space capsule?",
					"Can you find me a hyperdrive?"
			};
	private String [] goals =
			{
					"hypercube",
					"intergalactic space capsule?",
					"hyperdrive"
			};



}
