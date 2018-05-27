package com.luohaha.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class OBTestProperties {
    private String sql = "";
    private int count = -1;
    private int sqlIntervalTimeMs = -1;//ms
    private String url;
    private String driver = "com.mysql.jdbc.Driver";
    private String user;
    private String password;
    private Properties properties;
    private boolean isInit = false;
    private Logger logger = Logger.getLogger("OBTest");

    public OBTestProperties(String file) {
        try {
            InputStream stream = new FileInputStream(file);
            properties.load(stream);
            generateKV();
            isInit = true;
        } catch (FileNotFoundException e) {
            logger.warning("properties file not found" + e.toString());
        } catch (IOException e2) {
            logger.warning("properties load stream fail" + e2.toString());
        } catch (OBTestPropertiesNotInitException e3) {
            logger.warning("OBTest properties init fail" + e3.toString());
        }
    }

    private void generateKV() throws OBTestPropertiesNotInitException {
        try {
            this.sql = properties.getProperty("sql");
            if (sql.length() == 0) {
                throw new OBTestPropertiesNotInitException();
            }
            this.count = Integer.valueOf(properties.getProperty("sqlCount"));
            this.sqlIntervalTimeMs = Integer.valueOf(properties.getProperty("sqlIntervalTimeMs"));
            if (count < 0 && sqlIntervalTimeMs <= 0) {
                throw new OBTestPropertiesNotInitException();
            }
        } catch (NumberFormatException e) {
            throw new OBTestPropertiesNotInitException();
        }
        this.url = properties.getProperty("url");
        this.password = properties.getProperty("password");
        this.driver = properties.getProperty("driver");
        this.user = properties.getProperty("user");
    }

    public String getSql() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return sql;
    }

    public int getCount() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return count;
    }

    public int getSqlIntervalTimeMs() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return sqlIntervalTimeMs;
    }

    public String getUrl() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return url;
    }

    public String getDriver() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return driver;
    }

    public String getUser() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return user;
    }

    public String getPassword() throws OBTestPropertiesNotInitException {
        if (!isInit) {
            throw new OBTestPropertiesNotInitException();
        }
        return password;
    }
}
