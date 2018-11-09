import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Ricky Chu
 * @version September 2018
 */
public class MysteryBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println(getName());

		while (!statement.equals("Bye"))
		{


			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}
	/**
	 * Get a default greeting
	 * @return a greeting
	 */
	public String getName()
	{
		return "Welcome to the mysterious story of who murdered Maria Nathan. First off, what might be your name?";
	}

	public void getBackgroundInfo()
	{
		System.out.println( ", please for my sake, help me out. My daughter has been murdered and at this point, I don't even know what to do anymore. She was only 18 years old and went to Stanford University. ");
		System.out.println("It wasn't long before my precious little baby was murdered by the hands of someone very dangerous. She went clubbing late one night and never came back home. ");
		System.out.println("It hurts my heart that she just had to leave me at such a young age. That night, I filed a police report and it was reported that she was murdered by her drink being poisoned ");
		System.out.println("while she was with her friend Annissa Labrovsky. Ultimately, it came down to 3 suspects. The first person was a man by the name Matthew Lewis who was a close friend of Maria. ");
		System.out.println("His alibi was that he was in the bathroom during the time Maria was drinking and would never do something like that to her. The second suspect is a woman by the name of Jane Moore. ");
		System.out.println("Jane Moore was the bartender of that night and served the drinks to the two girls. However, she claims that she didn't have any cyanide or poison on her to toxicate the drinks. The last suspect ");
		System.out.println("would of course then have to be, Maria's friend Annissa. As shocking as this may sound, the police concluded that Annissa must be included as a suspect even though she was with Maria ");
		System.out.println("the whole time making it hard to poison her drink. However, I am sure that one of the three suspects is the murderer because they were the only ones who knew Maria. ");
		System.out.println("Can you help me crack this case? Start by saying \"I think it is\" and then the name of the murderer. Use context clues and the details from what I just gave you to make an educated guess.");
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";

	if (statement.length() == 0)
		{
			response = "Say something, please.";
		}


	if (findKeyword(statement, "no") >= 0)
	{
		response = "Why so negative?";
		emotion--;
	}

	else if (findKeyword(statement, "levin") >= 0)
	{
		response = "More like LevinTheDream, amiright?";
		emotion++;
	}
	else if (findKeyword(statement, "folwell") >= 0)
	{
		response = "Watch your backpacks, Mr. Folwell doesn't fall well.";
		emotion++;
	}
	else if (findKeyword(statement, "goldman") >= 0)
	{
		response = "Go for the gold, man.";
		emotion++;
	}

	// Response transforming I want to statement
	else if (findKeyword(statement, "I think it is") >= 0)
	{
		response = transformIThinkItIsStatement(statement);
	}
		return response;
	}

	/**
	 * Take a statement with "I think it is <something>." and transform it into
	 * "Why do you think it is <something>?"
	 * @param statement the user statement, assumed to contain "I think it is "
	 * @return the transformed statement
	 */
	private String transformIThinkItIsStatement(String statement)
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
		int psn = findKeyword (statement, "I think it is", 0);
		String restOfStatement = statement.substring(psn + 13).trim();
		return "Why do you think it is " + restOfStatement + "?";
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
	private int findKeyword(String statement, String goal,
							int startPos)
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
}

