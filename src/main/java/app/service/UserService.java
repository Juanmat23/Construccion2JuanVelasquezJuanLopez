package app.service;

import java.sql.SQLException;
import app.service.interfaces.UserServiceInterface;
import app.dao.PersonDao;
import app.dao.UserDao;
import app.dao.PartnerDao;

import app.dto.PersonDto;
import app.dto.UserDto;
import app.dto.PartnerDto;

public class UserService implements UserServiceInterface {
    private final PersonService personService = new PersonService();

    private final PersonDao personDao = new PersonDao();
    private final UserDao userDao = new UserDao();
    private final PartnerDao partnerDao = new PartnerDao();

    @Override
    public void createUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = this.personService.createPerson( );
        
        userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto != null ){
            throw new Exception("El usuario para: " + personDto.getName() + " es: " + userDto.getUserName() );
        }
        
        userDto = new UserDto();
        userDto.setPersonId( personDto.getId() );
        
        userDto.getUserNameDto();
        userDto.getUserTypeDto();
        userDto.getUserPasswordDto();
                
        if ( this.userDao.existsByUserName( userDto ) ) {
            throw new Exception("Ya existe un usuario con ese user name");
        }
        
        try {
            this.userDao.createUser( userDto );
        } catch (SQLException e) {
            throw new Exception( e.getMessage() );
        }
    }

    @Override
    public UserDto createUserGuest( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = this.personService.createPerson( );
        
        userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto != null ){
            if ( userDto.getRole().equals( "INVITADO" ) ){
                return userDto;
            }
            else{
                return null;
            }
        }
        
        userDto = new UserDto();
        userDto.setPersonId( personDto.getId() );
        
        userDto.getUserNameDto();
        userDto.setRole( "INVITADO" );
        userDto.getUserPasswordDto();
                
        if ( this.userDao.existsByUserName( userDto ) ) {
            throw new Exception("Ya existe un usuario con ese user name");
        }
        
        try {
            this.userDao.createUser( userDto );
        } catch (SQLException e) {
            throw new Exception( e.getMessage() );
        }
        
        userDto = this.userDao.findByUserName( userDto ) ;
        return userDto;
    }

    @Override
    public void changePasswordUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        personDto = this.personDao.findByDocument( personDto );
        if ( personDto == null ){
            throw new Exception("No se encontró el documento" );            
        }
        
        userDto = this.userDao.findByPersonId( personDto ) ;
        if ( userDto == null ){
            throw new Exception( personDto.getName() + " no tiene usuario" );
        }
        
        System.out.println("Cambiar password al usuario: " + userDto.getUserName() );
        userDto.getUserPasswordDto();
        
        this.userDao.updatePasswordUser( userDto );
    }
    
    @Override
    public void changeRoleUser( UserDto userDto ) throws Exception {
        
    }
    

    @Override
    public void deleteUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        personDto = this.personDao.findByDocument( personDto );
        if ( personDto == null ){
            throw new Exception("La persona no existe" );
        }

        userDto = this.userDao.findByPersonId( personDto ) ;        
        if ( userDto == null ){
            throw new Exception("La persona no tiene usuario" );
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        
        if ( partnerDto != null ){
            throw new Exception("El usuario es socio" );            
        }
        
        System.out.println("Borrar usuario: " + userDto.getUserName() );
        
        this.userDao.deleteUser( userDto );        
    }
    
}

