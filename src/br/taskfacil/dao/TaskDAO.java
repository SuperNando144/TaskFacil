package br.taskfacil.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.taskfacil.db.JPAUtil;
import br.taskfacil.models.Task;
import br.taskfacil.models.User;

public class TaskDAO {
	private EntityManager manager;

	public TaskDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	public void finalize() {
		this.manager.close();
	}

	public void insert(Task task) {
		this.manager.getTransaction().begin();
		this.manager.persist(task);
		this.manager.getTransaction().commit();
	}

	public void update(Task task) {
		this.manager.getTransaction().begin();
		this.manager.merge(task);
		this.manager.getTransaction().commit();
	}

	public void delete(Task task) {
		this.manager.getTransaction().begin();
		this.manager.remove(task);
		this.manager.getTransaction().commit();
	}

	public List<Task> findAll() {
		List<Task> list;
		Query query = manager.createQuery("Select t from Task t");
		list = query.getResultList();

		return list;

	}

	public List<Task> findSpecific(User user) {
		List<Task> list;
		Query query = manager.createQuery("select t from Task t where idowner_id = :idowner_id");
		query.setParameter("idowner_id", user.getId());
		list = query.getResultList();
		return list;

	}
}
