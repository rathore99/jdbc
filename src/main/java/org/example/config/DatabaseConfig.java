package org.example.config;

public class DatabaseConfig {

    private final String appName;
    private final String appVersion;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public DatabaseConfig(ConfigLoader loader) {
        this.appName = loader.get("app.name");
        this.appVersion = loader.get("app.version");
        this.dbUrl = loader.get("db.url");
        this.dbUser = loader.get("db.username");
        this.dbPassword = loader.get("db.password");
    }

    public void printConfig() {
        System.out.println("Application: " + appName + " v" + appVersion);
        System.out.println("Database URL: " + dbUrl);
        System.out.println("DB User: " + dbUser);
        // Never log passwords in real applications
    }

    public String getAppName() { return appName; }
    public String getDbUrl() { return dbUrl; }
    public String getDbUser() { return dbUser; }
    public String getDbPassword() { return dbPassword; }
}
