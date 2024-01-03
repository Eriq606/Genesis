package poketra;
import veda.godao.annotations.*;
@Table("Look")
public class Look{
    @Column("nom_look")
    private String nom_look;
    public  String getNom_look(){
        return this.nom_look;
    }
    public  void setNom_look(String nom_look){
        this.nom_look=nom_look;
    }

    @Column("id_look")
    private int id_look;
    public  int getId_look(){
        return this.id_look;
    }
    public  void setId_look(int id_look){
        this.id_look=id_look;
    }


}
