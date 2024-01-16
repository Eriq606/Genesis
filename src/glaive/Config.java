package glaive;
public class Config {
    private String defaultPackage;
    private String classSavepath;
    private String controllerSavepath;
    public String getControllerSavepath() {
        return controllerSavepath;
    }
    public void setControllerSavepath(String controllerSavepath) {
        this.controllerSavepath = controllerSavepath;
    }
    public String getClassSavepath() {
        return classSavepath;
    }
    public void setClassSavepath(String classSavepath) {
        this.classSavepath = classSavepath;
    }
    public String getDefaultPackage() {
        return defaultPackage;
    }
}
