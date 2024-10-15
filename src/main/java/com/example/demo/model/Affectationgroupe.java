package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Affectationgroupes")
public class Affectationgroupe {
  @Id
  private String id;

  private String nom;
  private String description;
  private String etat;
  private User user;
  private Groupe groupe ; 
  public Affectationgroupe(Groupe groupe) {
	super();
	this.groupe = groupe;
}
public Groupe getGroupe() {
	return groupe;
}
public void setGroupe(Groupe groupe) {
	this.groupe = groupe;
}

private String datecreation;
  private String datemodification;

  public Affectationgroupe() {

  }
  public User getuser() {
	    return user;
	  }

	  public void setiduser(User user) {
	    this.user = user;
	  }
  public String getdatemodification() {
	    return datemodification;
	  }

	  public void setdatemodification(String datemodification) {
	    this.datemodification = datemodification;
	  }
	  
  public String getdatecreation() {
	    return datecreation;
	  }

	  public void setdatecreation(String datecreation) {
	    this.datecreation = datecreation;
	  }
  public Affectationgroupe(String nom, String description, String etat) {
    this.nom = nom;
    this.description = description;

  }

  public String getId() {
    return id;
  }

  public String getnom() {
    return nom;
  }

  public void setnom(String nom) {
    this.nom = nom;
  }

  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }

  public String getetat() {
    return etat;
  }

  public void setetat(String etat) {
    this.etat = etat;
  }

  @Override
  public String toString() {
    return "Affectationgroupe [id=" + id + ", nom=" + nom + ", desc=" + description + "]";
  }
}