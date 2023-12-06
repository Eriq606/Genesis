import java.io.File;
import java.io.IOException;
import java.util.Map;
import veda.godao.utils.StringUtils;

public class Template {
    private String structure;
    private String attrBehavior;
    public String getStructure() {
        return structure;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }
    public String getAttrBehavior() {
        return attrBehavior;
    }
    public void setAttrBehavior(String attrBehavior) {
        this.attrBehavior = attrBehavior;
    }
    public void formatStructure(Langage langage, Entity entity){
        String struct=getStructure();
        
        for(Map.Entry<String, String> e:langage.getParams().entrySet()){
            struct=struct.replace(e.getKey(), e.getValue());
        }
        struct=struct.replace("class-name", StringUtils.majStart(entity.getEntityName()));
        struct=struct.replace("package-value", entity.getPackageName());
        formatAttrBehavior(entity, langage);
        struct=struct.replace("fields", getAttrBehavior());
        setStructure(struct);
    }
    public void formatAttrBehavior(Entity entity, Langage langage){
        String formattedAttr="";
        for(Map.Entry<String, String> e:entity.getField_type().entrySet()){
            formattedAttr+=getAttrBehavior();
            formattedAttr=formattedAttr.replace("annotation-start", langage.getParams().get("annotation-start"));
            formattedAttr=formattedAttr.replace("annotation-end", langage.getParams().get("annotation-end"));
            formattedAttr=formattedAttr.replace("annotationbracketstart", langage.getParams().get("annotationbracketstart"));
            formattedAttr=formattedAttr.replace("annotationbracketend", langage.getParams().get("annotationbracketend"));
            formattedAttr=formattedAttr.replace("godao-column", langage.getParams().get("godao-column"));
            formattedAttr=formattedAttr.replace("field-encaps", langage.getParams().get("field-encaps"));
            formattedAttr=formattedAttr.replace("getter-encaps", langage.getParams().get("getter-encaps"));
            formattedAttr=formattedAttr.replace("setter-encaps", langage.getParams().get("setter-encaps"));
            formattedAttr=formattedAttr.replace("line-end", langage.getParams().get("line-end"));
            formattedAttr=formattedAttr.replace("function", langage.getParams().get("function"));
            formattedAttr=formattedAttr.replace("get-prefix", langage.getParams().get("get-prefix"));
            formattedAttr=formattedAttr.replace("set-prefix", langage.getParams().get("set-prefix"));
            formattedAttr=formattedAttr.replace("this", langage.getParams().get("this"));
            formattedAttr=formattedAttr.replace("dot", langage.getParams().get("dot"));
            formattedAttr=formattedAttr.replace("bracket-start", langage.getParams().get("bracket-start"));
            formattedAttr=formattedAttr.replace("bracket-end", langage.getParams().get("bracket-end"));
            formattedAttr=formattedAttr.replace("field-type", e.getValue());
            formattedAttr=formattedAttr.replace("field-name", e.getKey());
            formattedAttr=formattedAttr.replace("fieldnamegetter", StringUtils.majStart(e.getKey()));
            formattedAttr+="\n";
        }
        setAttrBehavior(formattedAttr);
    }
    public File generate(Entity entity, Constantes constantes, Langage langage) throws IOException{
        return FileUtils.createFile(".", StringUtils.majStart(entity.getEntityName())+constantes.getConfigs().get(langage.getNom()+"-ext"), getStructure());
    }
}
