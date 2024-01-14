package glaive.genesis;
public class DBEntityField {
    private String name;
    private String type;
    private boolean isPrimary;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isPrimary() {
        return isPrimary;
    }
    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    
}
