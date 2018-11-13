
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
	// Initial variables and such
	String [] requests =
			{
					"Can you get me a hypercube?",
					"Can you find me a galactic space capsule?",
					"Can you find me a hyperdrive?"
			};
	String [] goals =
			{
					"hypercube",
					"galactic space capsule",
					"hyperdrive"
			};
	int [] offers = {100, 150, 200, 300};
	String[] positiveResponses = {"yes", " yeah ", "ok", "okay", "alright", "affirmative", "o.k.", "o.k", "fine", "sure"};
	String[] negativeResponses = {"no", "nope", "no way", "not a chance", "nah", "i decline", "negative", "not"};
	int rep = 0;
	int debt = 0;
	int money = 0;
	boolean completed = false;
	int quest = (int)(randomNumber(0, requests.length));
	String name = "";
	String goal = "";


	/**
	 * Runs the conversation for this particular chatbot. The switching has already been implemented in the main method.
	 */
	public void chatLoop()
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());
		System.out.println (askName());
		String statement = in.nextLine();
		name = statement;
		System.out.println("Nice to meet you " + name + "!");
		System.out.println("It is the year 2100. A man walks up to you and says:");
		System.out.println("My ship broke! " + requests[quest]);
		statement = in.nextLine();
		if (isPositive(statement))
		{
			System.out.println("Thanks!");
			debt = offers[0];
		}
		else if (isNegative(statement))
		{
			System.out.println(convince(1) + "!");
			statement = in.nextLine();
			if (isPositive(statement))
			{
				System.out.println("Thanks!");
				debt = offers[1];
			}
			else if (isNegative(statement))
			{
				System.out.println("What if " +convince(2) + "?");
				statement = in.nextLine();
				if (isPositive(statement))
				{
					System.out.println("Thanks!");
					debt = offers[2];
				}
				else if (hasKeyword(statement,"I don't want to" ))
				{
					System.out.println(transformDontWantTo(statement));

				}
				else if (isNegative(statement))
				{
					System.out.println("Well you're quite mean!");
				}
				else
				{
					exit();
				}
			}
			else
			{
				exit();
			}
		}
		else
		{
			exit();
		}
		if(debt >0)
		{
			rep++;
			goal = goals [quest];
		}
		System.out.println("So you get to a junction, and you can either go left or right. Which way do you want to go?");
		statement = in.nextLine();
		if (hasKeyword(statement, "right"))
		{
			System.out.println("All you see is a empty path that leads to nowhere");
			System.out.println("Would you like to continue on?");
			statement = in.nextLine();
			if (isPositive(statement))
			{
				System.out.println("You keep going for miles and miles, untill you faint from dehydration.");
				System.out.println("You wake up in 2007, and the story ends here.");
				System.exit(0);
			}
			System.out.println("So you go back to the left way.");
			left();
		}
		else if (hasKeyword(statement, "left"))
		{
			left();
		}
		else
		{
			exit();
		}
		if (completed)
		{
			System.out.println("So you go back and on your way, you see another man, and he asks:");
			if (!goal.equals(""))
			{
				System.out.println("Ay you! You got a " + goal + " that you're willing to sell?");
				while(true)
				{
					statement = in.nextLine();
					if (isPositive(statement))
					{
						System.out.println("Oh goodness me, he appears to have stolen it from you! How unfortunate!");
						rep --;
						debt = 0;
						break;
					}
					else if (isNegative(statement))
					{
						System.out.println("The man says:");
						System.out.println("Ok, I'll be on my way then!");
						rep ++;
						break;
					}
						else System.out.println("I don't understand");
				}
			}
			else
			{
				System.out.println("Would you like to help me with my homework?");
				statement =in.nextLine();
				if (isPositive(statement))
				{
					System.out.println("Ok, its just one math question.");
					System.out.println("What is 2 times 2?");
					statement = in.nextLine();
					if (hasKeyword(statement, "4"))
					{
						rep ++;
						debt = debt +10;
						System.out.println("Wow, thanks! Here is 10 dollars for your trouble!");
					}
					else
					{
						System.out.println("Really?");
						rep--;
					}
				}
				else if (isNegative(statement))
				{
					System.out.println("You are very very mean!");
					rep--;
				}
			}
		}


		System.out.println("So you go back to original man and he asks you:");
		if (goal.equals(""))
		{
			System.out.println("Why didn't you help me?");
			statement = in.nextLine();
			if (hasKeyword(statement,"could"))
			{
				System.out.println("It's Okay, I understand.");
				rep++;
			}
			else
			{
				System.out.println("That's no excuse!");
				rep--;
			}
		}
		else
		{
			System.out.println("So do you have the " +goal +" that I asked for?");
			statement = in.nextLine();
			if (debt == 0)
			{
				if (isPositive(statement))
				{
					System.out.println("The man, frustrated by your lies banished you to 2007, and the story ends there.");
					System.exit(0);
				}
				if (isNegative(statement))
				{
					System.out.println("Well at least you're honest about it.");
					rep++;
				}
				else
				{
					System.out.println("I don't understand, so i'll assume that you tried");
					rep++;
				}
			}
			else
			{
				System.out.println("Thank you so much!");
			}
		}
		System.out.println("The story concludes here.");
		System.out.println("rep" + rep);
		System.out.println("debt" + debt);
		System.out.println("Congratulations! Your final score is "+ (debt+rep) +"! Try again next time!");
	}

	/**
	 *  The sequence of events that take place if you choose the left path.
	 */
	private void left()
	{
		String statement;
		Scanner in = new Scanner (System.in);
		System.out.println("Oh no! You've stumbled into a maze, and a very dangerous one at that!");
		System.out.println("Would you like to go back?");
		statement= in.nextLine();
		if (isNegative(statement))
		{
			System.out.println("Ok on we go!");
		}
		else
		{
			System.out.println("Well it's too bad the bridge back just broke!");
			System.out.println("So I guess we have to continue on!");
		}
		if (maze())
		{
			if (!goal.equals(""))
			{
				System.out.println("Wow you found a "+ goal +"! Nice going " + name +"!");
				completed = true;
			}
			else
			{
				System.out.println("You found nothing, how unfortunate!");
				completed = true;
			}
		}
		else
		{
			System.out.println("Unfortunately you fall down a hole and get time traveled back to 2007, and this story ends there!");
			System.exit(0);
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



	private void exit()
	{
		System.out.println("I cannot understand your last comment, and so I must be on my way.");
		debt = 0;
		rep = 0;
		System.exit(0);
	}

	/**
	 * Take a statement with "I dont want to <something>." and transform it into
	 * "Why don't you want to <something>?"
	 * @param statement the user statement, assumed to contain "I don't want to"
	 * @return the transformed statement
	 */
	private String transformDontWantTo(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I don't want to", 0);
		String restOfStatement = statement.substring(psn + 15).trim();
		return "Why don't you want to " + restOfStatement + "?";
	}

	/**
	 * Gives the user a maze to solve.
	 * @return if the user completed the maze successfully.
	 */
	private boolean maze()
	{
		Scanner in = new Scanner (System.in);
		String path[] = {"left", "right"};
		for(int i = 0; i < path.length; i++) {
			System.out.println("Which way do you go? (right or left): ");
			String input = in.nextLine();
			if (!input.equals(path[i])) {
				System.out.println("You got lost!");
				return false;
			}
		}
		System.out.println("You made it!");
		return true;
	}
	private String convince (int i)
	{
		return "I'll give you " + offers [i] + " credits";
	}


	/**
	 * Uses findKeyword to find if a statment is positive. It references the string array positiveResponses.
	 * @param statement The statement to be checked
	 * @return a boolean indicating if the statement is a positive statement.
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
	/**
	 * Uses findKeyword to find if a statment is negative. It references the string array negativeResponses.
	 * @param statement The statement to be checked
	 * @return a boolean indicating if the statement is a negative statement.
	 */
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
	 * Uses find keyword to check if a String statement contains String goal.
	 * @param statement The string to search
	 * @param goal The string to search for
	 * @return
	 */

	private boolean hasKeyword (String statement, String goal)
	{
		statement = statement.toLowerCase();
		goal = goal.toLowerCase();
		if (findKeyword(statement, goal) != -1)
		{
			return true;
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

	/**
	 * Makes a random number between the two parameters.
	 * @param lowerlim The bottom limit
	 * @param upperlim The top limit
	 * @return The random number between.
	 */
	private int randomNumber(int lowerlim, int upperlim)
	{
		return (int)(Math.random()*(upperlim-lowerlim)+lowerlim);
	}
	




}
