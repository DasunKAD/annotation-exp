package org.example;

import org.example.entity.UserEntity;

@Bean
public interface DatabaseService {

    void save(UserEntity userEntity);
}
