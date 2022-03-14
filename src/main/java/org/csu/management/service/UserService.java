package org.csu.management.service;

import org.csu.management.domain.User;
import org.csu.management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    public boolean updateUserByUsername(User user){
        return userMapper.updateUserByUsername(user);
    }

    public User signin(User user){
        return userMapper.findUserByUsernameAndPassword(user);
    }

    public boolean register(User user){
        return userMapper.insertUserByUsernameAndPassword(user);
    }

    public List<User>searchUserList() {
        return userMapper.searchUserList();
    }

}
