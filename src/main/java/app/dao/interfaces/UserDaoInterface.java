package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.UserDto;
import app.dto.PartnerDto;
import app.dto.GuestDto;

public interface UserDaoInterface {
    public UserDto findByUserName( UserDto userDto ) throws Exception;
    public UserDto findByPersonId( PersonDto personDto ) throws Exception;
    public UserDto findByUserId( PartnerDto partnerDto ) throws Exception;
    public UserDto findByGuestUserId( GuestDto guestDto ) throws Exception;
    public boolean existsByUserName( UserDto userDto ) throws Exception;
    public void createUser( UserDto userDto ) throws Exception;    
    public void updatePasswordUser( UserDto userDto ) throws Exception;    
    public void updateRoleUser( UserDto userDto ) throws Exception;    
    public void deleteUser( UserDto userDto ) throws Exception;    
}
