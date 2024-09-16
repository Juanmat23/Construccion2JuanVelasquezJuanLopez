package app.service.interfaces;

import java.util.List;

import app.dto.PartnerDto;
import app.dto.UserDto;

public interface AdminService {
    public void createPartner(PartnerDto partnerDto) throws Exception;

    public List<UserDto> listPartners() throws Exception;

}