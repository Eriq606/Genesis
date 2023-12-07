package energie_solaire;
import veda.godao.*;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;

@Table("Batteries")
public class Batteries{
    @Column("puissancew")
    private double puissancew;
    public  double getPuissancew(){
        return this.puissancew;
    }
    public  void setPuissancew(double puissancew){
        this.puissancew=puissancew;
    }

    @Column("id")
    private int id;
    public  int getId(){
        return this.id;
    }
    public  void setId(int id){
        this.id=id;
    }

    @Column("chargewh")
    private double chargewh;
    public  double getChargewh(){
        return this.chargewh;
    }
    public  void setChargewh(double chargewh){
        this.chargewh=chargewh;
    }

    @Column("nom")
    private String nom;
    public  String getNom(){
        return this.nom;
    }
    public  void setNom(String nom){
        this.nom=nom;
    }


}
