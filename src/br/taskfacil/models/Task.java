package br.taskfacil.models;

import java.util.Date;
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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 64)
	private String title;
	@Column(length = 64)
	private String description;
	@Column(length = 64)
	private String location;
	@Type(type="date")
	private Date realizationDate;

	@ManyToOne
	private User idOwner;

	@ManyToMany
	private List<User> collaborators;

	public Task(String title, String description, String location, Date realizationDate) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.realizationDate = realizationDate;
	}

	public Task() {
		this.id = -1L;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getRealizationDate() {
		return realizationDate;
	}

	public void setRealizationDate(Date realizationDate) {
		this.realizationDate = realizationDate;
	}

	public User getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(User idOwner) {
		this.idOwner = idOwner;
	}

	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", realizationDate=" + realizationDate + ", idOwner=" + idOwner + "]";
	}

}
