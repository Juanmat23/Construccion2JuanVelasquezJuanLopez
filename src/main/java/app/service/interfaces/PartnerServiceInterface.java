package app.service.interfaces;

import app.dto.UserDto;
import app.dto.PartnerDto;

public interface PartnerServiceInterface {
    public void createPartner( ) throws Exception;
    public void updateAmountPartner( ) throws Exception;
    public void updateTypePartner( PartnerDto partnerDto ) throws Exception;
    public void deletePartner( ) throws Exception;    
    public void deletePartner( UserDto userDto ) throws Exception;    
}
