package com.pipespace.pipespace.requests;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="body_request")
public class Request {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="doby", columnDefinition = "TEXT")
	private String body;
	
	public Request() {}

	public Request(String body) {
		super();
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	

}
