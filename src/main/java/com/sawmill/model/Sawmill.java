package com.sawmill.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="sawmill")
public class Sawmill implements Comparable<Sawmill>{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Column(name="name",unique=true)
	private String name;
	
	
	@Column(name="city")
	private String city;
	
	
	@Column(name="country")
	private String country;
	
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="created_at")
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="updated_at")
	private Date updatedAt;
	
	public Sawmill() {
		super();
	}

	public Sawmill(String name, String city, String country, Date createdAt, Date updatedAt) {
		super();
		this.name = name;
		this.city = city;
		this.country = country;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public int compareTo(Sawmill o) {
		// TODO Auto-generated method stub
		if(this.getName().hashCode() > o.getName().hashCode()){
			return 1;
		}else if (this.getName().hashCode() < o.getName().hashCode()){
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public String toString(){
		return "id = "+this.id+" , name = "+this.name+" , city = "+this.city+ " , country = "+country+
					" , createdAt = "+this.createdAt+" , updatedAt = "+this.updatedAt;
	}
	
	
	 

	

}
