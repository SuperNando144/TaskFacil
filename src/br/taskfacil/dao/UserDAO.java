package br.taskfacil.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.taskfacil.db.JPAUtil;
import br.taskfacil.models.User;

public class UserDAO {
	private EntityManager manager;

	public UserDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	public void finalize() {
		this.manager.close();
	}

	public void insert(User user) {
		this.manager.getTransaction().begin();
		this.manager.persist(user);
		this.manager.getTransaction().commit();
	}

	public void update(User user) {
		this.manager.getTransaction().begin();
		this.manager.merge(user);
		this.manager.getTransaction().commit();
	}

	public void delete(User user) {
		this.manager.getTransaction().begin();
		this.manager.remove(user);
		this.manager.getTransaction().commit();
	}

	public List<User> findAll() {
		List<User> list;
		Query query = manager.createQuery("Select u from User u");
		list = query.getResultList();

		return list;

	}
	
	public boolean findSpecific(User user){
		boolean contem;
		this.manager.getTransaction().begin();
		contem = this.manager.contains(user);
		this.manager.getTransaction().commit();
		return contem;
	}

}