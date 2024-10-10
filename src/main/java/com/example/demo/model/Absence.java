package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Absences")
public class Absence {
  @Id
  private String id;

  private String titre;
  private String description;
  private String etat;
  private String datedebut;
  private String datefin;
  private String datecreation;

  public Absence() {
  }

  public Absence(String titre, String description, String etat) {
    this.titre = titre;
    this.description = description;
    this.etat = etat;
  }

  public String getId() {
    return id;
  }

  public String getTitre() {
    return titre; // Changer gettitre() en getTitre()
  }

  public String getDatedebut() {
    return datedebut; // Changer getdatedebut() en getDatedebut()
  }

  public String getDatefin() {
    return datefin; // Changer getdatefin() en getDatefin()
  }

  public String getDatecreation() {
    return datecreation; // Changer getdatecreation() en getDatecreation()
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setDatecreation(String datecreation) {
    this.datecreation = datecreation; // Changer setdatecreation() en setDatecreation()
  }

  public void setDatefin(String datefin) {
    this.datefin = datefin; // Changer setdatefin() en setDatefin()
  }

  public void setDatedebut(String datedebut) {
    this.datedebut = datedebut; // Changer setdatedebut() en setDatedebut()
  }

  public void setTitre(String titre) {
    this.titre = titre; // Changer settitre() en setTitre()
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEtat() {
    return etat; // Changer isetat() en getEtat()
  }

  public void setEtat(String etat) {
    this.etat = etat;
  }
}
