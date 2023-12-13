namespace solaire;
using veda.godao.*;
[Table("Coupures")]
public class Coupures{
    [Column("id")]
    private int id;
    public  int getId(){
        return this.id;
    }
    public  void setId(int id){
        this.id=id;
    }

    [Column("idsource")]
    private int idsource;
    public  int getIdsource(){
        return this.idsource;
    }
    public  void setIdsource(int idsource){
        this.idsource=idsource;
    }

    [Column("DateOnlyheure")]
    private DateTime DateOnlyheure;
    public  DateTime getDateheure(){
        return this.DateOnlyheure;
    }
    public  void setDateheure(DateTime DateOnlyheure){
        this.DateOnlyheure=DateOnlyheure;
    }


}
