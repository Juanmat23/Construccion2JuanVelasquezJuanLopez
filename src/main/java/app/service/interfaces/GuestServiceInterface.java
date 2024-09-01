package app.service.interfaces;

import app.dto.UserDto;

public interface GuestServiceInterface {
    public void createGuest( ) throws Exception;
    public void createGuest( UserDto userDto ) throws Exception;
    public void updateGuest( ) throws Exception;
    public void deleteGuest( ) throws Exception;    
    public void changeGuestToPartner( UserDto userDto ) throws Exception;    
}
