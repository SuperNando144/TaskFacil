package br.taskfacil.app;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.hibernate.sql.ordering.antlr.Factory;

import com.sun.prism.impl.FactoryResetException;

import br.taskfacil.controllers.AnchorPaneCadastroUsuarioDialogController;
import br.taskfacil.dao.TaskDAO;
import br.taskfacil.dao.UserDAO;
import br.taskfacil.db.JPAUtil;
import br.taskfacil.models.Task;
import br.taskfacil.models.User;

public class Test {

	private static Task task;
	private static User user;
	
	public static void main(String[] args) {
		EntityManager manager = JPAUtil.getEntityManager();
		
		task = new Task();
		task.setLocation("Teste");
		task.setTitle("Ferdinando");
		task.setRealizationDate("fdsfsd");
		task.setDescription("hsuhsus");
		
		user = new User();
		user = manager.find(User.class, 4L);
		
		task.setIdOwner(user);
		
		manager.getTransaction().begin();
		manager.persist(task);		
		manager.getTransaction().commit();
		
		System.out.println(task.getTitle());
	}
	
	
	
	

}
