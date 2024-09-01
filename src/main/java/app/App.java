package app;

import app.controllers.ControllerInterface;
import app.controllers.LoginController;
import app.controllers.validator.UserValidator;
import app.service.LoginService;
import app.controllers.PersonController;
import app.controllers.UserController;
import app.controllers.PartnerController;
import app.controllers.GuestController;
import app.config.MYSQLConnection;

public class App {
    public static void main(String[] args) throws Exception {
        UserValidator userValidator = new UserValidator();
        LoginService loginService = new LoginService();
        
        ControllerInterface personController = new PersonController();
        ControllerInterface userController = new UserController();
        ControllerInterface guestController = new GuestController();
        ControllerInterface partnerController = new PartnerController(personController, userController, guestController);
        
        ControllerInterface loginController = new LoginController(
            userValidator,
            loginService,
            personController,
            userController,
            partnerController,
            guestController
        );

        try {
            loginController.session();
            MYSQLConnection.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
