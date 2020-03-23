package family.doerflinger.commands;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;



import family.doerflinger.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BlessingCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {		
			HttpsURLConnection con = null;
	        String[] result = new String[4];
	        
	        try {
	            URL url = new URL("https://www.losungen.de/die-losungen/");
	            con = (HttpsURLConnection) url.openConnection();
	            con.setRequestMethod("GET");
	            con.setDoInput(true);
	            con.setConnectTimeout(5000);
	            con.setReadTimeout(5000);
	            con.connect();

	            if (con.getResponseCode() == 200) {
            		InputStream in = new BufferedInputStream(con.getInputStream());
            		Document doc = Jsoup.parse(IOUtils.toString(in, "UTF-8"));
            		result[0] = doc.getElementsByClass("losungen-verse1txt").get(0).text();
            		result[1] = doc.getElementsByClass("losungen-verse1verse").get(0).text();
            		result[2] = doc.getElementsByClass("losungen-verse2txt").get(0).text();
            		result[3] = doc.getElementsByClass("losungen-verse2verse").get(0).text();
                
            		in.close();
	                con.disconnect();
	            }
	        } catch (IOException e) {
	        } finally {
	            if (con != null) {
	                con.disconnect();

	            }
	        }
	        
	        if(result != null  && (!result[0].equals("null") || !result[1].equals("null") || !result[2].equals("null") || !result[3].equals("null"))) {
	        	Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
				
				channel.sendMessage("Losung für den " + sdf.format(cal.getTime()) + ":.").queue();
				channel.sendMessage("Losungstext:").queue();
				
          		channel.sendMessage(result[0]).queue();
          		channel.sendMessage(result[1]).queue();
          		
          		channel.sendMessage("Lehrtext:").queue();
          		
          		channel.sendMessage(result[2]).queue();
          		channel.sendMessage(result[3]).queue();
	        } else {
            	channel.sendMessage("No blessings found!").queue();
            }
		}
	}
}
