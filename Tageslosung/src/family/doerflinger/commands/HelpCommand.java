package family.doerflinger.commands;

import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {
			channel.sendMessage("Welcome to the documentation of Tageslosung. Seriously, the program is so self-explanatory that you don't even need this extraordinary documentary. If you seriously need the help command here, you have not understood the CMD syntax fundamentally. The program offers you the following fabulous possibilities:").queue();
			channel.sendMessage("los!hi greets the calling user by its nickname").queue();
			channel.sendMessage("los!time show the current time").queue();
			channel.sendMessage("los!blessing shows the daily blessing and educational text").queue();
			channel.sendMessage("los!impulse shows the recommended impulse to the current daily message. This could be a devotion, a song verse or a prayer from the Christian tradition").queue();
			channel.sendMessage("los!exit shutdowns the Tageslosung bot").queue();
			channel.sendMessage("los!help Seriously?").queue();
		}
	}

}
