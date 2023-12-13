import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class Entity {
    private String entityName;
    private String packageName;
    private HashMap<String, String> field_type = new HashMap<>();
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public HashMap<String, String> getField_type() {
        return field_type;
    }
    public void setField_type(HashMap<String, String> field_type) {
        this.field_type = field_type;
    }
    public static Entity getEntity(DBEntity dbentity, Connection connex, String entityName, Langage langage) throws SQLException{
        boolean opened=false;
        Connection connect=connex;
        String query="select %s ,%s ,%s ,%s from information_schema.columns where table_name = ?";
        query=String.format(query, dbentity.getParams().get("table-catalog"), dbentity.getParams().get("table-name"), dbentity.getParams().get("column-name"), dbentity.getParams().get("type-name"));
        PreparedStatement statement=connect.prepareStatement(query);
        statement.setString(1, entityName);
        try{
            Entity entity=null;
            try(ResultSet result=statement.executeQuery()){
                for(int i=0;result.next();i++){
                    if(i==0){
                        entity=new Entity();
                        entity.setEntityName(result.getString(2));
                        entity.setPackageName(result.getString(1));
                    }
                    entity.field_type.put(result.getString(3), result.getString(4));
                }
                entity.setField_type(entity.getSimpleTypeCorrespField(dbentity));
                entity.setField_type(entity.getLanguageTypeCorrespField(langage));
            }
            return entity;
        }finally{
            statement.close();
            if(opened){
                connect.close();
            }
        }
    }
    public static String[] getAllEntityNames(DBEntity dbEntity, Connection connex) throws Exception{
        String[] excluded=dbEntity.getAllExcludedTables();
        String query="select distinct %s from information_schema.tables where "+dbEntity.getParams().get("table-type")+"<>'VIEW'";
        for(int i=0;i<excluded.length;i++){
            query+=" and "+dbEntity.getParams().get("table-schema")+"<>'"+excluded[i]+"'";
        }
        query=String.format(query, dbEntity.getParams().get("table-name"));
        PreparedStatement statement=connex.prepareStatement(query);
        try{
            LinkedList<String> liste=new LinkedList<>();
            try(ResultSet result=statement.executeQuery()){
                while(result.next()){
                    liste.add(result.getString(dbEntity.getParams().get("table-name")));
                }
            }
            String[] strings=new String[liste.size()];
            for(int i=0;i<strings.length;i++){
                strings[i]=liste.get(i);
            }
            return strings;
        }finally{   
            statement.close();
        }
    }
    private HashMap<String, String> getSimpleTypeCorrespField(DBEntity dbentity) {
        HashMap<String, String> realField = (HashMap<String, String>) this.getField_type().clone();
        for(Map.Entry<String, String> e : realField.entrySet()) {
            realField.replace(e.getKey(), dbentity.getParams().get(e.getValue()));
        }
        return realField;
    }

    private HashMap<String, String> getLanguageTypeCorrespField(Langage langage) {
        HashMap<String, String> realField = (HashMap<String, String>) this.getField_type().clone();
        for(Map.Entry<String, String> e : realField.entrySet()) {
            realField.replace(e.getKey(), langage.getParams().get(e.getValue()));
        }
        return realField;
    }
}