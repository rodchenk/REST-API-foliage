package com.foliage.security.common;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SecurityUtils {
	
	public static boolean isEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public static boolean isID(String str) {
		Integer id;
		try {
			id = Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return id > 0;
	}
}
