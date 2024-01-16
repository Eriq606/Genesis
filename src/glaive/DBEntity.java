package glaive;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import handyman.Utils;

public class DBEntity {
    private String className;
    private DBEntityField[] fields;
    public DBEntityField[] getFields() {
        return fields;
    }
    public void setFields(DBEntityField[] fields) {
        this.fields = fields;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public DBEntity(Connection connex, String className, DatabaseInfo databaseInfo) throws SQLException, ClassNotFoundException {
        this.className = className;
        PreparedStatement statement=connex.prepareStatement(databaseInfo.getGetcolumnsQuery());
        statement.setString(1, className);
        try{
            try(ResultSet result=statement.executeQuery()){
                DBEntityField field;
                LinkedList<DBEntityField> liste=new LinkedList<>();
                String constraint;
                while(result.next()){
                    field=new DBEntityField();
                    field.setName(result.getString("column_name"));
                    field.setType(databaseInfo.getTypes().get(result.getString("data_type")));
                    constraint=result.getString("constraint_type");
                    constraint=constraint==null?"":constraint;
                    field.setPrimary(constraint.equals("PRI"));
                    liste.add(field);
                }
                DBEntityField[] fields=new DBEntityField[liste.size()];
                for(int i=0;i<fields.length;i++){
                    fields[i]=liste.get(i);
                }
                setFields(fields);
            }
        }finally{
            statement.close();
        }
    }
    public Entity getEntity(Language langage, String packageName){
        Entity entity=new Entity();
        entity.setPackageName(packageName);
        entity.setClassName(Utils.majStart(Utils.toCamelCase(getClassName())));
        entity.setDbentity(this);
        EntityField[] fields=new EntityField[getFields().length];
        for(int i=0;i<fields.length;i++){
            fields[i]=new EntityField();
            fields[i].setName(Utils.toCamelCase(getFields()[i].getName()));
            fields[i].setType(langage.getTypes().get(getFields()[i].getType()));
            fields[i].setPrimary(getFields()[i].isPrimary());
        }
        entity.setFields(fields);
        return entity;
    }
}
