package org.citrya.stellar.delivery;

public class MessageGenerator {
	public static String generate(DeliveryContentBean msgContent) {
		if(msgContent.getMsgTemplate() == null || msgContent.getMsgTemplate() == "") {
			msgContent.setMsgTemplate("Dear%20{{name}}%20share%20your%20Dirt%20Mania%20memories%20with%20friends%20at%20http://tclr.co/{{url}}%20Click%20on%20Share%20and%20win%20a%20free%20ride%20when%20your%20friends%20visit!");
		}
		String message = msgContent.getMsgTemplate().replaceAll("{{name}}", msgContent.getContact().getUser().getFullName());
		message = message.replaceAll("{{url}}", UrlGenerator.generate(msgContent));
		return message;		
	}
}
