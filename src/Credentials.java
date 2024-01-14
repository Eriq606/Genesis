import java.io.IOException;

import handyman.Utils;

public class Credentials {
    private String user, password;
    private String host, port;
    private String databaseName;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public void init(String configPath) throws IOException{
        String configContent=Utils.getFileContentFromInsideJar(Credentials.class, configPath);
        Credentials credentials=Utils.fromJson(Credentials.class, configContent);
        setUser(credentials.getUser());
        setPassword(credentials.getPassword());
        setHost(credentials.getHost());
        setPort(credentials.getPort());
        setDatabaseName(credentials.getDatabaseName());
    }
}
