package modeles;

import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("look")
public class Look {
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

  @Column("heuretravail")
  private Double heuretravail;

  public Double getHeuretravail() {
    return this.heuretravail;
  }

  public void setHeuretravail(Double heuretravail) {
    this.heuretravail = heuretravail;
  }
}
