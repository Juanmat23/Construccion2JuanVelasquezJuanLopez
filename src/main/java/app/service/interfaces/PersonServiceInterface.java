package app.service.interfaces;

import app.dto.PersonDto;

public interface PersonServiceInterface {
    public PersonDto createPerson( ) throws Exception;    
    public PersonDto updatePerson( ) throws Exception;    
    public void deletePerson( ) throws Exception;    
}
