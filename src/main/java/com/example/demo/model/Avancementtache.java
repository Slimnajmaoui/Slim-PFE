

	package com.example.demo.model;



	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "Avancementtache")
	public class Avancementtache {
	  @Id
	  private String id;

	  private String nom;
	  private String description;
	  private String datedebut;
	  private String datefin;
	  private Affectationprojet idprojet;
	  private String etat;
	  private String avancement;
	  private String datecreation;

	  private String datemodification;
	
	  
	public Avancementtache(String id, String nom, String description, String datedebut, String datefin,
			Affectationprojet idprojet, String etat, String avancement, String datecreation, String datemodification) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.idprojet = idprojet;
		this.etat = etat;
		this.avancement = avancement;
		this.datecreation = datecreation;
		this.datemodification = datemodification;
	}
	public String getdatecreation() {
		    return datecreation;
		  }
	  public Affectationprojet getidprojet() {
		    return idprojet;
		  }

		  public void setidprojet(Affectationprojet idprojet) {
		    this.idprojet = idprojet;
		  }
		  public void setdatecreation(String datecreation) {
		    this.datecreation = datecreation;
		  }
		  public String getdatemodification() {
			    return datemodification;
			  }

			  public void setdatemodification(String datemodification) {
			    this.datemodification = datemodification;
			  }
			  
			  
	  public String getetat() {
		    return etat;
		  }

		  public void setetat(String etat) {
		    this.etat = etat;
		  }
	  public void Avancementprojet() {

	  }
	  public String getdatefin() {
		    return datefin;
		  }

		  public void setdatefin(String datefin) {
		    this.datefin = datefin;
		  }
	  public String getdatedebut() {
		    return datedebut;
		  }

		  public void setdatedebut(String datedebut) {
		    this.datedebut = datedebut;
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

	  public String getavancement() {
	    return avancement;
	  }
	  public void setavancement(String avancement) {
	    this.avancement = avancement;
	  }

	}