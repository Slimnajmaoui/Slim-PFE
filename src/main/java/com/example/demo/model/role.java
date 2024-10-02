package com.example.demo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
public class role {

	@Id
	private String id ;
	private String profil ;

	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public role(String id, String profil) {
		super();
		this.id = id;
		this.profil = profil;
	}
	public role() {
		super();
	} 
	
}
