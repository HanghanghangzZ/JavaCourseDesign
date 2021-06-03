package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList(String query, Integer offset, Integer pageSize);

    int countUser(String query);

    int addUser(User user);

    int updateUserById(User user);

    int deleteUserById(Integer id);

    List<Integer> login(User user);

    int duplicateName(String username);
}
