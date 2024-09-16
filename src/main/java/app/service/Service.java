package app.service;

import java.sql.SQLException;
import java.util.List;

import app.controller.Utils;
import app.dao.GuestDaoImplementation;
import app.dao.PartnerDaoImplementation;
import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.helpers.Roles;
import app.service.interfaces.AdminService;
import app.service.interfaces.PartnerService;

public class Service implements AdminService, PartnerService {
    private UserDao userDao;
    private PersonDao personDao;
    private PartnerDao partnerDao;
    private GuestDao guestDao;

    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
        this.partnerDao = new PartnerDaoImplementation();
        this.guestDao = new GuestDaoImplementation();
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        this.createUser(guestDto.getUserId());
        this.guestDao.createGuest(guestDto);
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        this.createUser(partnerDto.getUserId());
        this.partnerDao.createPartner(partnerDto);

    }

    @Override
    public PartnerDto getPartner(PartnerDto partnerDto) throws Exception {
        return this.partnerDao.getPartner(partnerDto);
    }

    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
        }
        userDto.setRole(validateDto.getRole());
        userDto.setPersonId(validateDto.getPersonId());
        user = validateDto;
    }

    private void createUser(UserDto userDto) throws Exception {
        this.createPerson(userDto.getPersonId());

        PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
        userDto.setPersonId(personDto);
        if (this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese username");
        }

        try {
            this.userDao.createUser(userDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
        }
    }

    private void createPerson(PersonDto personDto) throws Exception {
        if (this.personDao.existsByDocument(personDto)) {
            throw new Exception("ya existe una persona con ese documento");
        }
        this.personDao.createPerson(personDto);
    }

    @Override
    public GuestDto getGuest(GuestDto guestDto) throws Exception {
        return this.guestDao.getGuest(guestDto);
    }

    @Override
    public void changeStatusGuest(GuestDto guestDto) throws Exception {
        this.guestDao.changeStatus(guestDto);
    }

    @Override
    public List<UserDto> listPartners() throws Exception {
        try {
            List<UserDto> users = userDao.findUsersByRole(Roles.getPARTNER());
            for (UserDto userDto : users) {
                PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
                userDto.setPersonId(personDto);
            }

            return users;

        } catch (Exception e) {
            Utils.log(e.getMessage());
            throw new RuntimeException("error al listar los socios");
        }
    }

    @Override
    public List<UserDto> listGuests() throws Exception {
        try {
            List<UserDto> users = userDao.findUsersByRole(Roles.getGUEST());
            for (UserDto userDto : users) {
                PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
                userDto.setPersonId(personDto);
            }

            return users;

        } catch (Exception e) {
            Utils.log(e.getMessage());
            throw new RuntimeException("error al listar los socios");
        }
    }

}

