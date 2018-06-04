package com.utility;

import java.util.Date;
import java.util.ResourceBundle;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Validate {
	public final static ResourceBundle bundle = ResourceBundle
			.getBundle("com.Messages");

	public static boolean validateDate(Date dateNisje, Date datePerfundimi,
			Date dataSot) {
		if (dateNisje.compareTo(dataSot) < 0) {
			MessagesUtility
					.addMessage(bundle.getString("TASK_STARTDATE_ERROR"));
			return false;
		} else {
			if (datePerfundimi.compareTo(dateNisje) < 0) {
				MessagesUtility.addMessage(bundle
						.getString("TASK_ENDDATE_ERROR"));
				return false;
			} else {
				return true;
			}
		}
	}

	public static boolean changePassword(String oldPassword,
			String newPassword, String confirmPassword, String dbPassword) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		if (!passwordEncryptor.checkPassword(oldPassword, dbPassword)) {
			MessagesUtility.addMessage(bundle
					.getString("INCORRECT_OLD_PASSWORD"));
			return false;
		} else {
			if (!newPassword.equals(confirmPassword)) {
				MessagesUtility.addMessage(bundle
						.getString("PASSWORD_NOT_CONFIRM"));
				return false;
			} else {
				return true;
			}
		}

	}
}
