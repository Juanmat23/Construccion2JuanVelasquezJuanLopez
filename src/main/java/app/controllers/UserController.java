package app.controllers;

import app.service.UserService;
import app.service.LoginService;

public class UserController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n 1. CREAR usuario \n 2. Cambiar password a usuario \n 3. BORRAR un usuario \n 9. Volver a menú principal \n";
    
    private UserService userService = new UserService();
    
    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println("bienvenido " + LoginService.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception{
        switch (option) {
            case "1": {
                this.userService.createUser();
                return true;
            }
            case "2": {
                this.userService.changePasswordUser();
                return true;
            }
            case "3": {
                this.userService.deleteUser();
                return true;
            }
            case "9": {
                System.out.println("Se ha cerrado sesion");
                return false;
            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }
}

