package com.movii.hexagonal.infraestructure.out.mysqldb2.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MysqlHikariDatasource extends HikariDataSource {

    public MysqlHikariDatasource(HikariConfig configuration) {
        super(configuration);
    }

}
