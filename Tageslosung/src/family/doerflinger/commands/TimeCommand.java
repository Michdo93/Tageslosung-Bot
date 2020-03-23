package family.doerflinger.commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class TimeCommand implements ServerCommand {
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
			
			channel.sendMessage("Es ist " + sdf.format(cal.getTime()) + "!").queue();
		}
	}
}
