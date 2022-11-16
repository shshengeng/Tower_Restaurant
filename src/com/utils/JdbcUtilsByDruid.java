package com.utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtilsByDruid {

    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src//druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }


    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
