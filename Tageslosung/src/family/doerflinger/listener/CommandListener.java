package family.doerflinger.listener;

import family.doerflinger.Tageslosung;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		System.out.println("onMessageReceived");
		
		String message = event.getMessage().getContentDisplay();
		
		if(event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();
			
			// !los arg0 arg1 arg2 ...
			if(message.startsWith("los!")) {
				String[] args = message.substring(4).split(" ");
				
				System.out.println("starts With");
				
				if(!Tageslosung.INSTANCE.getCmdMan().perform(args[0], event.getMember(), channel, event.getMessage())) {
					channel.sendMessage("Unknown Command! See help with los!help").queue();
				}
			}
		}
	}
}
