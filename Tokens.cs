namespace voiture;
using veda.godao.*;
[Table("tokens")]
public class tokens{
    [Column("id_token")]
    private int id_token;
    public  int getId_token(){
        return this.id_token;
    }
    public  void setId_token(int id_token){
        this.id_token=id_token;
    }

    [Column("expiration_token")]
    private DateTime expiration_token;
    public  DateTime getExpiration_token(){
        return this.expiration_token;
    }
    public  void setExpiration_token(DateTime expiration_token){
        this.expiration_token=expiration_token;
    }

    [Column("value_token")]
    private string value_token;
    public  string getValue_token(){
        return this.value_token;
    }
    public  void setValue_token(string value_token){
        this.value_token=value_token;
    }

    [Column("id_user_token")]
    private int id_user_token;
    public  int getId_user_token(){
        return this.id_user_token;
    }
    public  void setId_user_token(int id_user_token){
        this.id_user_token=id_user_token;
    }


}
