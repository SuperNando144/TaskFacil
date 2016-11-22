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

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 64)
	private String title;
	@Column(length = 64)
	private String description;
	@Column(length = 64)
	private String location;
	@Column(length = 10)
	private String realizationDate;

	@Access(AccessType.PROPERTY)
	@ManyToMany
	@JoinTable(name = "UserTask",
	joinColumns={@JoinColumn(name="UserId")},
	inverseJoinColumns = {@JoinColumn(name="TaskId")})
	private List users;
	
	public Task(int id, String title, String description, String location, String realizationDate
			) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.realizationDate = realizationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRealizationDate() {
		return realizationDate;
	}

	public void setRealizationDate(String realizationDate) {
		this.realizationDate = realizationDate;
	}

	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", realizationDate="
				+ realizationDate + ", collaborators=" + "]";
	}

}
