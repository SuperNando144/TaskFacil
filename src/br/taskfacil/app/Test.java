package br.taskfacil.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.taskfacil.dao.UserDAO;
import br.taskfacil.models.User;

public class Test {
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	
	public static void main(String[] args) {
		
		System.out.println(validarEmail("fehaddad"));
		String email = "fernandohaddad";
		int indiceEmail;
		indiceEmail = email.indexOf('@');
	    if (indiceEmail > 0)
	        System.out.println("Valido");
	    else
	    	System.out.println("InValido");
		
	}
	
	public static boolean validarEmail(String email){
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	 }
	
	

}
