import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Ricky Chu
 * @version November 2018
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
		Scanner out = new Scanner (System.in);
		System.out.println(getName());
		String name = out.nextLine();
		System.out.println(getBackgroundInfo(name));

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

	/**
	 * Get background information on the case
	 * @param name the name of the user
	 * @return the case information
	 */
	public String getBackgroundInfo(String name)
	{
		return name + ", please for my sake, help me out. My daughter has been murdered and at this point, I don't even know what to do anymore. It wasn't long before my precious little baby was murdered by the" + "\n" + "hands of someone very dangerous. She was only 18 years old and went to Stanford University. It hurts my heart that she just had to leave me at such a young age. She went clubbing late one night" + "\n" + "and never came back home. That night, I filed a police report and it was reported that she was murdered by her drink being poisoned while she was with her friend Annissa Labrovsky. Ultimately," + "\n" + "it came down to 3 suspects. The first suspect was a man by the name Matthew Lewis who was a close friend of Maria. His alibi was that he was in the bathroom during the time Maria was drinking and" + "\n" + "would never do something like that to her. The second suspect is a woman by the name of Jane Moore. Jane Moore was the bartender of that night and served the drinks to the two girls. However," + "\n" + "she claims that she didn't have any cyanide or poison on her to toxicate the drinks. The last suspect would of course then have to be, Maria's friend Annissa Labrovsky. As shocking as this may sound," + "\n" + "the police concluded that Annissa must be included as a suspect even though she was with Maria the whole time making it hard to poison her drink. However, I am sure that one of the three" + "\n" + "suspects is the murderer because they were the only ones who knew Maria. Can you help me crack this case? Start by saying \"I think it is\" and then the name of the murderer. Use context clues" + "\n" + "and the details from what I just gave you to make an educated guess.";
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

	// Gives advice to the user by presenting additional case details
	if (findKeyword(statement, "Annissa Labrovsky") >= 0)
	{
		response = "Hello. My name is Detective Holmes and I will assist you on this case. I have the answer to this case and I see that you need some guidance. The investigation team" + "\n" + "has found that rather than the actual drink being contaminated with poison, the ice cubes had poison in it. This was detected through the ice cubes melting causing" + "\n" + "the water substance acting unusual. Do you have a better idea now on who may have killed Maria?";
		emotion--;
	}

	// Gives advice to the user by presenting additional case details
	else if (findKeyword(statement, "Matthew Lewis") >= 0)
	{
		response = "Hello. My name is Detective Holmes and I will assist you on this case. I have the answer to this case and I see that you are struggling to understand what may be going on." + "\n" + "Let me help you. The investigation team has found that rather than the actual drink being contaminated with poison, the ice cubes had poison in it. This was detected through" + "\n" + "the ice cubes melting causing the water substance acting unusual. Do you have a better idea now on who may have killed Maria?";
		emotion--;
	}

	// Gives positive feedback to the user by telling them that they are so close to finishing the case and just needs to explain how it happened
	else if (findKeyword(statement, "Jane Moore") >= 0)
	{
		response = "Hello. My name is Detective Holmes and I can see that you are getting one step closer in cracking this case. However, I don't think you fully understand how Maria Nathan" + "\n" + "was killed. Let me help you. Knowing that the ice cubes were poisoned rather than the drink itself is valuable to cracking this case. In addition, just because Jane said" + "\n" + "that she didn't have any poison or cyanide doesn't mean that she couldn't have gotten rid of it beforehand. Now, how do you think Maria Nathan was killed?";
		emotion++;
	}

	// Helps the user out because they understand the information but need to know how to get to the answer
	else if (findKeyword(statement, "yes") >= 0)
	{
		response = "First we need to make things more clear by narrowing down the three suspects to one suspect. Let's start with Matthew Lewis. From the evidence and details provided," + "\n" + "it wouldn't seem reasonable for a man who was in the bathroom during the actual murder to be the murderer. Moving on, Annissa Labrovsky is Maria's best friend and was the" + "\n" + "one who told Maria to come out for a drink. It wouldn't make sense for Annissa to kill Maria unless she had a motive for doing so but according to the interview and multiple" + "\n" + "sources, there have been no evidence. Annissa herself is suffering from this loss as well. That could only leave Jane Moore as the suspect. But how could she have done it?" + "\n" + "Knowing that the ice cubes were poisoned rather than the drink itself is valuable to cracking this case. In addition, just because Jane said that she didn't have any poison" + "\n" + "or cyanide doesn't mean that she couldn't have gotten rid of it beforehand. Now, how do you think Maria Nathan was killed?";
		emotion++;
	}

	// Where the case is closed and the code will end, as well as providing links to other mystery riddles
	else if (findKeyword(statement, "before") >= 0)
	{
		response = "Exactly! The key word here is \"before\" because although you may not have fully gotten the correct deduction, I know that you can interpret the evidence and background information." + "\n" + "What really happened, is that Jane Moore saw Maria walk into the bar and quickly set a cup of ice aside and put drops of cyanide into the ice cubes. She disposed the rest of the" + "\n" + "cyanide down the drain and wiped her fingers with a hand wipe which was later on detected. It turns out that Jane's motive for killing Maria was that back then, when the two girls" + "\n" + "went to school together, Maria took the lead role of a play from Jane. Anyways great work detective! For more information on mystery riddles, check out these links:" + "\n" + "\"https://www.rd.com/funny-stuff/detective-riddles/\"" + "\n" + "\"https://solveordie.com/mystery-riddles/\"" + "\n" + "\"https://brightside.me/wonder-quizzes/7-mystery-crime-riddles-only-a-true-detective-can-solve-374160/\"" + "\n" + "\"https://9gag.com/gag/arRw6Wy/answers-11-murder-mystery-riddles-can-you-solve-them-all\"";
	}

	// Response transforming I want to statement
	else if (findKeyword(statement, "I think it is") >= 0)
	{
		response = transformIThinkItIsStatement(statement);
	}
	else {
		response = getRandomResponse();
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
		return "Why do you think it is " + restOfStatement + "? Address the question by including the FULL NAME of the person you suspect in your answer.";
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
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		Random r = new Random ();
		if (emotion == 0)
		{
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{
			return randomNegativeResponses [r.nextInt(randomNegativeResponses.length)];
		}
		return randomPositiveResponses [r.nextInt(randomPositiveResponses.length)];
	}

	private String [] randomNeutralResponses = {"Please say someone's name, it's just a guess!", "Use the format \"I think it is\" in your answer!", "Don't worry if you get it wrong just follow the format!"};
	private String [] randomNegativeResponses = {"How don't you understand what I'm saying?", "It's a yes or no question!", "Gosh you're a tough one aren't you?"};
	private String [] randomPositiveResponses = {"Hmm..., think more deeply.", "You're getting there but try again!", "Keep guessing!"};

}

