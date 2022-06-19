package dev.spring.p10walletapp.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "h2Datasource")
    @Primary
    public DataSource h2Datasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }


    @Bean(name = "mysqlDatasource")
    public DataSource mysqlDatasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/ooptest1?characterEncoding=UTF8");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("Passw0rd");
        return dataSourceBuilder.build();
    }
}
