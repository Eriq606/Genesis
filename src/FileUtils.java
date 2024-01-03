import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    public static String getFileContent(File file) throws FileNotFoundException{
        String content="";
        Scanner scan=new Scanner(file);
        try{
            while(scan.hasNextLine()){
                content+=scan.nextLine()+"\n";
            }
            return content;
        }finally{
            scan.close();
        }
    }
    public static File createFile(String path, String fileName, String content) throws IOException {
        File file = new File(path+"/"+fileName);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
            return file;
        }
    }
    public static String toRemoveGodaoTable(String structure, Langage langage){
        String newStruct=structure;
        boolean usingVeda=Boolean.valueOf(langage.getParams().get("using-veda"));
        if(usingVeda==false){
            newStruct=newStruct.replace("##import## ##godao-package####line-end##", "");
            newStruct=newStruct.replace("##annotation-start####godao-table####annotationbracketstart##\"#class-name#\"##annotationbracketend####annotation-end##", "");
        }
        return newStruct;
    }
    public static String toRemoveGodaoColumn(String structure, Langage langage){
        String newStruct=structure;
        boolean usingVeda=Boolean.valueOf(langage.getParams().get("using-veda"));
        if(usingVeda==false){
            newStruct=newStruct.replace("##annotation-start####godao-column####annotationbracketstart###\"field-name\"###annotationbracketend##annotation-end##", "");
        }
        return newStruct;
    }
}
