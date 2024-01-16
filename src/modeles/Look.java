package modeles;

import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("look")
public class Look {
  @Column("datecreation")
  private java.time.LocalDate datecreation;

  public java.time.LocalDate getDatecreation() {
    return this.datecreation;
  }

  public void setDatecreation(java.time.LocalDate datecreation) {
    this.datecreation = datecreation;
  }

  @Column("heurevente")
  private java.time.LocalDateTime heurevente;

  public java.time.LocalDateTime getHeurevente() {
    return this.heurevente;
  }

  public void setHeurevente(java.time.LocalDateTime heurevente) {
    this.heurevente = heurevente;
  }

  @Column("id")
  @PrimaryKey
  private Integer id;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column("nom")
  private String nom;

  public String getNom() {
    return this.nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Column("prix")
  private Double prix;

  public Double getPrix() {
    return this.prix;
  }

  public void setPrix(Double prix) {
    this.prix = prix;
  }
}
