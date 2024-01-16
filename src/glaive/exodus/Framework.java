package glaive.exodus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import glaive.Constantes;
import glaive.Credentials;
import glaive.DatabaseInfo;
import glaive.Entity;
import glaive.EntityField;
import handyman.Utils;

public class Framework {
    private int id;
    private String name;
    private HashMap<String, String> params;
    private String[] imports;
    private String[] classAnnotations;
    private FrameworkField[] fields;
    private FrameworkConstructor constructor;
    private FrameworkMethod[] methods;
    private String template;
    private String extension;
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
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
    public String[] getClassAnnotations() {
        return classAnnotations;
    }
    public void setClassAnnotations(String[] classAnnotations) {
        this.classAnnotations = classAnnotations;
    }
    public FrameworkField[] getFields() {
        return fields;
    }
    public void setFields(FrameworkField[] fields) {
        this.fields = fields;
    }
    public FrameworkConstructor getConstructor() {
        return constructor;
    }
    public void setConstructor(FrameworkConstructor constructor) {
        this.constructor = constructor;
    }
    public FrameworkMethod[] getMethods() {
        return methods;
    }
    public void setMethods(FrameworkMethod[] methods) {
        this.methods = methods;
    }
    public String getControllerTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerImportTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.IMPORT_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerFieldTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.FIELD_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerAnnotationTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.ANNOTATION_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerParameterTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.PARAMETER_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerInstanciationTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.INSTANCIATION_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String getControllerMethodTemplate(String templatePath) throws IOException{
        String template=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.METHOD_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        return template;
    }
    public String generateImports(String templatePath) throws IOException{
        String importTemplate=getControllerImportTemplate(templatePath);
        String importContent="";
        String importLine;
        for(String s:getImports()){
            importLine=importTemplate;
            importLine=importLine.replace("[import]", getParams().get("import"));
            importLine=importLine.replace("[package-import]", s);
            importLine=importLine.replace("[line-end]", getParams().get("line-end"));
            importContent+=importLine;
        }
        return importContent;
    }
    public String generateClassAnnotations(String templatePath) throws IOException{
        String annotationTemplate=getControllerAnnotationTemplate(templatePath);
        String annotationContent="";
        String annotationLine;
        for(String s:getClassAnnotations()){
            annotationLine=annotationTemplate;
            annotationLine=annotationLine.replace("[annotation-start]", getParams().get("annotation-start"));
            annotationLine=annotationLine.replace("[annotation-value]", s);
            annotationLine=annotationLine.replace("[annotation-end]", getParams().get("annotation-end"));
            annotationLine+="\n";
            annotationContent+=annotationLine;
        }
        return annotationContent;
    }
    public String generateControllerField(Entity entity, String templatePath) throws IOException{
        String fieldsTemplate=getControllerFieldTemplate(templatePath);
        String fieldsLine;
        String fields="";
        for(int i=0;i<getFields().length;i++){
            fieldsLine=fieldsTemplate;
            fieldsLine=fieldsLine.replace("[field-encaps]", getParams().get("field-encaps"));
            fieldsLine=fieldsLine.replace("[field-type]", getFields()[i].getType());
            fieldsLine=fieldsLine.replace("[field-name]", getFields()[i].getName());
            fieldsLine=fieldsLine.replace("[class-name-maj]", Utils.majStart(entity.getClassName()));
            fieldsLine=fieldsLine.replace("[line-end]", getParams().get("line-end"));
            fieldsLine+="\n";
            fields+=fieldsLine;
        }
        return fields;
    }
    public String generateControllerInstanciation(FrameworkConstructor constructor, String templatePath) throws IOException{
        String instanciationTemplate=getControllerInstanciationTemplate(templatePath);
        String instanciationLine;
        String instanciationContent="";
        for(FrameworkParameter p:constructor.getArguments()){
            instanciationLine=instanciationTemplate;
            instanciationLine=instanciationLine.replace("[self]", getParams().get("self"));
            instanciationLine=instanciationLine.replace("[dot]", getParams().get("dot"));
            instanciationLine=instanciationLine.replace("[field-name]", p.getName());
            instanciationLine=instanciationLine.replace("[equals]", getParams().get("equals"));
            instanciationLine=instanciationLine.replace("[parameter]", p.getName());
            instanciationLine=instanciationLine.replace("[line-end]", getParams().get("line-end"));
            instanciationContent+=instanciationLine+"\n";
        }
        return instanciationContent;
    }
    public String generateParameter(FrameworkParameter parameter, Entity entity, String templatePath) throws IOException{
        String parameterTemplate=getControllerParameterTemplate(templatePath);
        String annotationTemplate=getControllerAnnotationTemplate(templatePath);
        String parameterAnnotationUnit;
        String parameterAnnotations="";
        for(String s:parameter.getAnnotations()){
            parameterAnnotationUnit=annotationTemplate;
            parameterAnnotationUnit=parameterAnnotationUnit.replace("[annotation-start]", getParams().get("annotation-start"));
            parameterAnnotationUnit=parameterAnnotationUnit.replace("[annotation-value]", s);
            parameterAnnotationUnit=parameterAnnotationUnit.replace("[annotation-end]", getParams().get("annotation-end"));
            parameterAnnotations+=parameterAnnotationUnit+" ";
        }
        parameterTemplate=parameterTemplate.replace("[parameter-annotations]", parameterAnnotations);
        parameterTemplate=parameterTemplate.replace("[parameter-type]", parameter.getType());
        parameterTemplate=parameterTemplate.replace("[class-name-maj]", Utils.majStart(entity.getClassName()));
        parameterTemplate=parameterTemplate.replace("[parameter-name]", parameter.getName());
        return parameterTemplate;
    }
    public String generateMethodContent(DatabaseInfo database, Credentials credentials, Entity entity, String template, String templatePath) throws IOException{
        template=template.replace("[database-name]", credentials.getDatabaseName());
        template=template.replace("[host]", credentials.getHost());
        template=template.replace("[port]", credentials.getPort());
        template=template.replace("[user]", credentials.getUser());
        template=template.replace("[password]", credentials.getPassword());
        template=template.replace("[useSSL]", String.valueOf(database.isUseSSL()));
        template=template.replace("[sgbd-id]", String.valueOf(database.getId()));
        template=template.replace("[class-name-maj]", Utils.majStart(entity.getClassName()));
        template=template.replace("[class-name-min]", Utils.minStart(entity.getClassName()));
        String flameworkInstanceTemplate=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.FLAMEWORK_INSTANCE_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        String flameworkInstanciation="";
        String flameworkInstanceLine;
        for(EntityField e:entity.getFields()){
            flameworkInstanceLine=flameworkInstanceTemplate;
            flameworkInstanceLine=flameworkInstanceLine.replace("[field-name-min]", Utils.minStart(e.getName()));
            flameworkInstanceLine=flameworkInstanceLine.replace("[field-name-maj]", Utils.majStart(e.getName()));
            flameworkInstanceLine=flameworkInstanceLine.replace("[field-type]", e.getType());
            flameworkInstanceLine+="\n";
            flameworkInstanciation+=flameworkInstanceLine;
        }
        template=template.replace("[instanciation-flamework]", flameworkInstanciation);
        String springInstanceTemplate=Utils.getFileContentFromInsideJar(Framework.class, templatePath+"/"+getTemplate()+Constantes.SPRING_INSTANCE_TEMPLATE_SUFFIX+Constantes.TEMPLATE_EXTENSION);
        String springInstanciation="";
        String springInstanceLine;
        for(EntityField e:entity.getFields()){
            springInstanceLine=springInstanceTemplate;
            springInstanceLine=springInstanceLine.replace("[field-name-maj]", Utils.majStart(e.getName()));
            springInstanceLine=springInstanceLine.replace("[field-name-min]", Utils.minStart(e.getName()));
            springInstanceLine=springInstanceLine.replace("[class-name-maj]", Utils.majStart(entity.getClassName()));
            springInstanceLine=springInstanceLine.replace("[class-name-min]", Utils.minStart(entity.getClassName()));
            springInstanceLine+="\n";
            springInstanciation+=springInstanceLine;
        }
        template=template.replace("[instanciation-spring]", springInstanciation);
        return template;
    }
    public String generateControllerMethod(FrameworkMethod method, DatabaseInfo database, Credentials credentials, Entity entity, String templatePath) throws IOException{
        String methodTemplate=getControllerMethodTemplate(templatePath);
        String methodAnnotationTemplate=getControllerAnnotationTemplate(templatePath);
        String methodAnnotationUnit;
        String methodAnnotations="";
        for(String s:method.getAnnotations()){
            methodAnnotationUnit=methodAnnotationTemplate;
            methodAnnotationUnit=methodAnnotationUnit.replace("[annotation-start]", getParams().get("annotation-start"));
            methodAnnotationUnit=methodAnnotationUnit.replace("[annotation-value]", s);
            methodAnnotationUnit=methodAnnotationUnit.replace("[annotation-end]", getParams().get("annotation-end"));
            methodAnnotationUnit+="\n";
            methodAnnotations+=methodAnnotationUnit+" ";
        }
        methodTemplate=methodTemplate.replace("[method-annotations]", methodAnnotations);
        methodTemplate=methodTemplate.replace("[method-encaps]", method.getEncaps());
        methodTemplate=methodTemplate.replace("[method-return-type]", method.getReturnType());
        methodTemplate=methodTemplate.replace("[method-name]", method.getName());
        methodTemplate=methodTemplate.replace("[parenthesis-start]", getParams().get("parenthesis-start"));
        String parameters="";
        for(FrameworkParameter p:method.getArguments()){
            parameters+=generateParameter(p, entity, templatePath)+",";
        }
        parameters=parameters.length()>0?parameters.substring(0, parameters.length()-1):parameters;
        methodTemplate=methodTemplate.replace("[method-parameters]", parameters);
        methodTemplate=methodTemplate.replace("[parenthesis-end]", getParams().get("parenthesis-end"));
        String thrownItems="";
        for(String s:method.getThrowItem()){
            thrownItems+=s+", ";
        }
        thrownItems=thrownItems.length()>0?getParams().get("throws")+" "+thrownItems.substring(0, thrownItems.length()-2):thrownItems;
        methodTemplate=methodTemplate.replace("[thrown-items]", thrownItems);
        methodTemplate=methodTemplate.replace("[bracket-start]", getParams().get("bracket-start"));
        methodTemplate=methodTemplate.replace("[method-content]", method.getMethodContent());
        methodTemplate=generateMethodContent(database, credentials, entity, methodTemplate, templatePath);
        methodTemplate=methodTemplate.replace("[bracket-end]", getParams().get("bracket-end"));
        return methodTemplate;
    }
    public String generateController(Entity entity, DatabaseInfo database, Credentials credentials, String templatePath) throws IOException{
        String controller=getControllerTemplate(templatePath);
        for(Map.Entry<String, String> entry:getParams().entrySet()){
            controller=controller.replace("["+entry.getKey()+"]", entry.getValue());
        }
        controller=controller.replace("[imports]", generateImports(templatePath));
        controller=controller.replace("[class-annotations]", generateClassAnnotations(templatePath));
        controller=controller.replace("[package-value]", entity.getPackageName());
        controller=controller.replace("[class-name-maj]", entity.getClassName());
        controller=controller.replace("[fields]", generateControllerField(entity, templatePath));
        String constructorParams="";
        for(FrameworkParameter p:getConstructor().getArguments()){
            constructorParams+=generateParameter(p, entity, templatePath);
            constructorParams+=", ";
        }
        constructorParams=constructorParams.length()>0?constructorParams.substring(0, constructorParams.length()-2):constructorParams;
        controller=controller.replace("[constructor-parameters]", constructorParams);
        controller=controller.replace("[parameters-setup]", generateControllerInstanciation(getConstructor(), templatePath));
        String methods="";
        for(FrameworkMethod m:getMethods()){
            methods+=generateControllerMethod(m, database, credentials, entity, templatePath);
            methods+="\n";
        }
        controller=controller.replace("[methods]", methods);
        return controller;
    }
}
