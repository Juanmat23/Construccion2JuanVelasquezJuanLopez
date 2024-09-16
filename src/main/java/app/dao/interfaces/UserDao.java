package app.dao.interfaces;

import java.util.List;

import app.dto.UserDto;

public interface UserDao {
    public UserDto findByUserName(UserDto userDto) throws Exception;

    public List<UserDto> findUsersByRole(String role) throws Exception;

    public boolean existsByUserName(UserDto userDto) throws Exception;

    public void createUser(UserDto userDto) throws Exception;

}
