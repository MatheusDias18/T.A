package br.edu.ifpe.jpa.example.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BLOG")
public class Blog implements Cloneable {

	private String name;
	private String description;
	private Date creationDate;
	private Collection<Post> posts;
	private Integer identifier;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POS_IDENTIFIER")
	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BLO_NAME", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "BLO_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "BLO_CREATION_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "Blog [identifier=" + getIdentifier() + ", name=" + name
				+ ", description=" + description + ", creationDate="
				+ creationDate + "]";
	}

	@Override
	public Blog clone() {
		try {
			return (Blog) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
	public Collection<Post> getPosts() {
		return posts;
	}
}