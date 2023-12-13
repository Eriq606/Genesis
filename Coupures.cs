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

    [Column("dateheure")]
    private DateTime dateheure;
    public  DateTime getDateheure(){
        return this.dateheure;
    }
    public  void setDateheure(DateTime dateheure){
        this.dateheure=dateheure;
    }


}
