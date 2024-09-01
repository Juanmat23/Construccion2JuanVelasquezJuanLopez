package app.service.interfaces;

import app.dto.UserDto;

public interface LoginServiceInterface {
    public void login(UserDto userDto) throws Exception;
    public void logout();    
}
