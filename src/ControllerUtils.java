import java.io.IOException;
import exodus.Constantes;
import exodus.CsControllerTemplate;
import exodus.FlameworkTemplate;
import exodus.SpringControllerTemplate;
import exodus.Langage;
import exodus.Template;
import exodus.VanillaTemplate;
public class ControllerUtils {
    public static void generateController(String nomController, int modele) throws IOException{
        Template temps = null;
        String configPath;
        Constantes constantes = null;
        Constantes constGlobales=new Constantes("data_exodus/app.config");
        Langage[] langages;
        Langage langage = null;
        switch (modele) {
            case 1:
                configPath = "data_exodus/springControllerApp.config";
                constantes = new Constantes(configPath);
                langages = Langage.getAllLangages(constantes);
                langage = Langage.getLangageByName(langages, "java");
                assert langage != null;
                temps = langage.getSpringControllerTemplate(constantes);
                break;
            case 2:
                configPath = "data_exodus/flameworkController.config";
                constantes = new Constantes(configPath);
                langages = Langage.getAllLangages(constantes);
                langage = Langage.getLangageByName(langages, "java");
                assert langage != null;
                temps = langage.getFlameworkTemplate(constantes);
                break;
            case 3:
                configPath = "data_exodus/csControllerApp.config";
                constantes = new Constantes(configPath);
                langages = Langage.getAllLangages(constantes);
                langage = Langage.getLangageByName(langages, "csharp");
                assert langage != null;
                temps = langage.getCsControllerTemplate(constantes);
                break;
            case 4:
                VanillaTemplate template=new VanillaTemplate();
                String content=template.constructController(constGlobales);
                FileUtils.createFile(".", template.getControllerName()+constGlobales.getConfigs().get("servletfile-suffix"), content);
                return;
        }
        if (temps instanceof CsControllerTemplate) {
            ((CsControllerTemplate) temps).formatStructure(nomController, langage);
            ((CsControllerTemplate) temps).generate(nomController, constantes, langage);
        } else if (temps instanceof FlameworkTemplate) {
            ((FlameworkTemplate) temps).formatStructure(nomController, langage);
            ((FlameworkTemplate) temps).generate(nomController, constantes, langage);
        } else if (temps != null) {
            ((SpringControllerTemplate) temps).formatStructure(nomController, langage);
            ((SpringControllerTemplate) temps).generate(nomController, constantes, langage);
        }
    }
}
