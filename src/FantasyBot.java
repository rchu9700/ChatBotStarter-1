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
			areYouSure=0;
			}

			//getResponse handles the user reply
			if (emotion > -5) {
				System.out.println(getResponse(statement));
			}
			else{
				if (emotion == -100){
					System.out.println("The End");
				}
				else{
					System.out.println("BobBot got frustrated and killed you, the end...");
				}
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
		return "BobBot: Hi, I'm BobBot." + "\n" + "BobBot: You have awaken in the world ruled by the demon lord, you must defeat the demon lord to return home!" + "\n" + "BobBot: So, what is your divine name?";
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
		Boolean isRandomResponse = false;

		if (statement.length() == 0)
		{
			emotion--;
			response = "Hey, don't ignore your BobBot, say something!";
		}

		else if (rIndex == 0){
			if (areYouSure == 0 ) {
				String name = statement;
				response = "Is your name " + name + "?";
				responses[0] = name;
				emotion--;
			}
			else if(areYouSure == 1){
				response = "Ok " + responses[0] + ", there are a few weapons on the ground, pick up a weapon and get on going";
				emotion = emotion + 2;
				nextQuestion = true;
			}
			else if(areYouSure == 2){
				response = "Then, what is your name?";
				emotion--;
				areYouSure = 0;
			}
		}

		else if (rIndex == 1){
			if (areYouSure == 0) {
				String weapon = "noWeapon";
				if (findKeyword(statement, "Sword") >= 0) {
					response = "Is this Sword your weapon of choice?";
					weapon = "Sword";
				} else if (findKeyword(statement, "Lolipop") >= 0) {
					response = "A Lolipop, that's a unique one!";
					weapon = "Lolipop";
				} else if (findKeyword(statement, "Poop On a Stick") >= 0) {
					response = "A Poop on a Stick, you have good eyes";
					weapon = "Poop on a Stick";
				} else if (findKeyword(statement, "Wand") >= 0) {
					response = "This Wand will bring you magic";
					weapon = "Wand";
				} else if (findKeyword(statement, "Gun") >= 0) {
					response = "You better know how to aim with this Gun";
					weapon = "Gun";

				} else {
					if(findKeyword(statement, "weapon") >= 0 || findKeyword(statement, "weapons") >= 0){
						response = "There is a Sword, a Lolipop, a Poop on a Stick, a Wand, and a Gun";
					}
					else {
						response = getRandomResponse();
						emotion--;
					}
					isRandomResponse = true;

				}
				responses[1] = weapon;
				if (isRandomResponse == false) {
					response = response + "\n" + "BobBot: " + responses[0] + ", Are you sure you want " + weapon + "? You won't have room for any other weapons!";
				}
				isRandomResponse = false;
			}
			else if(areYouSure == 1){
				response = "You have acquired the Legendary " + responses[1] + "! Now, out of the three paths in front of you, which path do you want to take?";
				emotion = emotion +2;
				nextQuestion = true;
			}
			else if(areYouSure == 2){
				response = "Then pick your weapon already, there are only five choices, come on, pick!";
				emotion--;
				areYouSure=0;
			}


		}

		else if(rIndex == 2){

			if (areYouSure ==0){
				String path = "no where";
				if(findKeyword(statement, "middle") >= 0){
					response = "I sense a huge goblin army coming toward us from the middle path," + responses[0] + ", Shall we continue down this path?";
					path = "middle";
				}
				else if(findKeyword(statement, "left") >= 0){
					response = "The forest of mushrooms toward our left is giving off dark ominous winds. " + responses[0] + ", Do you really want to go from this route?";
					path = "left";
				}
				else if(findKeyword(statement, "right") >= 0){
					response = "The right path is covered in giant poops, from large creatures I suppose. I can fly over the poops, but you would have to swim across. " + responses[0] + ", Are you sure you want to be covered in poops?";
					path = "right";
				}
				else if(findKeyword(statement, "I want to") >=0){
					response = transformIWantToStatement(statement);
					emotion--;
				}
				else if(findKeyword(statement, "I want") >=0){
					response = transformIWantStatement(statement);
					emotion--;
				}
				else{
					if (findKeyword(statement, "path") >=0 || findKeyword(statement, "paths") >=0){
						response = "There are only three paths, middle, left, or right.";
					}
					else {
						response = getRandomResponse();
					}
				}
				responses[2] = path;
			}
			else if (areYouSure ==1){
				response = "Ok, looks like you have chosen the " + responses[2] + " path. Let's move on with caution!";
				emotion = emotion +2;
				nextQuestion = true;

				if(responses[2].equals("left")){
					response = response + "\n" + "BobBot: " + responses[0] + ", I think you have encountered the true darkness in Mushroom forest, and have lost your way. What do you do?";
				}
				else if(responses[2].equals("right")){
					response = response + "\n" + "BobBot: " + responses[0] + ", Never mind you smelling terrible, a poop monster has appeared ahead, what do you want to do with it?";
				}
				else { //middle
					response = response + "\n" + "BobBot: The goblin army is fast approaching, " + responses[0] + ", what is your counter measure?";
				}

			}
			else if (areYouSure ==2){
				response = "Non-sense! " + responses[0] + "! Make your chose now or I will end you here myself!";
				emotion= emotion - 2;
				areYouSure=0;
			}
		}

		else if(rIndex == 3){
			if(findKeyword(statement, "attack") >= 0) {
				if(responses[2].equals("left")){
					response = "Ok " + responses[0] + ", go for it!" + "\n" + "You tried to attack true dark with " + responses[1] + ", but has no effect. The darkness consumed you.";
					emotion = -100;
				}
				else if(responses[2].equals("right")){
					response = "Ok " + responses[0] + ", go for it!" + "\n" + "You tried to attack the Poop Monster with " + responses[1] + ", but has no effect. The Poop Monster used body slam and drowned you in poop.";
					emotion = -100;
				}
				else{
					response = "Ok " + responses[0] + ", go for it!" + "\n" + "You tried to attack the Goblin Army with " + responses[1] + ", but only managed to kill a few. The goblins outnumbered you and slaughtered you.";
					emotion = -100;
				}
			}
			else if(findKeyword(statement,"eat") >=0 || findKeyword(statement,"suck") >=0){
				if(responses[1].equals("Lolipop")){
					response = responses[0] +"! You all powered up!";
					if(responses[2].equals("left")){
						response = response + "\n" + "But the power up did not help" + responses [0] + "find his way, he's forever lost in the mushroom forest";
						emotion = -100;
					}
					else{
						response = response + "\n" + responses [0] + " defeated all monsters ahead and moved on towards the demon lord" + "\n" + "BobBot; Oh is that the demon lord up there!" + responses[0] + "! What do we do?";
						emotion++;
						nextQuestion = true;
					}
				}
				else{
					response = "How are you going to eat " + responses[1]+ "?";
					emotion--;
				}
			}
			else if(findKeyword(statement, "magic") >= 0) {
				if(responses[1].equals("Wand")){
					response = "Alright " + responses[0] + " use fireball!" + "\n" + "Oh no! You have no mana, thus can not use magic! You been consumed by the danger!";
					emotion= -100;
				}
				else{
					response = "How can you use magic without a wand?";
					emotion--;
				}
			}
			else if(findKeyword(statement, "fire") >= 0 || findKeyword(statement,"shoot") >=0) {
				if(responses[1].equals("Gun")){
					response = "Alright " + responses[0] + " fire away!" + "\n" + "Oh no! You have no bullets, and can't fire any shots! You been consumed by the danger";
					emotion= -100;
				}
				else{
					response = "How can you going to fire a gun without a gun?";
					emotion--;
				}
			}
			else if(findKeyword(statement,"run") >=0 || findKeyword(statement,"back")>=0){
				if(responses[2].equals("left")){
					response = "You can't find your way out of the mushroom forest because you can't see anything!";
					emotion--;
				}
				else if(responses[2].equals("right")){
					response = "Another Poop Monster appeared behind you! " + responses[0] + "! There is no where to run!";
					emotion--;
				}
				else{
					response ="The goblin cavaliers run faster than you on their wolves!" + "\n" + "You got slain by the goblins.";
					emotion = -100;
				}
			}
			else if(findKeyword(statement, "talk") >= 0 || findKeyword(statement,"negotiate") >=0) {

				if(responses[2].equals("left")){
					response = "Talk? Talk to who? Me? Right now is not the time for it!";
					emotion--;
				}
				else if(responses[2].equals("right")){
					if (responses[1].equals("Poop on a Stick")){
						response = "The Poop Monster seem to acknowledge your Poop on a Stick!" + "\n" + "The Poop Monsters are now on your command!" + "\n" + "BobBot; Oh is that the demon lord up there!" + responses[0] + "! What do we do?";
						emotion++;
						nextQuestion = true;
					}
					else{
						response = "..."+"\n"+"The Poop Monsters responded by crushing you";
						emotion=-100;
					}
				}
				else{
					response = "..."+"\n"+ "The goblins responded with thousands of arrow, they appear not understanding your language!";
					emotion = -100;
				}
			}
			else if(findKeyword(statement, "I want to") >=0){
				response = transformIWantToStatement(statement);
				emotion--;
			}
			else if(findKeyword(statement, "I want") >=0){
				response = transformIWantStatement(statement);
				emotion--;
			}
			else{
				response = getRandomResponse();
				emotion--;
			}
		}

		else if(rIndex == 4){
			if (findKeyword(statement, "attack")>=0){
				if(responses[1].equals("Lolipop")){
					response = "I will support you from here! Go for it!" + "\n" + "You have slained the demon lord with Lolipop's invincibility! You saved the world!" +"\n"+ "BobBot: Do you want to return home?";
					nextQuestion = true;
				}
				else{
					response = "Go " + responses[1] + "! Use your Poop Monsters" + "\n" + "You defeated the demon lord with the Poop Monsters, because the demon lord hates poop. You saved the world!" + "\n" + "BobBot: Do you want to return home?";
					nextQuestion = true;
				}
			}
			else if (findKeyword(statement, "talk")>=0){
				if(responses[1].equals("Lolipop")){
					response = "..." + "\n" + "The demon lord ignored your talks, but waited until your invincibility wears off." + "\n" + "The demon lord slained you with dark magic!";
					emotion = -100;
				}
				else{
					response = "..." + "\n" + "The demon lord ignored your talks, but killed your Poop Monsters while your talking, from afar." + "\n" + "The demon lord slained you with dark magic!";
					emotion = -100;
				}
			}
			else{
				response = "What? What did you just say?" + "\n" + "The demon lord made his moves first while you're having conversation with BobBot, and assasinated you from your shadows!";
				emotion = -100;
			}
		}

		else if(rIndex == 5){
			if(findKeyword(statement,"yes")>=0){
				response = "I will send you home then!" + "\n" + "BobBot send you home, but he became the next demon lord that rule this world for eternity!";
			}
			else{
				response = "No? Ok I guess you have to die here and now, because I'm going to become the next demon lord, you're not going to be my trouble!" + "\n" + "You got slained by BobBot, and BobBot became the next demon lord that rule this world for eternity!";
			}
			emotion = -100;
		}

		return ("BobBot: "+ response);
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
		return "Why do you want to " + restOfStatement + "? Do you not want to save this world? We have no time to waste here, I beg you to make a good decision now!";
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
		return "I want " + restOfStatement + " too, but we can't get it unless we defeat the demon lord and save this world so lets keep on moving!";
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
		if (rIndex == 2){
			return randomPathResponses[r.nextInt(randomPathResponses.length)];
		}
		if (rIndex == 3){
			return randomDecisionResponses[r.nextInt(randomDecisionResponses.length)];
		}
		return ("um Hello?");
	}

	private String[] randomWeaponResponses = {"I don't see any of that here, how about a sword?",
			"Do you really think that would be on the ground? Just pick up that Poop on a Stick!",
			"Open your eyes, there are only a sword, a gun, a wand, a Lolipop, and a Poop on a Stick for you to choose from!",
			"We don't have time for this, just get that Lolipop!"
			};

	private String[] randomPathResponses = {"Stop wasting time, we need to get to the demon lord as soon as possible! Why not just take the middle path",
			"That's not possible, let's just take the left route!",
			"Halt your nonsense, just say that you want to go right!" };

	private String[] randomDecisionResponses = {"Interesting decision, but I don't think we can do that at this moment.",
			"That's not possible, stop daydreaming, we don't have time! Attack? Run? Negotiate? Just do something!",
			"If you can't make the decision of what to do, I will make the decision of killing you, so you don't have to suffer!"};

}

