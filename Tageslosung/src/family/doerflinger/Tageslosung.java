package family.doerflinger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import family.doerflinger.listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Tageslosung {

	public static Tageslosung INSTANCE;
	
	public ShardManager shardMan;
	private CommandManager cmdMan;

	public static void main(String[] args) {
		try {
			new Tageslosung();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public Tageslosung() throws LoginException, IllegalArgumentException {
		INSTANCE = this;
		
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("NjkxMjcyOTQ2MDg1Mzk2NTQw.Xndlmw.3Dr8WDIL5H93JgMSlD_yGPF7nYU");
		
		builder.setActivity(Activity.playing("Tageslosung"));
		builder.setStatus(OnlineStatus.ONLINE);
		
		this.cmdMan = new CommandManager();
			    
		builder.addEventListeners(new CommandListener());
		  
		this.shardMan = builder.build();
		System.out.println("Tageslosung-Bot online");	
		shutdown();
	}
	
	public void shutdown() {
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				while((line = reader.readLine()) != null) {
					
					if(line.equalsIgnoreCase("exit")) {
						if(shardMan != null) {
							shardMan.setStatus(OnlineStatus.OFFLINE);;
							shardMan.shutdown();
							System.out.println("Tageslosung-Bot offline");
						}
						
						reader.close();
					} else {
						System.out.println("Use 'exit' to shutdown");
					}
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			if(this.shardMan != null) {
				this.shardMan.setStatus(OnlineStatus.OFFLINE);;
				this.shardMan.shutdown();
			} else {
				System.out.println("Use 'exit' to shutdown");
			}
			
		}).start();
	}
	
	public CommandManager getCmdMan() {
		return this.cmdMan;
	}
}