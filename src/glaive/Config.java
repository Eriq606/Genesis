package glaive;
public class Config {
    private String project;
    private String defaultPackage;
    private String classSavepath;
    private String controllerSavepath;
    private String viewpageSavepath;
    public String getViewpageSavepath() {
        return viewpageSavepath;
    }
    public void setViewpageSavepath(String viewpageSavepath) {
        this.viewpageSavepath = viewpageSavepath;
    }
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }
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
