package org.example;

import org.example.core.SpringContext;
import org.example.entity.UserEntity;

public class Main {
    public static void main(String[] args) {
        DatabaseService bean = SpringContext.getBean(MongoDatabaseService.class, "mongo");
        DatabaseService bean2 = SpringContext.getBean(MysqlDatabaseService.class, "mysql");
        bean.save(new UserEntity("dasun", "Bandaragama", 10));
        bean2.save(new UserEntity("dasun2", "Bandaragama2", 102));
        System.out.println("Hello world!");
    }
}