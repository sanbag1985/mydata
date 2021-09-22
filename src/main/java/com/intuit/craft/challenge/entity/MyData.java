package com.intuit.craft.challenge.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mydata")
public class MyData {
	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private Long timestamp;

	@OneToMany(targetEntity = Attribute.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "a_fk", referencedColumnName = "id")
	private List<Attribute> attributes;

	public MyData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

}
