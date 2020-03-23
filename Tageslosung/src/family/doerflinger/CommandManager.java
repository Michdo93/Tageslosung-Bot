package family.doerflinger;

import java.util.concurrent.ConcurrentHashMap;

import family.doerflinger.commands.BlessingCommand;
import family.doerflinger.commands.ClearCommand;
import family.doerflinger.commands.ExitCommand;
import family.doerflinger.commands.HelpCommand;
import family.doerflinger.commands.HiCommand;
import family.doerflinger.commands.ImpulseCommand;
import family.doerflinger.commands.TimeCommand;
import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager {
	public ConcurrentHashMap<String, ServerCommand> commands;

	public CommandManager() {
		this.commands = new ConcurrentHashMap<>();
		//this.commands.put("clear", new ClearCommand());
		this.commands.put("time", new TimeCommand());
		this.commands.put("hi", new HiCommand());
		this.commands.put("blessing", new BlessingCommand());
		this.commands.put("help", new HelpCommand());
		this.commands.put("exit", new ExitCommand());
		this.commands.put("impulse", new ImpulseCommand());
	}
	
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		ServerCommand cmd;
		
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			
			System.out.println("perform True");
			
			cmd.performCommand(m, channel, message);
			return true;
		}
		
		return false;
	}

}
