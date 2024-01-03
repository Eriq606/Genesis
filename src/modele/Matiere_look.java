package poketra;
import veda.godao.annotations.*;
@Table("Matiere_look")
public class Matiere_look{
    @Column("id_look_matiere_look")
    private int id_look_matiere_look;
    public  int getId_look_matiere_look(){
        return this.id_look_matiere_look;
    }
    public  void setId_look_matiere_look(int id_look_matiere_look){
        this.id_look_matiere_look=id_look_matiere_look;
    }

    @Column("id_matiere_look")
    private int id_matiere_look;
    public  int getId_matiere_look(){
        return this.id_matiere_look;
    }
    public  void setId_matiere_look(int id_matiere_look){
        this.id_matiere_look=id_matiere_look;
    }

    @Column("id_matiere_matiere_look")
    private int id_matiere_matiere_look;
    public  int getId_matiere_matiere_look(){
        return this.id_matiere_matiere_look;
    }
    public  void setId_matiere_matiere_look(int id_matiere_matiere_look){
        this.id_matiere_matiere_look=id_matiere_matiere_look;
    }


}
