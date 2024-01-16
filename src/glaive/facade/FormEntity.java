package glaive.facade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

import glaive.Constantes;
import glaive.DBEntity;
import glaive.Entity;
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
    public String generateViewpage(Framework framework, DBEntity dbentity, String templatePath) throws IOException{
        String viewTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getTemplate()+"/"+framework.getFormwithlistTemplate());
        viewTemplate=viewTemplate.replace("[class-name-maj]", Utils.majStart(Utils.toCamelCase(dbentity.getClassName())));
        String listHeaderTemplate=Utils.getFileContentFromInsideJar(FormEntity.class, templatePath+"/"+framework.getTemplate()+"/"+framework.getListHeaderTemplate());
        String listHeaderLine;
        String listHeaderContent="";
        
    }
}
