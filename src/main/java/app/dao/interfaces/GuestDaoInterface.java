package app.dao.interfaces;

import app.dto.UserDto;
import app.dto.GuestDto;

public interface GuestDaoInterface {
    public boolean existsByUserId( UserDto userDto ) throws Exception;
    public void createGuest( GuestDto guestDto ) throws Exception;
    public void deleteGuest( GuestDto guestDto ) throws Exception;
    public void updateGuestStatus( GuestDto guestDto ) throws Exception;
    public GuestDto findByUserId( UserDto userDto ) throws Exception;
}

