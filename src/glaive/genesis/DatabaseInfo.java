package glaive.genesis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import glaive.Credentials;

public class DatabaseInfo {
    private int id;
    private String name;
    private String driver;
    private boolean useSSL;
    private HashMap<String, String> types;
    private String getcolumnsQuery;
    private String gettablesQuery;
    public String getGettablesQuery() {
        return gettablesQuery;
    }
    public void setGettablesQuery(String gettablesQuery) {
        this.gettablesQuery = gettablesQuery;
    }
    public String getGetcolumnsQuery() {
        return getcolumnsQuery;
    }
    public void setGetcolumnsQuery(String getcolumnsQuery) {
        this.getcolumnsQuery = getcolumnsQuery;
    }
    public HashMap<String, String> getTypes() {
        return types;
    }
    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }
    public boolean isUseSSL() {
        return useSSL;
    }
    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
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
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public Connection getConnection(Credentials credentials) throws ClassNotFoundException, SQLException{
        Class.forName(getDriver());
        Connection connex=DriverManager.getConnection("jdbc:"+getName()+"://"+credentials.getHost()+"/"+credentials.getDatabaseName()+"?useSSL="+isUseSSL(), credentials.getUser(), credentials.getPassword());
        connex.setAutoCommit(false);
        return connex;
    }
    public String[] getAllTables(Credentials credentials, Connection connex) throws SQLException{
        String query=getGettablesQuery().replace("?", "'"+credentials.getDatabaseName()+"'");
        try(PreparedStatement statement=connex.prepareStatement(query)){
            LinkedList<String> liste=new LinkedList<>();
            try(ResultSet result=statement.executeQuery()){
                while(result.next()){
                    liste.add(result.getString("table_name"));
                }
            }
            String[] tableNames=new String[liste.size()];
            for(int i=0;i<tableNames.length;i++){
                tableNames[i]=liste.get(i);
            }
            return tableNames;
        }
    }
    public DBEntity[] getDBEntities(Credentials credentials, String className, Connection connex) throws SQLException, ClassNotFoundException{
        String[] tableNames=className.equals("*")?getAllTables(credentials, connex):new String[]{className};
        DBEntity[] entities=new DBEntity[tableNames.length];
        for(int i=0;i<entities.length;i++){
            entities[i]=new DBEntity(connex, tableNames[i], this);
        }
        return entities;
    }
}
