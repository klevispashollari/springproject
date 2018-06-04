package com.utility;

import java.util.Date;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Validate {

	public static boolean validateDate(Date dateNisje, Date datePerfundimi,
			Date dataSot) {
		if (dateNisje.compareTo(dataSot) < 0) {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("TASK_STARTDATE_ERROR"));
			return false;
		} else {
			if (datePerfundimi.compareTo(dateNisje) < 0) {
				MessagesUtility.addMessage(MessagesUtility.bundle
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
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("INCORRECT_OLD_PASSWORD"));
			return false;
		} else {
			if (!newPassword.equals(confirmPassword)) {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("PASSWORD_NOT_CONFIRM"));
				return false;
			} else {
				return true;
			}
		}

	}
}
