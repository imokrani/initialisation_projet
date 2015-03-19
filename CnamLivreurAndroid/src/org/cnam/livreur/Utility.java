package org.cnam.livreur;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	
	private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    
    private static final String PHONE_PATTERN= "(0|\\+33|0033)[1-9][0-9]{8}"; 
    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    public static boolean validatePhoneNumber(String numero) {
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(numero);
        return matcher.matches();
 
    }
    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
}
