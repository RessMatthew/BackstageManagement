package org.csu.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.management.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
     User findUserByUsername(String username);
     boolean updateUserByUsername(User user);
     User findUserByUsernameAndPassword(User user);
     boolean insertUserByUsernameAndPassword(User user);
     List<User>searchUserList();
}
