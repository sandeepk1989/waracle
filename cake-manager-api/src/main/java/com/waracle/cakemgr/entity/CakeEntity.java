package com.waracle.cakemgr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CAKE", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "TITLE") })
public class CakeEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "TITLE", unique = true, nullable = false, length = 100)
	private String title;

	@Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
	private String description;

	@Column(name = "IMAGE", unique = false, nullable = false, length = 300)
	private String image;

	public String getTitle() {
		return title;
	}

	public CakeEntity setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public CakeEntity setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getImage() {
		return image;
	}

	public CakeEntity setImage(String image) {
		this.image = image;
		return this;
	}

}