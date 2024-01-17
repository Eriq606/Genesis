package modeles;

import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("salaire")
public class Salaire {
  @Column("id")
  @PrimaryKey
  private Integer id;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column("datesalaire")
  private java.time.LocalDateTime datesalaire;

  public java.time.LocalDateTime getDatesalaire() {
    return this.datesalaire;
  }

  public void setDatesalaire(java.time.LocalDateTime datesalaire) {
    this.datesalaire = datesalaire;
  }

  @Column("valeur")
  private Double valeur;

  public Double getValeur() {
    return this.valeur;
  }

  public void setValeur(Double valeur) {
    this.valeur = valeur;
  }
}
