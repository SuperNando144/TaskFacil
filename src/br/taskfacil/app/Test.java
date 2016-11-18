package br.taskfacil.app;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.taskfacil.controllers.AnchorPaneCadastroUsuarioDialogController;
import br.taskfacil.dao.UserDAO;
import br.taskfacil.models.User;

public class Test {

	
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		List<User> list = dao.findAll();
	}
	
	
	
	

}
