package org.example;

import org.example.entity.UserEntity;

@Bean(name = "mongo")
public class MongoDatabaseService  implements DatabaseService{
    public void save(UserEntity userEntity) {
        System.out.println(userEntity.toString());

    }
}
