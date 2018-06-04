package com.utility;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtility {
	
	public final static ResourceBundle bundle = ResourceBundle
			.getBundle("com.Messages");

	public static void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		
		requestContext().addMessage(null, message);
	}

	private static FacesContext requestContext() {
		return FacesContext.getCurrentInstance();
	}

}
