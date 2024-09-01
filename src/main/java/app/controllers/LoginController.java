package app.controllers;

import app.controllers.validator.UserValidator;
import app.dto.UserDto;
import app.service.LoginService;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements ControllerInterface {
    private static final String MENU = "Ingrese la opción que desea: \n 1. para iniciar sesión. \n 2. para detener la ejecución.";
    private final UserValidator userValidator;
    private final LoginService serviceLogin;
    private final Map<String, ControllerInterface> roles;

    public LoginController(UserValidator userValidator, LoginService serviceLogin,
                           ControllerInterface personController, ControllerInterface userController,
                           ControllerInterface partnerController, ControllerInterface guestController) {
        this.userValidator = userValidator;
        this.serviceLogin = serviceLogin;

        this.roles = new HashMap<>();
        this.roles.put("ADMIN", personController); 
        this.roles.put("PARTNER", partnerController);
        this.roles.put("GUEST", guestController);
    }

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1":
                this.login();
                return true;
            case "2":
                System.out.println("Se detiene el programa");
                return false;
            default:
                System.out.println("Ingrese una opción válida");
                return true;
        }
    }

    private void login() throws Exception {
        System.out.println("Ingrese el usuario");
        String userName = Utils.getReader().nextLine();
        this.userValidator.validUserName(userName);
        System.out.println("Ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        this.userValidator.validPassword(password);
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setUserName(userName);
        this.serviceLogin.login(userDto);

        ControllerInterface roleController = this.roles.get(userDto.getRole());
        if (roleController == null) {
            throw new Exception("Rol inválido");
        }
        roleController.session();
    }
}
