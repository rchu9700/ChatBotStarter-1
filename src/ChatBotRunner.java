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
		MysteryBot mysteryBot = new MysteryBot();
        BotBob chatbotBob = new BotBob();
        SciFiBot sciFiBot = new SciFiBot();



        Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the chatbot, nice to meet you.");
		String statement = in.nextLine();


		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1



			mysteryBot.chatLoop(statement);


			statement = in.nextLine();


		}
	}
}
