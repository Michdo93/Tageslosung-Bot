package family.doerflinger.commands;


import family.doerflinger.Tageslosung;
import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ExitCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.ADMINISTRATOR)) {			
			//shutdown();
			Tageslosung.INSTANCE.shutdown();
		}
	}

}
