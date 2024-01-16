package glaive;
import java.io.IOException;
import java.util.Map;

import handyman.Utils;

public class Entity {
    private String packageName;
    private String className;
    private EntityField[] fields;
    private DBEntity dbentity;
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public DBEntity getDbentity() {
        return dbentity;
    }
    public void setDbentity(DBEntity dbentity) {
        this.dbentity = dbentity;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public EntityField[] getFields() {
        return fields;
    }
    public void setFields(EntityField[] fields) {
        this.fields = fields;
    }
    public String generateImports(Language langage, String templatePath) throws IOException{
        String importTemplate=langage.getImportTemplate(templatePath);
        String importContent="";
        String importLine;
        for(String s:langage.getImports()){
            importLine=importTemplate;
            importLine=importLine.replace("[import]", langage.getParams().get("import"));
            importLine=importLine.replace("[package-import]", s);
            importLine=importLine.replace("[line-end]", langage.getParams().get("line-end"));
            importContent+=importLine;
        }
        return importContent;
    }
    public String generateClassAnnotations(Language langage, String templatePath) throws IOException{
        String annotationTemplate=langage.getAnnotationTemplate(templatePath);
        String annotationContent="";
        String annotationLine;
        for(String s:langage.getClassAnnotations()){
            annotationLine=annotationTemplate;
            annotationLine=annotationLine.replace("[annotation-start]", langage.getParams().get("annotation-start"));
            annotationLine=annotationLine.replace("[annotation-value]", s);
            annotationLine=annotationLine.replace("[[table-name]]", "\""+getDbentity().getClassName()+"\"");
            annotationLine=annotationLine.replace("[annotation-end]", langage.getParams().get("annotation-end"));
            annotationLine+="\n";
            annotationContent+=annotationLine;
        }
        return annotationContent;
    }
    public String generateFieldAnnotations(Language langage, String templatePath, DBEntityField field) throws IOException{
        String annotationTemplate=langage.getAnnotationTemplate(templatePath);
        String annotationContent="";
        String annotationLine;
        for(String s:langage.getFieldAnnotations()){
            annotationLine=annotationTemplate;
            annotationLine=annotationLine.replace("[annotation-start]", langage.getParams().get("annotation-start"));
            annotationLine=annotationLine.replace("[annotation-value]", s);
            annotationLine=annotationLine.replace("[[column-name]]", "\""+field.getName()+"\"");
            annotationLine=annotationLine.replace("[annotation-end]", langage.getParams().get("annotation-end"));
            annotationLine+="\n";
            annotationContent+=annotationLine;
        }
        if(field.isPrimary()){
            for(String s:langage.getPrimaryFieldAnnotations()){
                annotationLine=annotationTemplate;
                annotationLine=annotationLine.replace("[annotation-start]", langage.getParams().get("annotation-start"));
                annotationLine=annotationLine.replace("[annotation-value]", s);
                annotationLine=annotationLine.replace("[annotation-end]", langage.getParams().get("annotation-end"));
                annotationLine+="\n";
                annotationContent+=annotationLine;
            }
        }
        return annotationContent;
    }
    public String generateField(Language language, String templatePath, EntityField field, DBEntityField dbfield) throws IOException{
        String fieldTemplate=language.getFieldTemplate(templatePath);
        for(Map.Entry<String, String> entry:language.getParams().entrySet()){
            fieldTemplate=fieldTemplate.replace("["+entry.getKey()+"]", entry.getValue());
        }
        fieldTemplate=fieldTemplate.replace("[field-type]", field.getType());
        fieldTemplate=fieldTemplate.replace("[field-name-min]", Utils.minStart(field.getName()));
        fieldTemplate=fieldTemplate.replace("[field-name-maj]", Utils.majStart(field.getName()));
        fieldTemplate=fieldTemplate.replace("[field-annotations]", generateFieldAnnotations(language, templatePath, dbfield));
        fieldTemplate+="\n";
        return fieldTemplate;
    }
    public String generateAllFields(Language language, String templatePath) throws IOException{
        String fieldContent="";
        for(int i=0;i<getFields().length;i++){
            fieldContent+=generateField(language, templatePath, getFields()[i], getDbentity().getFields()[i]);
        }
        return fieldContent;
    }
    public String generateClassFileContent(Language langage, String templatePath) throws IOException{
        String classTemplate=langage.getClassTemplate(templatePath);
        for(Map.Entry<String, String> entry:langage.getParams().entrySet()){
            classTemplate=classTemplate.replace("["+entry.getKey()+"]", entry.getValue());
        }
        classTemplate=classTemplate.replace("[package-value]", getPackageName());
        classTemplate=classTemplate.replace("[class-name-maj]", Utils.majStart(getClassName()));
        classTemplate=classTemplate.replace("[imports]", generateImports(langage, templatePath));
        classTemplate=classTemplate.replace("[class-annotations]", generateClassAnnotations(langage, templatePath));
        classTemplate=classTemplate.replace("[fields]", generateAllFields(langage, templatePath));
        return classTemplate;
    }
}
