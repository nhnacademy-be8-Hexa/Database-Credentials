package com.nhnacademy.screattest.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NativeQueryDatabaseService {

    // 이건 데이터베이스에 접속이 되어서 쿼리수행 여부를 테스트 하기위해서 작성된 코드입니다.

    @Autowired
    private EntityManager entityManager;

    public List<String> getAllTables() {
        String sql = "SHOW TABLES";
        Query query = entityManager.createNativeQuery(sql);

        return query.getResultList();
    }
}
