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

public class ImpulseCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {		
			HttpsURLConnection con = null;
	        String[] result = new String[4];
	        
	        try {
	            URL url = new URL("https://www.losungen.de/bethlehem/");
	            con = (HttpsURLConnection) url.openConnection();
	            con.setRequestMethod("GET");
	            con.setDoInput(true);
	            con.setConnectTimeout(5000);
	            con.setReadTimeout(5000);
	            con.connect();

	            if (con.getResponseCode() == 200) {
            		InputStream in = new BufferedInputStream(con.getInputStream());
            		Document doc = Jsoup.parse(IOUtils.toString(in, "UTF-8"));
            		
            		Calendar cal = Calendar.getInstance();
            		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
            		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.M.YYYY");
            		
            		if(!(doc.getElementsByClass("csc-firstHeader").get(0).text().contains(sdf.format(cal.getTime())) || doc.getElementsByClass("csc-firstHeader").get(0).text().contains(sdf2.format(cal.getTime())))) {
            			in.close();
    	                con.disconnect();
    	                
    	                URL url2 = new URL("https://www.losungen.de/bethlehem-2/");
    		            con = (HttpsURLConnection) url2.openConnection();
    		            con.setRequestMethod("GET");
    		            con.setDoInput(true);
    		            con.setConnectTimeout(5000);
    		            con.setReadTimeout(5000);
    		            con.connect();
    		            
    		            if (con.getResponseCode() == 200) {
    	            		in = new BufferedInputStream(con.getInputStream());
    	            		doc = Jsoup.parse(IOUtils.toString(in, "UTF-8"));
    	            		
    	            		cal = Calendar.getInstance();
    	            		sdf = new SimpleDateFormat("dd.MM.YYYY");
    	            		sdf2 = new SimpleDateFormat("dd.M.YYYY");
    	            		
    	            		if(!(doc.getElementsByClass("csc-firstHeader").get(0).text().contains(sdf.format(cal.getTime())) || doc.getElementsByClass("csc-firstHeader").get(0).text().contains(sdf2.format(cal.getTime())))) {
    	            			result = null;
    	            		}
    		            }
            		}
    		            
    		        result[0] = doc.getElementsByClass("csc-default").get(0).text();
                
            		in.close();
	                con.disconnect();
	            }
	        } catch (IOException e) {
	        } finally {
	            if (con != null) {
	                con.disconnect();

	            }
	        }
	        
	        if(result != null  && (!result[0].equals("null"))) {
          		channel.sendMessage(result[0]).queue();
            } else {
            	channel.sendMessage("No impulse found!").queue();
            }
		}
	}
}
