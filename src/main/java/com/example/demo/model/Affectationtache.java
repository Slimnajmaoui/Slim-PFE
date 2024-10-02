package com.example.demo.model;



	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "Affectationtaches")
	public class Affectationtache {
	  @Id
	  private String id;

	  private String nom;
	  private String description;
	  private String etat;
	  private User user;
	  private Tache tache ; 
	  
	  public Affectationtache(Tache tache) {
		super();
		this.tache = tache;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}

	private String datecreation;
	  private String datemodification;
	  private String datedebut;
	  private String datefin;

	  public Affectationtache(String datedebut, String datefin) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
	}
	public String getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}
	public String getDatefin() {
		return datefin;
	}
	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}
	
	public Affectationtache() {

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
	  public Affectationtache(String nom, String description, String etat) {
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
	    return "Affectationtache [id=" + id + ", nom=" + nom + ", desc=" + description + "]";
	  }
	}