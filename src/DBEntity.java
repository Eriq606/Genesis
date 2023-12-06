import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DBEntity {
    private String nom;
    private int db;
    private HashMap<String, String> params;
    
    public DBEntity(String nom, Constantes constantes) throws FileNotFoundException {
        this.nom = nom;
        params=new HashMap<>();
        File paramFile=new File(constantes.getConfigs().get("db-dir")+nom+constantes.getConfigs().get("db-ext"));
        Scanner scan=new Scanner(paramFile);
        try{
            while(scan.hasNextLine()){
                String line=scan.nextLine();
                String[] params=line.split("=");
                String paramValue=params[1].substring(1, params[1].length()-1).trim();
                getParams().put(params[0], paramValue);
            }
        }finally{
            scan.close();
        }
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public HashMap<String, String> getParams() {
        return params;
    }
    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    public int getDb() {
        return db;
    }
    public void setDb(int db) {
        this.db = db;
    }
    
}
