import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

import glaive.Config;
import glaive.Credentials;
import glaive.genesis.Constantes;
import glaive.genesis.DBEntity;
import glaive.genesis.DatabaseInfo;
import glaive.genesis.Entity;
import glaive.genesis.Language;
import handyman.Utils;

public class App {
    public static void generateModels() throws Exception {
        String databases=Utils.getFileContentFromInsideJar(DatabaseInfo.class, Constantes.DATABASEINFO_PATH);
        DatabaseInfo[] databasesInfos=Utils.fromJson(DatabaseInfo[].class, databases);
        int sgbd=0;
        String languageFileContent=Utils.getFileContentFromInsideJar(Language.class, Constantes.LANGUAGE_PATH);
        Language[] languages=Utils.fromJson(Language[].class, languageFileContent);
        int languageID=0;
        String nomEntite="*";
        try(Scanner scanner=new Scanner(System.in)){
            System.out.println("Choisir le SGBD:");
            for(int i=0; i<databasesInfos.length;i++){
                System.out.println((i+1)+") "+databasesInfos[i].getName());
            }
            System.out.print("> ");
            sgbd=scanner.nextInt();
            System.out.println("Choisir le langage:");
            for(int i=0;i<languages.length;i++){
                System.out.println((i+1)+") "+languages[i].getName());
            }
            System.out.print("> ");
            languageID=scanner.nextInt();
            System.out.print("Nom de l'entite[*]:\n>");
            nomEntite=scanner.next().trim();
            nomEntite=nomEntite.isEmpty()?"*":nomEntite; 
        }
        String configFileContent=Utils.getFileContentFromInsideJar(Config.class, Constantes.CONFIG_PATH);
        Config config=Utils.fromJson(Config.class, configFileContent);
        Credentials credentials=new Credentials();
        credentials.init(Constantes.CREDENTIAL_PATH);
        DatabaseInfo databaseInfo=databasesInfos[sgbd-1];
        Language language=languages[languageID-1];
        try(Connection connex=databaseInfo.getConnection(credentials)){
            DBEntity[] dbEntities=databaseInfo.getDBEntities(credentials, nomEntite, connex);
            Entity[] entities=new Entity[dbEntities.length];
            File file;
            for(int i=0;i<entities.length;i++){
                entities[i]=dbEntities[i].getEntity(language, config.getDefaultPackage());
                file=new File(config.getClassSavepath()+"/"+entities[i].getClassName()+language.getExtension());
                Utils.createFile(file.getPath());
                Utils.overwriteFileContent(file.getPath(), entities[i].generateClassFileContent(language, Constantes.TEMPLATE_PATH));
            }
        }
    }
}
