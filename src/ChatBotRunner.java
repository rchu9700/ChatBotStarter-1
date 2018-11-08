import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Ricky Chu, Wei Chen, Loris Jautakas
 * @version September 2018
 */
public class ChatBotRunner {

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		String[] positiveResponses = {"yes", " yeah ", "ok", "okay", "alright"};
		String[] negativeResponses = {"No", "Nope", "No way", "Not a chance"};


        Scanner in = new Scanner (System.in);
		String bot = "";
        System.out.println("Welcome to the storyBot, nice to meet you.");
		System.out.println("Would you like a mystery story?");
		String input = "";
		Boolean agree = false;
		while (bot.equals(""))
		{
			input = in.nextLine().toLowerCase();
			for (int i = 0; i < positiveResponses.length; i++)
			{
				if (positiveResponses[i].equals(input))
				{
					agree = true;
				}
			}
			if (agree)
			{
				bot = "mystery";
				break;
			}
			System.out.println("Would you like a fantasy story then?");
			input = in.nextLine().toLowerCase();
			for (int i = 0; i < positiveResponses.length; i++)
			{
				if (positiveResponses[i].equals(input))
				{
					agree = true;
				}
			}
			if (agree)
			{
				bot = "fantasy";
				break;
			}
			//
			System.out.println("Would you like a scifi story then?");
			input = in.nextLine().toLowerCase();
			for (int i = 0; i < positiveResponses.length; i++)
			{
				if (positiveResponses[i].equals(input))
				{
					agree = true;
				}
			}
			if (agree)
			{
				bot = "scifi";
				break;
			}
		}
		loop(bot);

	}
	private static void loop(String bot)
	{
		MysteryBot mysteryBot = new MysteryBot();
		FantasyBot fantasyBot = new FantasyBot(0,new String[5],0,false);
		SciFiBot scifiBot = new SciFiBot();
		Scanner in = new Scanner (System.in);
		String statement = "";
		if (bot == "scifi")
		{
				scifiBot.chatLoop(statement);
		}
		if (bot == "mystery")
		{
				mysteryBot.chatLoop(statement);
		}
		if (bot == "fantasy")
		{
				fantasyBot.chatLoop(statement);
		}
	}
	private static boolean hasKeyWord (String statement, String goal)
	{
		goal = goal.toLowerCase();
		statement = statement.toLowerCase();
		if (findKeyword(statement,goal,0) != -1)
		{
			return true;
		}
		return false;
	}
	private static int findKeyword(String statement, String goal, int startPos)
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
}
