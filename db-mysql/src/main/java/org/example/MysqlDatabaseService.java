package org.example;

import org.example.entity.UserEntity;

@Bean(name = "mysql")
public class MysqlDatabaseService implements DatabaseService{
    public void save(UserEntity userEntity) {

        System.out.println(userEntity.toString());
    }
}
