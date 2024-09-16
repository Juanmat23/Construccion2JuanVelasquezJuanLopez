package app.controller;

import java.util.HashMap;
import java.util.Map;

import app.controller.validator.UserValidator;
import app.dto.UserDto;
import app.service.Service;

public class LoginController implements ControllerInterface {
    private final UserValidator userValidator;
    private final Service service;
    private static final String MENU = "ingrese la opcion que desea: \n 1. para iniciar sesion. \n 2. para detener la ejecucion.";
    private final Map<String, ControllerInterface> roles;

    public LoginController() {
        this.userValidator = new UserValidator();
        this.service = new Service();
        ControllerInterface partnerController = new PartnerController();
        ControllerInterface guestController = new PartnerController();
        ControllerInterface adminController = new AdminController();
        this.roles = new HashMap<>();

        roles.put("ADMIN", adminController);
        roles.put("PARTNER", partnerController);
        roles.put("GUEST", guestController);
    }

    @Override
    public void session(UserDto userDto) {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            Utils.log(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);
        } catch (Exception e) {
            Utils.log(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.login();
                return true;
            }
            case "2": {
                Utils.log("se detiene el programa");
                return false;
            }
            default: {
                Utils.log("ingrese una opcion valida");
                return true;
            }
        }
    }

    private void login() throws Exception {
        Utils.log("ingrese el usuario");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);

        Utils.log("ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        userValidator.validPassword(password);

        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setUsername(userName);

        this.service.login(userDto);

        if (roles.get(userDto.getRole()) == null) {
            throw new Exception("Rol invalido");
        }
        roles.get(userDto.getRole()).session(userDto);

    }

}
