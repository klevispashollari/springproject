package com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtility {

	public static void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		
		requestContext().addMessage(null, message);
	}

	private static FacesContext requestContext() {
		return FacesContext.getCurrentInstance();
	}

}
