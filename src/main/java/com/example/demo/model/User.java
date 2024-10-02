package com.example.demo.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {


  @Id
  private String id;

  private String nom;
  private String prenom;
  private String email ;
  private String mdp;
  private String datecreation;
  private role role ;

  public User(com.example.demo.model.role role) {
	super();
	this.role = role;
}


public role getRole() {
	return role;
}


public void setRole(role role) {
	this.role = role;
}


private String statut;


  public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getPrenom() {
	return prenom;
}


public void setPrenom(String prenom) {
	this.prenom = prenom;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getMdp() {
	return mdp;
}


public void setMdp(String mdp) {
	this.mdp = mdp;
}


public String getDatecreation() {
	return datecreation;
}


public void setDatecreation(String datecreation) {
	this.datecreation = datecreation;
}


public String getStatut() {
	return statut;
}


public void setStatut(String statut) {
	this.statut = statut;
}


public User(String id, String nom, String prenom, String email, String mdp, String datecreation, String statut) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.mdp = mdp;
	this.datecreation = datecreation;
	this.statut = statut;
}


public User() {

  }

}