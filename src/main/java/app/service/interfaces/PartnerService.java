package app.service.interfaces;

import java.util.List;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;

public interface PartnerService {
    public void createGuest(GuestDto guestDto) throws Exception;

    public PartnerDto getPartner(PartnerDto partnerDto) throws Exception;

    public List<UserDto> listGuests() throws Exception;

    public void changeStatusGuest(GuestDto guestDto) throws Exception;

    public GuestDto getGuest(GuestDto guestDto) throws Exception;

}
