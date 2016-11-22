package br.taskfacil.models;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 64)
	private String nome;
	@Column(length = 64)
	private String email;
	@Column(length = 64)
	private String password;
	@Access(AccessType.PROPERTY)
	@ManyToMany
	@JoinTable(name = "UserTask",
		joinColumns={@JoinColumn(name="TaskId")},
		inverseJoinColumns = {@JoinColumn(name="UserId")})
	private List tasks;
	
	public User(String nome, String email, String password) {
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

	public User() {
		this.id = -1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public List getTasks() {
		return tasks;
	}

	public void setTasks(List tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}
