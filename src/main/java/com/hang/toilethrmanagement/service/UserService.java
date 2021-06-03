package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.UserDTO;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.mapper.TokenMapper;
import com.hang.toilethrmanagement.mapper.UserMapper;
import com.hang.toilethrmanagement.model.Token;
import com.hang.toilethrmanagement.model.User;
import com.hang.toilethrmanagement.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    private TokenMapper tokenMapper;

    @Autowired
    public void setTokenMapper(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Transactional
    public List<UserDTO> getUserDTOList(String query, Integer offset, Integer pageSize) {

        List<User> userList = userMapper.getUserList(query, offset, pageSize);

        List<UserDTO> userDTOList = userList.stream().map(user -> {
            String staffNameById = staffMapper.getStaffNameById(user.getStaffId());

            UserDTO userDTO = new UserDTO(user, staffNameById);

            return userDTO;
        }).collect(Collectors.toList());

        return userDTOList;
    }

    public int countUser(String query) {

        return userMapper.countUser(query);
    }

    @Transactional
    public int addUser(User user) {

        int count = userMapper.duplicateName(user.getUsername());

        if (count > 0) {
            return 0;
        } else {
            return userMapper.addUser(user);
        }
    }

    public int updateUserById(User user) {

        return userMapper.updateUserById(user);
    }

    public int deleteUserById(Integer id) {

        return userMapper.deleteUserById(id);
    }

    @Transactional
    public Token login(User user) {
        List<Integer> login = userMapper.login(user);
        if (login.size() == 0) {
            return null;
        }
        user.setId(login.get(0));

        Token token = TokenUtil.tokenTest(user);
        if (tokenMapper.containUserId(user.getId()) > 0) {
            tokenMapper.updateToken(token);
        } else {
            tokenMapper.generateToken(token);
        }

        return token;
    }

    public int logout(Integer userId) {

        int i = tokenMapper.deleteToken(userId);

        return i;
    }
}
