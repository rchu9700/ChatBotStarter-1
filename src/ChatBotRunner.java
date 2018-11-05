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
		String[] positiveResponses = {" Yes ", " Yeah ", ""};
		String[] Letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w,", "x", "y", "z" };
		MysteryBot mysteryBot = new MysteryBot();
        FantasyBot fantasyBot = new FantasyBot();
        SciFiBot sciFiBot = new SciFiBot();

        Scanner in = new Scanner (System.in);
		String bot = "";
        System.out.println("Welcome to the chatbot, nice to meet you.");
		System.out.println("Would you like a mystery story?");
		if (hasKeyWord(in.nextLine(), "Yes"))
		{
			bot = "SciFi";
		}

		System.out.println("Would you like a fantasy story then?");
		if (hasKeyWord(in.nextLine(), "Yes"))
		{

		}

	}
	private static boolean hasKeyWord (String input, String keyWord)
	{
		input = input.toLowerCase();
		keyWord = keyWord.toLowerCase();
		if (input.indexOf(keyWord) == -1)
		{
			return false;
		}

		return true;
	}
	private static String charBefore(String input, String keyWord)
	{
		input = input.toLowerCase();
		keyWord = keyWord.toLowerCase();
		return input.substring(input.indexOf(keyWord)-1,input.indexOf(keyWord));
	}
	private static String charAfter(String input, String keyWord)
	{
		input = input.toLowerCase();
		keyWord = keyWord.toLowerCase();
		return input.substring(input.indexOf(keyWord)+keyWord.length(),input.indexOf(keyWord)+keyWord.length()+1);
	}
}
