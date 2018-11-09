import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Wei Chen
 * @version September 2018
 */
public class FantasyBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;


	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */

	private int rIndex;
	private String[] responses;
	private int areYouSure;
	private boolean nextQuestion;

	public FantasyBot(int rIndex, String[] responses, int areYouSure, boolean nextQuestion){
		this.rIndex = rIndex;
		this.responses = responses;
		this.areYouSure = areYouSure;
		this.nextQuestion = nextQuestion;
	}


	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());

		while (!statement.equals("Bye"))
		{
			statement = in.nextLine();

			if (findKeyword(statement, "yes") >= 0){
				areYouSure = 1;
			}
			else if(findKeyword(statement, "no") >= 0){
				areYouSure = 2;
			}


			if(nextQuestion){
			rIndex++;
			nextQuestion = false;
			}

			//getResponse handles the user reply
			if (emotion > -5) {
				System.out.println(getResponse(statement));
			}
			else{
				System.out.println("BobBot got frustrated and killed you, the end...");
				statement = "Bye";
			}
			//System.out.println(responses[0]);


		}

	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, I'm BobBot." + "\n" + "You have awaken in the world ruled by the demon lord, you must defeat the demon lord to return home!" + "\n" + "So, what is your divine name?";
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
			emotion--;
			response = "Hey, don't ignore your BobBot, say something!";
		}

		else if (rIndex == 0){
			if (areYouSure == 0 ) {
				response = "Is your name " + statement + "?";
				emotion--;
			}
			else if(areYouSure == 1){
				response = "Ok " + responses[0] + ", there are a few weapons on the ground, pick up a weapon and get on going";
				emotion = emotion + 2;
			}
			else if(areYouSure == 2){
				response = "Then, what is your name?";
				emotion--;
				areYouSure = 0;
			}
		}

		else if (rIndex == 1){
			if(findKeyword(statement, "Sword") >=0){
				response = "Is this Sword your weapon of choice?";
				statement = "Sword";
			}
			else if(findKeyword(statement, "Lolipop") >=0){
				response = "A Lolipop, that's a unique one!";
				statement = "Lolipop";
			}
			else if(findKeyword(statement, "Poop On a Stick") >=0){
				response = "A Poop on a Stick, you have good eyes";
				statement = "Poop on a Stick";
			}
			else if(findKeyword(statement, "Wand") >=0){
				response = "This Wand will bring you magic";
				statement = "Wand";
			}
			else if(findKeyword(statement, "Gun") >=0){
				response = "You better know how to aim with this Gun";
				statement = "Gun";

			}
			else{
				response = getRandomResponse();

			}
		}


		/*else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
			//emotion--;
		}
		
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream amiright?";
			//emotion++;
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		else
		{
			response = getRandomResponse();
		}*/

		responses[rIndex] = statement;
		areYouSure = 0;
		return response;
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
	private String transformIYouStatement(String statement)
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
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
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
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (rIndex == 1){
			return randomWeaponResponses[r.nextInt(randomWeaponResponses.length)];
		}
		/*if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
		*/
		return randomWeaponResponses[r.nextInt(randomWeaponResponses.length)];
	}

	private String[] randomWeaponResponses = {"I don't see any of that here, how about a sword?",
			"Do you really think that would be on the ground? Just pick up that Poop on a Stick!",
			"Open your eyes, there are only a sword, a gun, a wand, a Lolipop, and a Poop on a Stick for you to choose from!",
			"We don't have time for this, just get that Lolipop!"
			};

	/*private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"It's all boolean to me.",
			"So, would you like to go for a walk?",
			"Could you say that again?"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	*/
}
