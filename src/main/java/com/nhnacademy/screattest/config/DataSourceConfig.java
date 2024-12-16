package com.nhnacademy.screattest.config;

import com.nhnacademy.screattest.credentials.DatabaseCredentials;
import com.nhnacademy.screattest.service.SecureKeyManagerService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final  SecureKeyManagerService secureKeyManagerService;

    public DataSourceConfig(SecureKeyManagerService secureKeyManagerService) {
        this.secureKeyManagerService = secureKeyManagerService;
    }

    @Bean
    public DataSource dataSource(){

        String databaseInfo = secureKeyManagerService.fetchSecretFromKeyManager();
        DatabaseCredentials databaseCredentials = new DatabaseCredentials(databaseInfo);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseCredentials.getUrl());
        dataSource.setUsername(databaseCredentials.getUsername());
        dataSource.setPassword(databaseCredentials.getPassword());

        // 톰캣 기본 설정과 일치시키는 Connection Pool 설정

        // 커넥션 풀이 초기화될 때, 미리 생성될 커넥션 수
        // 보통 10~50개 정도 사용하고 예상 트래픽이 높으면 늘리는 식으로
        dataSource.setInitialSize(20);

        // 커넥션 풀이 동시에 관리할 수 있는 최대 커넥션 수
        // 보통 50 ~ 200 정도로 설정 , 너무 큰 값을 설정하면 DB 서버의 부하가 커질 수 있음
        dataSource.setMaxTotal(100);

        // 커넥션 풀이 유지해야 하는 최소 유휴 커넥션 수
        // 트래픽이 적을 때도 일정 수의 커넥션을 유지하도록 설정
        // 보통 10 ~ 30 사이로 설정
        dataSource.setMinIdle(20);

        // 커넥션 풀이 유지할 수 있는 최대 유휴 커넥션 수
        // 이 값은 시스템의 자원 낭비를 방지하는 데 중요합니다. 일반적으로 minIdle 값과 비슷하게 설정
        dataSource.setMaxIdle(60);

        // Connection 유효성 검사를 위한 설정
        dataSource.setTestOnBorrow(true);    // 커넥션 획득 전 테스트 (톰캣 기본값: true)

        dataSource.setValidationQuery("SELECT 1"); // 커넥션 유효성 검사 쿼리


        return dataSource;
    }
}
