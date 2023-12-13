package genesis;
import veda.godao.*;
@Table("Mytable")
public class Mytable{
    @Column("date")
    private java.time.LocalDateTime date;
    public  java.time.LocalDateTime getDate(){
        return this.date;
    }
    public  void setDate(java.time.LocalDateTime date){
        this.date=date;
    }

    @Column("id")
    private int id;
    public  int getId(){
        return this.id;
    }
    public  void setId(int id){
        this.id=id;
    }


}
