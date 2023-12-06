import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Langage {
    private String nom;
    private HashMap<String, String> params;
    
    public Langage() {
        params=new HashMap<>();
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
    public static Langage[] getAllLangages(Constantes constantes) throws FileNotFoundException{
        String path=constantes.getConfigs().get("language-dir");
        File langdir=new File(path);
        File[] langfiles=langdir.listFiles();
        Langage[] langages=new Langage[langfiles.length];
        for(int i=0;i<langfiles.length;i++){
            langages[i]=new Langage();
            langages[i].setNom(langfiles[i].getName().substring(0, (langfiles[i].getName().length()-constantes.getConfigs().get("language-ext").length())));
            langages[i].setLangageFromFile(langfiles[i]);
        }
        return langages;
    }
    public void setLangageFromFile(File langfile) throws FileNotFoundException{
        Scanner scan=new Scanner(langfile);
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
    public Template getTemplate(Constantes constantes) throws FileNotFoundException{
        String path=constantes.getConfigs().get("template-dir");
        File templdir=new File(path);
        File[] tempFiles=templdir.listFiles();
        Template template=new Template();
        for(int i=0;i<tempFiles.length;i++){
            if(tempFiles[i].getName().substring(0, tempFiles[i].getName().length()-constantes.getConfigs().get("template-ext").length()).equals(getParams().get("template-model"))){
                template.setStructure(FileUtils.getFileContent(tempFiles[i]).replace("#", ""));
            }else if(tempFiles[i].getName().substring(0, tempFiles[i].getName().length()-constantes.getConfigs().get("template-ext").length()).contains(getParams().get("template-model"))){
                template.setAttrBehavior(FileUtils.getFileContent(tempFiles[i]).replace("#", ""));   
            }
        }
        return template;
    }

    public CsControllerTemplate getCsControllerTemplate(Constantes constantes) throws FileNotFoundException{
        String path=constantes.getConfigs().get("template-dir");
        File templdir=new File(path);
        File[] tempFiles=templdir.listFiles();
        CsControllerTemplate template=new CsControllerTemplate();
        for(int i=0;i<tempFiles.length;i++){
            if(tempFiles[i].getName().substring(0, tempFiles[i].getName().length()-constantes.getConfigs().get("template-ext").length()).equals(getParams().get("template-model"))){
                template.setStructure(FileUtils.getFileContent(tempFiles[i]).replace("#", ""));
            }else if(tempFiles[i].getName().substring(0, tempFiles[i].getName().length()-constantes.getConfigs().get("template-ext").length()).contains(getParams().get("template-model"))){
                template.setAttrBehavior(FileUtils.getFileContent(tempFiles[i]).replace("#", ""));   
            }
        }
        return template;
    }
}
