
	
	package com.example.demo.model;


	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "Taches")
	public class Tache {
	  @Id
	  private String id;

	  private String nom;
	  private String description;
	  private String datedebut;
	  private String datefin;
	  private Projet idprojet;
	  private String etat;
	  private String datecreation;
	  private String datemodification;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Projet getIdprojet() {
		return idprojet;
	}
	public void setIdprojet(Projet idprojet) {
		this.idprojet = idprojet;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}
	public String getDatemodification() {
		return datemodification;
	}
	public void setDatemodification(String datemodification) {
		this.datemodification = datemodification;
	}
	public Tache(String id, String nom, String description, String datedebut, String datefin, Projet idprojet,
			String etat, String datecreation, String datemodification) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.idprojet = idprojet;
		this.etat = etat;
		this.datecreation = datecreation;
		this.datemodification = datemodification;
	}
	public Tache() {
		super();
	}

	}