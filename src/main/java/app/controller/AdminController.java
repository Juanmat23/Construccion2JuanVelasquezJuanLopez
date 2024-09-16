package app.controller;

import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.helpers.Roles;
import app.helpers.Suscription;
import app.service.Service;
import app.service.interfaces.AdminService;

public class AdminController implements ControllerInterface {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;
    private static final String MENU = "ingrese la opcion que desea \n 1. para crear Socio \n 2. listar socios \n 3. para cerrar sesion \n";

    public AdminController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
    }

    @Override
    public void session(UserDto currentUserDto) {
        boolean session = true;
        while (session) {
            session = menu();
        }

    }

    private boolean menu() {
        try {
            Utils.log("bienvenido " + Service.user.getUsername());
            Utils.log(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch (

                Exception e) {
            Utils.log(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createPartner();
                return true;
            }
            case "2": {
                this.listPartners();
                return true;
            }
            case "3": {
                Utils.log("se ha cerrado sesion");
                return false;
            }
            default: {
                Utils.log("ingrese una opcion valida");
                return true;
            }
        }
    }

    private void listPartners() throws Exception {
        this.service.listPartners().forEach(user -> {
            Utils.log(user.getPersonId().getName());
        });
    }

    private void createPartner() throws Exception {
        Utils.log("ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
        personValidator.validName(name);
        Utils.log("ingrese la cedula del socio");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        Utils.log("ingrese el numero del socio");
        long cellPhone = personValidator.validCellPhone(Utils.getReader().nextLine());
        Utils.log("ingrese el nombre de usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);
        Utils.log("ingrese la contraseña del socio");
        String password = Utils.getReader().nextLine();
        userValidator.validPassword(password);

        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(document);
        personDto.setCellPhone(cellPhone);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(userName);
        userDto.setPassword(password);
        userDto.setRole(Roles.getPARTNER());

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        partnerDto.setAmount(0);
        partnerDto.setType(Suscription.getRegular());

        this.service.createPartner(partnerDto);
        Utils.log("se ha creado el usuario exitosamente");
    }

}

