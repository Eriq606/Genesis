package glaive.genesis;
import java.io.IOException;
import java.util.HashMap;

import handyman.Utils;

public class Language {
    private int id;
    private String name;
    private HashMap<String, String> params;
    private String[] imports;
    private HashMap<String, String> types;
    private String[] classAnnotations, fieldAnnotations, primaryFieldAnnotations;
    private String template;
    private String extension;
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String[] getClassAnnotations() {
        return classAnnotations;
    }
    public void setClassAnnotations(String[] classAnnotations) {
        this.classAnnotations = classAnnotations;
    }
    public String[] getFieldAnnotations() {
        return fieldAnnotations;
    }
    public void setFieldAnnotations(String[] fieldAnnotations) {
        this.fieldAnnotations = fieldAnnotations;
    }
    public String[] getPrimaryFieldAnnotations() {
        return primaryFieldAnnotations;
    }
    public void setPrimaryFieldAnnotations(String[] primaryFieldAnnotations) {
        this.primaryFieldAnnotations = primaryFieldAnnotations;
    }
    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }
    public HashMap<String, String> getTypes() {
        return types;
    }
    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HashMap<String, String> getParams() {
        return params;
    }
    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    public String[] getImports() {
        return imports;
    }
    public void setImports(String[] imports) {
        this.imports = imports;
    }
    public String getClassTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Language.class, templatePath+"/"+getTemplate()+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getImportTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Language.class, templatePath+"/"+getTemplate()+Constantes.IMPORT_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getAnnotationTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Language.class, templatePath+"/"+getTemplate()+Constantes.ANNOTATION_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getFieldTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Language.class, templatePath+"/"+getTemplate()+Constantes.FIELD_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
}
