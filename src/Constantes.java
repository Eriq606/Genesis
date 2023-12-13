import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Constantes {
    private HashMap<String, String> configs;
    
    public Constantes() throws FileNotFoundException{
        configs=new HashMap<>();
        File conf=new File("data/app.config");
        Scanner scan=new Scanner(conf);
        try{
            String line=null;
            String[] confs=null;
            String confValue=null;
            while(scan.hasNextLine()){
                line=scan.nextLine();
                confs=line.split("=");
                confValue=confs[1].substring(1, confs[1].length()-1).trim();
                configs.put(confs[0], confValue);
            }
        }finally{
            scan.close();
        }
    }

    public HashMap<String, String> getConfigs() {
        return configs;
    }

    public void setConfigs(HashMap<String, String> configs) {
        this.configs = configs;
    }
}
