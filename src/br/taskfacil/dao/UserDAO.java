package br.taskfacil.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.taskfacil.db.JPAUtil;
import br.taskfacil.models.Task;
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
	
	public String findName(User user) {
		List list;
		Query query = manager.createQuery("Select nome from User where email=:email");
		query.setParameter("email", user.getEmail());
		list = query.getResultList();
		return list.get(0).toString();

	}
	
	public Long findId(User user) {
		List list;
		Query query = manager.createQuery("Select id from User where email=:email");
		query.setParameter("email", user.getEmail());
		list = query.getResultList();
		return Long.parseLong(list.get(0).toString());

	}

	public boolean findPassword(User user) {
		List rows = null;
		Query query = manager.createQuery("Select password from User where email=:email");
		query.setParameter("email", user.getEmail());
		rows = query.getResultList();
		if (rows.isEmpty()) {
			return false;
		} else {
			if (rows.get(0).toString().equals(user.getPassword())) {
				return true;
			}

		}
		return false;
	}

	public boolean findEmail(String email) {
		List rows = null;
		Query query = manager.createQuery("Select email from User where email=:email");
		query.setParameter("email", email);
		rows = query.getResultList();

		if (rows.isEmpty()) {
			return false;
		} else {

			return true;

		}

	}

}