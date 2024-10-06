package com.pipespace.pipespace.requests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Mock")
public class MockRequest {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String section;
	private String response;
	private int httpResponse;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getHttpResponse() {
		return httpResponse;
	}
	public void setHttpResponse(int httpResponse) {
		this.httpResponse = httpResponse;
	}
	
	
	
	
}
