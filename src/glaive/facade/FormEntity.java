package glaive.facade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

import glaive.Constantes;
import glaive.DBEntity;
import glaive.DBEntityField;
import glaive.exodus.Framework;
import handyman.Utils;

public class FormEntity {
    private HashMap<String, FormFieldEntity> formFields;

    public HashMap<String, FormFieldEntity> getFormFields() {
        return formFields;
    }

    public void setFormFields(HashMap<String, FormFieldEntity> formFields) {
        this.formFields = formFields;
    }
    @SuppressWarnings({"unchecked"})
    public FormEntity() throws IOException {
        String fileContent=Utils.getFileContentFromInsideJar(FormEntity.class, Constantes.FORMFIELD_JSON_PATH);
        HashMap<String, LinkedTreeMap<String, Object>> content=Utils.fromJson(HashMap.class, fileContent);
        HashMap<String, FormFieldEntity> fields=new HashMap<>();
        FormFieldEntity f;
        for(Map.Entry<String, LinkedTreeMap<String, Object>> entry:content.entrySet()){
            f=new FormFieldEntity();
            f.setFile(entry.getValue().get("file").toString());
            f.setType(entry.getValue().get("type").toString());
            f.setStep((double)entry.getValue().get("step"));
            fields.put(entry.getKey(), f); 
        }
        setFormFields(fields);
    }
    public String generateViewpage(Framework framework, DBEntity dbentity, String projectName, String defaultPackage, String templatePath) throws IOException{
        String viewTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getFacadeTemplate()+"/"+framework.getFormwithlistTemplate());
        viewTemplate=viewTemplate.replace("[class-name-maj]", Utils.majStart(Utils.toCamelCase(dbentity.getClassName())));
        viewTemplate=viewTemplate.replace("[default-package]", defaultPackage);
        String listHeaderTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getFacadeTemplate()+"/"+framework.getListHeaderTemplate());
        String listHeaderLine;
        String listHeaderContent="";
        String listElementTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getFacadeTemplate()+"/"+framework.getListElementTemplate());
        String listElementLine;
        String listElementContent="";
        String updateTemplate, insertTemplate;
        String updateContent="", insertContent="";
        for(DBEntityField f:dbentity.getFields()){
            if(f.isPrimary()==false){
                updateTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getFacadeTemplate()+"/"+framework.getUpdateTemplatePath()+"/"+getFormFields().get(f.getType()).getFile());
                updateTemplate=updateTemplate.replace("[field-name-maj]", Utils.toCamelCase(Utils.majStart(f.getName())));
                updateTemplate=updateTemplate.replace("[field-name-min]", Utils.toCamelCase(Utils.minStart(f.getName())));
                updateTemplate=updateTemplate.replace("[inputstep]", String.valueOf(getFormFields().get(f.getType()).getStep()));
                updateTemplate=updateTemplate.replace("[inputtype]", getFormFields().get(f.getType()).getType());
                insertTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getFacadeTemplate()+"/"+framework.getInsertTemplatePath()+"/"+getFormFields().get(f.getType()).getFile());
                insertTemplate=insertTemplate.replace("[field-name-maj]", Utils.toCamelCase(Utils.majStart(f.getName())));
                insertTemplate=insertTemplate.replace("[field-name-min]", Utils.toCamelCase(Utils.minStart(f.getName())));
                insertTemplate=insertTemplate.replace("[inputstep]", String.valueOf(getFormFields().get(f.getType()).getStep()));
                insertTemplate=insertTemplate.replace("[inputtype]", getFormFields().get(f.getType()).getType());
                updateTemplate+="\n";
                insertTemplate+="\n";
                updateContent+=updateTemplate;
                insertContent+=insertTemplate;
            }
            listHeaderLine=listHeaderTemplate;
            listElementLine=listElementTemplate;
            listHeaderLine=listHeaderLine.replace("[headervalue]", Utils.formatReadable(f.getName()));
            listElementLine=listElementLine.replace("[field-name-maj]", Utils.toCamelCase(Utils.majStart(f.getName())));
            listHeaderLine+="\n";
            listElementLine+="\n";
            listHeaderContent+=listHeaderLine;
            listElementContent+=listElementLine;
        }
        viewTemplate=viewTemplate.replace("[header]", listHeaderContent);
        viewTemplate=viewTemplate.replace("[list-element]", listElementContent);
        viewTemplate=viewTemplate.replace("[class-name-min]", Utils.toCamelCase(Utils.minStart(dbentity.getClassName())));
        viewTemplate=viewTemplate.replace("[project-name-maj]", projectName);
        viewTemplate=viewTemplate.replace("[fields-update]", updateContent);
        viewTemplate=viewTemplate.replace("[fields-insert]", insertContent);
        return viewTemplate;
    }
}
