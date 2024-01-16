package glaive.exodus;

public class FrameworkField {
    private boolean isFinal;
    private String[] annotations;
    private String type;
    private String name;
    public String[] getAnnotations() {
        return annotations;
    }
    public void setAnnotations(String[] annotations) {
        this.annotations = annotations;
    }
    public boolean isFinal() {
        return isFinal;
    }
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
