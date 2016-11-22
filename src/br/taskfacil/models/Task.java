package br.taskfacil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@Column(length = 10)
	private String realizationDate;
	private String collaborators;

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

	public String getRealizationDate() {
		return realizationDate;
	}

	public void setRealizationDate(String realizationDate) {
		this.realizationDate = realizationDate;
	}

	public String getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(String collaborators) {
		this.collaborators = collaborators;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", realizationDate="
				+ realizationDate + ", collaborators=" + collaborators + "]";
	}

}
