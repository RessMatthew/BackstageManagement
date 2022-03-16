package org.csu.management.service;

import org.csu.management.domain.User;
import org.csu.management.mapper.UserMapper;
import org.csu.management.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //MD5 加密所用的盐
    private static final String salt="1a2b3c4d";

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    public boolean updateUserByUsername(User user){
        String inputPassword = user.getPassword();
        String MD5Password = MD5Util.inputPassToDBPass(inputPassword,salt);
        System.out.println("*******************************************");
        System.out.println("输入密码： "+inputPassword+"  MD5加密后： "+MD5Password);
        System.out.println("*******************************************");
        user.setPassword(MD5Password);
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
