package br.taskfacil.app;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.taskfacil.controllers.AnchorPaneCadastroUsuarioDialogController;
import br.taskfacil.dao.TaskDAO;
import br.taskfacil.dao.UserDAO;
import br.taskfacil.models.User;

public class Test {

	
	
	public static void main(String[] args) {
		User user = new User("Fernando", "fer@hot.com", "1234");
		user.setId(5L);
		TaskDAO dao = new TaskDAO();
		dao.findSpecific(user);
		
	}
	
	
	
	

}
