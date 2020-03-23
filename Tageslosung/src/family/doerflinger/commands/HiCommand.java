package family.doerflinger.commands;

import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HiCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {			
			channel.sendMessage("Hallo " + m.getAsMention() + "!").queue();
		}
	}

}
