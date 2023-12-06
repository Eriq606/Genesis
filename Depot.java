package banque_presidence;
import veda.godao.*;
@Table("depot")
public class depot{
    @Column("idBanque")
    private int idBanque;
    public  int getIdBanque(){
        return this.idBanque;
    }
    public  void setIdBanque(int idBanque){
        this.idBanque=idBanque;
    }

    @Column("cin")
    private String cin;
    public  String getCin(){
        return this.cin;
    }
    public  void setCin(String cin){
        this.cin=cin;
    }

    @Column("date_depot")
    private java.time.LocalDate date_depot;
    public  java.time.LocalDate getDate_depot(){
        return this.date_depot;
    }
    public  void setDate_depot(java.time.LocalDate date_depot){
        this.date_depot=date_depot;
    }

    @Column("montant")
    private double montant;
    public  double getMontant(){
        return this.montant;
    }
    public  void setMontant(double montant){
        this.montant=montant;
    }

    @Column("id")
    private int id;
    public  int getId(){
        return this.id;
    }
    public  void setId(int id){
        this.id=id;
    }

    @Column("idDevise")
    private int idDevise;
    public  int getIdDevise(){
        return this.idDevise;
    }
    public  void setIdDevise(int idDevise){
        this.idDevise=idDevise;
    }


}
