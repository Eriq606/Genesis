import java.sql.Connection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Constantes constantes=new Constantes();
        Langage[] langages=Langage.getAllLangages(constantes);
        Scanner scan=new Scanner(System.in);
        System.out.print("Username: ");
        String username=scan.next();
        System.out.print("Mot de passe: ");
        String pwd=scan.next();
        System.out.print("Base de donnee: ");
        String dbname=scan.next();
        System.out.print("Port: ");
        String port=scan.next();
        System.out.print("Hote de base de donnees: ");
        String host=scan.next();
        System.out.println("SGBD a utiliser:\nPsql\nMysql");
        String sgbd=scan.next();
        System.out.print("Nom de l'entite (table): ");
        String entityName=scan.next();
        System.out.print("Langage:\n>");
        for(int i=1;i<=langages.length;i++){
            System.out.println(i+")"+langages[i-1].getNom());
        }
        int indexLang=scan.nextInt();
        Template temps=langages[indexLang-1].getTemplate(constantes);
        Connection connex=(Connection) Connexion.class.getMethod("get"+sgbd+"Connexion", String.class, String.class, String.class, String.class, String.class).invoke(Connexion.class, dbname, host, port, username, pwd);
        DBEntity dbentity=new DBEntity(sgbd, constantes);
        Entity entity=Entity.getEntity(dbentity, connex, entityName, langages[indexLang-1]);
        try{
            temps.formatStructure(langages[indexLang-1], entity);
            temps.generate(entity, constantes, langages[indexLang-1]);
        }finally{
            scan.close();
            connex.close();
        }
    }
}
