import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import glaive.Config;
import glaive.Constantes;
import glaive.Credentials;
import glaive.DBEntity;
import glaive.DatabaseInfo;
import glaive.Entity;
import glaive.Language;
import glaive.exodus.Framework;
import glaive.facade.FormEntity;
import glaive.genesis.LanguageFile;
import handyman.Utils;

public class App {
    public static void generateModels(Scanner scanner) throws Exception {
        String databases=Utils.getFileContentFromInsideJar(DatabaseInfo.class, Constantes.DATABASEINFO_PATH);
        DatabaseInfo[] databasesInfos=Utils.fromJson(DatabaseInfo[].class, databases);
        int sgbd=0;
        String languageFileContent=Utils.getFileContentFromInsideJar(Language.class, Constantes.LANGUAGE_PATH);
        Language[] languages=Utils.fromJson(Language[].class, languageFileContent);
        int languageID=0;
        String nomEntite="*";
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
            String fileContent;
            Formatter formatter=new Formatter();
            String additionnalFileName;
            for(int i=0;i<entities.length;i++){
                entities[i]=dbEntities[i].getEntity(language, config.getDefaultPackage());
                file=new File(config.getClassSavepath()+"/"+entities[i].getClassName()+language.getExtension());
                Utils.createFile(file.getPath());
                fileContent=entities[i].generateClassFileContent(language, Constantes.CLASS_TEMPLATE_PATH);
                Utils.overwriteFileContent(file.getPath(), formatter.formatSource(fileContent));
                for(LanguageFile lf:language.getAdditionnalFiles()){
                    additionnalFileName=lf.getName().replace("[class-name-maj]", Utils.majStart(entities[i].getClassName()));
                    file=new File(config.getClassSavepath()+"/"+additionnalFileName);
                    Utils.createFile(file.getPath());
                    fileContent=entities[i].generateAdditionnalFile(lf, config.getDefaultPackage());
                    Utils.overwriteFileContent(file.getPath(), fileContent);
                }
            }
        }
    }
    public static void generateController(Scanner scanner) throws IOException, ClassNotFoundException, SQLException, FormatterException{
        String frameworksFile=Utils.getFileContentFromInsideJar(Framework.class, Constantes.FRAMEWORK_PATH);
        Framework[] frameworks=Utils.fromJson(Framework[].class, frameworksFile);
        String databases=Utils.getFileContentFromInsideJar(DatabaseInfo.class, Constantes.DATABASEINFO_PATH);
        DatabaseInfo[] databasesInfos=Utils.fromJson(DatabaseInfo[].class, databases);
        String languageFileContent=Utils.getFileContentFromInsideJar(Language.class, Constantes.LANGUAGE_PATH);
        Language[] languages=Utils.fromJson(Language[].class, languageFileContent);
        int frameworkId=0;
        int sgbd=0;
        String nomEntite="*";
        System.out.println("Choisir le SGBD:");
        for(int i=0; i<databasesInfos.length;i++){
            System.out.println((i+1)+") "+databasesInfos[i].getName());
        }
        System.out.print("> ");
        sgbd=scanner.nextInt()-1;
        System.out.println("Choisir le framework:");
        for(int i=0;i<frameworks.length;i++){
            System.out.println((i+1)+") "+frameworks[i].getName());
        }
        System.out.print("> ");
        frameworkId=scanner.nextInt()-1;
        System.out.println("Nom de l'entite:");
        System.out.print("> ");
        nomEntite=scanner.next().trim();
        Framework framework=frameworks[frameworkId];
        Credentials credentials=new Credentials();
        credentials.init(Constantes.CREDENTIAL_PATH);
        DatabaseInfo databaseInfo=databasesInfos[sgbd];
        Language language=languages[frameworkId];
        String configFile=Utils.getFileContentFromInsideJar(Config.class, Constantes.CONFIG_PATH);
        Config config=Utils.fromJson(Config.class, configFile);
        try(Connection connex=databaseInfo.getConnection(credentials)){
            DBEntity[] dbentities=databaseInfo.getDBEntities(credentials, nomEntite, connex);
            Entity[] entities=new Entity[dbentities.length];
            File file;
            String fileContent;
            Formatter formatter=new Formatter();
            for(int i=0;i<entities.length;i++){
                entities[i]=dbentities[i].getEntity(language, config.getDefaultPackage());
                fileContent=framework.generateController(entities[i], databaseInfo, credentials, Constantes.CONTROLLER_TEMPLATE_PATH);
                file=new File(config.getControllerSavepath()+"/"+entities[i].getClassName()+framework.getParams().get("controller-suffix")+framework.getExtension());
                Utils.createFile(file.getPath());
                Utils.overwriteFileContent(file.getPath(), formatter.formatSource(fileContent));
            }
        }
    }
    public static void generateViewPage(Scanner scanner) throws IOException, ClassNotFoundException, SQLException, FormatterException{
        String frameworksFile=Utils.getFileContentFromInsideJar(Framework.class, Constantes.FRAMEWORK_PATH);
        Framework[] frameworks=Utils.fromJson(Framework[].class, frameworksFile);
        String databases=Utils.getFileContentFromInsideJar(DatabaseInfo.class, Constantes.DATABASEINFO_PATH);
        DatabaseInfo[] databasesInfos=Utils.fromJson(DatabaseInfo[].class, databases);
        String languageFileContent=Utils.getFileContentFromInsideJar(Language.class, Constantes.LANGUAGE_PATH);
        Language[] languages=Utils.fromJson(Language[].class, languageFileContent);
        int frameworkId=0;
        int sgbd=0;
        String nomEntite="*";
        System.out.println("Choisir le SGBD:");
        for(int i=0; i<databasesInfos.length;i++){
            System.out.println((i+1)+") "+databasesInfos[i].getName());
        }
        System.out.print("> ");
        sgbd=scanner.nextInt()-1;
        System.out.println("Choisir le framework:");
        for(int i=0;i<frameworks.length;i++){
            System.out.println((i+1)+") "+frameworks[i].getName());
        }
        System.out.print("> ");
        frameworkId=scanner.nextInt()-1;
        System.out.println("Nom de l'entite:");
        System.out.print("> ");
        nomEntite=scanner.next().trim();
        Framework framework=frameworks[frameworkId];
        Credentials credentials=new Credentials();
        credentials.init(Constantes.CREDENTIAL_PATH);
        DatabaseInfo databaseInfo=databasesInfos[sgbd];
        Language language=languages[frameworkId];
        String configFile=Utils.getFileContentFromInsideJar(Config.class, Constantes.CONFIG_PATH);
        Config config=Utils.fromJson(Config.class, configFile);
        try(Connection connex=databaseInfo.getConnection(credentials)){
            DBEntity[] dbentities=databaseInfo.getDBEntities(credentials, nomEntite, connex);
            Entity[] entities=new Entity[dbentities.length];
            File file;
            String fileContent;
            FormEntity formEntity=new FormEntity();
            for(int i=0;i<entities.length;i++){
                entities[i]=dbentities[i].getEntity(language, config.getDefaultPackage());
                fileContent=formEntity.generateViewpage(framework, dbentities[i], config.getProject(), config.getDefaultPackage(), Constantes.FACADE_PATH);
                file=new File(config.getViewpageSavepath()+"/"+dbentities[i].getClassName()+framework.getFacadeExtension());
                Utils.createFile(file.getPath());
                Utils.overwriteFileContent(file.getPath(), fileContent);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        try(Scanner scanner=new Scanner(System.in)){
            generateModels(scanner);
            generateController(scanner);
            generateViewPage(scanner);
        }
    }
}
