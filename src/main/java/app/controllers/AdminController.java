package app.controllers;

import app.service.LoginService;

public class AdminController implements ControllerInterface {
    private static final String MENU = "Ingrese la opción que desea \n 1. PERSONAS \n 2. USUARIOS \n 3. SOCIOS \n 4. INVITADOS \n 9. Para cerrar sesión \n";

    private final ControllerInterface personController;
    private final ControllerInterface userController;
    private final ControllerInterface partnerController;
    private final ControllerInterface guestController;

   
    public AdminController(ControllerInterface personController, ControllerInterface userController,
                      ControllerInterface partnerController, ControllerInterface guestController) {
        this.personController = personController;
        this.userController = userController;
        this.partnerController = partnerController;
        this.guestController = guestController;
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
            System.out.println("Bienvenido " + LoginService.user.getUserName());
            System.out.print(MENU);
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
                this.personController.session();
                return true;
            case "2":
                this.userController.session();
                return true;
            case "3":
                this.partnerController.session();
                return true;
            case "4":
                this.guestController.session();
                return true;
            case "9":
                System.out.println("Se ha cerrado sesión");
                return false;
            default:
                System.out.println("Ingrese una opción válida");
                return true;
        }
    }
}
