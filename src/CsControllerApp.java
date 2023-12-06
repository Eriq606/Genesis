public class CsControllerApp {
    public static void main(String[] args) throws Exception {
        Constantes constantes=new Constantes("data/csControllerApp.config");
        Langage[] langages=Langage.getAllLangages(constantes);
        Langage cs = langages[0];
        String nameController = "voiture";
        CsControllerTemplate temps=cs.getCsControllerTemplate(constantes);
        try{
            temps.formatStructure(nameController, cs);
            temps.generate(nameController, constantes, cs);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
