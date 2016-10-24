package dashboard.web.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dashboard.web.model.Message;
import dashboard.web.model.MessagePlayerVod;



@RestController
public class MessageController {
	public static List<Message> listOfMessages = new ArrayList<Message>();
	@RequestMapping(value = "/checkMessage", method = RequestMethod.GET,headers="Accept=application/json")
	public Message getMessage(@RequestParam(value="id", defaultValue="") String id)
	{
		
		return getMessageById(id);
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST,headers="Accept=application/json")
	public void send(@RequestParam(value="message", defaultValue="") String message, @RequestBody String param) throws JsonParseException, IOException
	{
		
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jp = jsonFactory.createParser(param);
		jp.setCodec(new ObjectMapper());
		JsonNode jsonNode = jp.readValueAsTree();
		String deviceid="";
		
		if (jsonNode.get("deviceid")!=null)
			{
			deviceid=jsonNode.get("deviceid").textValue();
			}
		

		if ( message.equalsIgnoreCase("OPEN_PLAYER_VOD"))
		{
			String programid="";
			if (jsonNode.get("programid")!=null)
			{
				programid=jsonNode.get("programid").textValue();
			}	
			MessagePlayerVod m=new MessagePlayerVod(message,deviceid,programid);
			listOfMessages.add(m);
		}
		else
		{
			Message m=new Message(message,deviceid);
			listOfMessages.add(m);
		}
	}


	public Message getMessageById(String deviceid)
	{
		


		for (Message message: listOfMessages) {
			if(message.getDeviceid().equals(deviceid)){
				if (message instanceof MessagePlayerVod)
				{
					MessagePlayerVod mp=(MessagePlayerVod)message;
					MessagePlayerVod m=new MessagePlayerVod(mp.getMessage(),mp.getDeviceid(),mp.getProgramid());
				    listOfMessages.remove(message);
					return m;
				}
				else
				{
					Message m=new Message(message.getMessage(),message.getDeviceid());
				    listOfMessages.remove(message);
					return m;
				}
			}	
		}
		
		return new Message("NO_MESSAGES",deviceid);
	}
	
	public void readJsonData(JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.fields();
		while(ite.hasNext()){
			Map.Entry<String, JsonNode> entry = ite.next();
			if(entry.getValue().isObject()) {
				readJsonData(entry.getValue());
			} else {
			    System.out.println("key:"+entry.getKey()+", value:"+entry.getValue());
			}
		}
	}


}
