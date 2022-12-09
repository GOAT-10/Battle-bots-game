package bots;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;

import java.awt.*;

/**
 * The Terminator bot uses defensive and offensive strategy to move and shoot.
 *
 * @author Ria Gahlot
 */
public class TerminatorBot extends Bot {

	/**
	 * Image for drawing
	 */
	private Image image = null;
	/**
	 * Next message to send
	 */
	private String nextMessage;
	/**
	 * My name
	 */
	private String name;

	/**
	 * Array of warning messages
	 */
	String[] messages = {"Stand back ","You are too close, ","Get back or face the consequences, ","Hands up, "};
	/**
	 * Array to remember who has been warned, so we don't spam chat with the
	 * same warning over and over again.
	 */
	private boolean[] warned;
	/**
	 * Name of my image
	 */
	public String[] imageNames()
	{
		String[] paths = {"starfish4.png"};
		return paths;
	}

	/**
	 * Save my image
	 */
	public void loadedImages(Image[] images)
	{
		if (images != null && images.length > 0)
			image = images[0];
	}

	/**
	 * Shoots occasionally or yells at anything too close
	 */
	public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets)
	{
		return BattleBotArena.FIREUP;
	}

	/**
	 * Construct and return my name
	 */
	public String getName()
	{
		if (name == null)
			name = "Sentry"+(botNumber<10?"0":"")+botNumber;
		return name;
	}

	/**
	 * Reset the "warned" array
	 */
	public void newRound()
	{
		warned = new boolean[BattleBotArena.NUM_BOTS];
		for (int i=0; i<warned.length; i++)
			warned[i] = false;
	}

	/**
	 * Draw the sentry
	 */
	public void draw (Graphics g, int x, int y)
	{
		if (image != null)
			g.drawImage(image, x,y,Bot.RADIUS*2, Bot.RADIUS*2, null);
		else
		{
			g.setColor(Color.gray);
			g.fillOval(x, y, Bot.RADIUS*2, Bot.RADIUS*2);
		}
	}

	/**
	 * Required method
	 */
	public void incomingMessage(int id, String msg)
	{
	}

	/**
	 * Team ARENA!
	 */
	public String getTeamName()
	{
		return "Arena";
	}

	/**
	 * Send and clear my message buffer
	 */
	public String outgoingMessage()
	{
		String msg = nextMessage;
		nextMessage = null;
		return msg;
	}
}
