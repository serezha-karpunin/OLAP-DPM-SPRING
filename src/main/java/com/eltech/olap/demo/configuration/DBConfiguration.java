package com.eltech.olap.demo.configuration;

import org.olap4j.OlapConnection;
import org.olap4j.OlapWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConfiguration {

    private String driverClassName = "mondrian.olap4j.MondrianOlap4jDriver";

    @Bean
    public OlapConnection olapConnection() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection =
                    DriverManager.getConnection(
                            "jdbc:mondrian:"
                                    + "Jdbc='jdbc:mysql://localhost/mdp?user=root&password=root';"
                                    + "Catalog='file://C:/Users/Admin/Desktop/diploma/dpm/src/main/resources/Diploma.xml';"
                                    + "JdbcDrivers=com.mysql.jdbc.Driver;");
            OlapWrapper olapWrapper = (OlapWrapper) connection;
            return olapWrapper.unwrap(OlapConnection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
