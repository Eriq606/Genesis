package glaive.exodus;

public class FrameworkMethod {
    private String name;
    private String returnType;
    private String encaps;
    private FrameworkParameter[] arguments;
    private String[] annotations;
    private String methodContent;
    private String[] throwItem;
    public String[] getThrowItem() {
        return throwItem;
    }
    public void setThrowItem(String[] throwItem) {
        this.throwItem = throwItem;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReturnType() {
        return returnType;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public String getEncaps() {
        return encaps;
    }
    public void setEncaps(String encaps) {
        this.encaps = encaps;
    }
    public FrameworkParameter[] getArguments() {
        return arguments;
    }
    public void setArguments(FrameworkParameter[] arguments) {
        this.arguments = arguments;
    }
    public String[] getAnnotations() {
        return annotations;
    }
    public void setAnnotations(String[] annotations) {
        this.annotations = annotations;
    }
    public String getMethodContent() {
        return methodContent;
    }
    public void setMethodContent(String methodContent) {
        this.methodContent = methodContent;
    }
    
}
