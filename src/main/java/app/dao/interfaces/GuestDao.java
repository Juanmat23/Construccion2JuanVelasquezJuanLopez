package app.dao.interfaces;

import app.dto.GuestDto;

public interface GuestDao {

    public void createGuest(GuestDto guestDto) throws Exception;

    public void changeStatus(GuestDto guestDto) throws Exception;

    public GuestDto getGuest(GuestDto guestDto) throws Exception;

}
