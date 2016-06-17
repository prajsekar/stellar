package org.citrya.stellar.delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SmsDeliveryHandler implements DeliveryHandler{
	private static String smsProviderUrl = "http://api.mvaayoo.com/mvaayooapi/MessageCompose?user=info@citrya.com:citrya230520&senderID=DMANIA&receipientno={{mobile}}&dcs=0&msgtxt={{messge}}&state=4";	
	
	public void send(DeliveryContentBean content) {
		String msg = MessageGenerator.generate(content);
		String phoneNumber = content.getContact().getValue().replaceAll("[-+.^:,]","");
		String smsGateway = smsProviderUrl.replaceAll("{{mobile}}", phoneNumber);
		smsGateway = smsGateway.replaceAll("{{message}}", msg);
		URL url= null;		
		
		BufferedReader reader;
		try {
			url = new URL(smsGateway);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String readerContent = null;
			while ((readerContent = reader.readLine())!= null)
		 	      System.out.println(readerContent);		 	
			reader.close();			
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
