package poketra;
import veda.godao.annotations.*;
@Table("Matiere")
public class Matiere{
    @Column("id_matiere")
    private int id_matiere;
    public  int getId_matiere(){
        return this.id_matiere;
    }
    public  void setId_matiere(int id_matiere){
        this.id_matiere=id_matiere;
    }

    @Column("nom_matiere")
    private String nom_matiere;
    public  String getNom_matiere(){
        return this.nom_matiere;
    }
    public  void setNom_matiere(String nom_matiere){
        this.nom_matiere=nom_matiere;
    }


}
