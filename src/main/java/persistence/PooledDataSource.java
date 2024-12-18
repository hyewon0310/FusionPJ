package main.java.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class PooledDataSource {
    private static BasicDataSource basicDS;

    static {
        try {
            basicDS = new BasicDataSource();
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("src/main/resources/config/db.yml");
            properties.load(inputStream);
            basicDS.setDriverClassName(properties.getProperty("DRIVER_CLASS")); //loads the jdbc driver
            basicDS.setUrl(properties.getProperty("DB_CONNECTION_URL"));
            basicDS.setUsername(properties.getProperty("DB_USER"));
            basicDS.setPassword(properties.getProperty("DB_PWD"));
            // Parameters for connection pooling
            basicDS.setInitialSize(10);
            basicDS.setMaxTotal(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return basicDS;
    }
}

/*
 Connection conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user ="jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String pw = "kwom@9604844";
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("DB연결 성공");
 */