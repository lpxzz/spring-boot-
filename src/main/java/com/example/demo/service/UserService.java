package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(cacheNames="userInfo", key="#id")//@CacheEvict(value = "v") 删除缓存
    public Optional<UserEntity> findUser(Long id){
        return userRepository.findById(id);
    }
}
