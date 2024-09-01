package app.service;

import app.dao.PersonDao;
import app.dao.UserDao;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.PersonServiceInterface;

public class PersonService implements PersonServiceInterface {
    private final PersonDao personDao = new PersonDao();
    
    @Override
    public PersonDto createPerson( ) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        if ( this.personDao.existsByDocument( personDto ) ) {
            System.out.println("Ya existe: " + personDto.getName() );
            personDto = this.personDao.findByDocument( personDto );
            return personDto;
        }
        
        personDto.getPersonNameDto();
        personDto.getPersonCellNumberDto();
        
        this.personDao.createPerson( personDto );
        
        personDto = this.personDao.findByDocument( personDto );
        return personDto;
    }

    @Override
    public PersonDto updatePerson( ) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        if ( this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No se encentra el número identificación");
        }
        
        personDto.getPersonCellNumberDto();
        
        this.personDao.updatePerson( personDto );
        
        personDto = this.personDao.findByDocument( personDto );
        return personDto;        
    }

    @Override
    public void deletePerson( ) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        if ( this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No se encentra el número identificación");
        }
        personDto = this.personDao.findByDocument( personDto );
                
        UserDao userDao = new UserDao();
        UserDto userDto = userDao.findByPersonId( personDto );
        if ( userDto != null ){
            throw new Exception("La persona tiene usuario" );
        }
        
        System.out.println("Borrar a: " + personDto.getName() );
        this.personDao.deletePerson( personDto );        
    }
}

