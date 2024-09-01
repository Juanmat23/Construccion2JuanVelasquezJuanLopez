package app.controllers;

import app.service.LoginService;

public class PartnerController implements ControllerInterface {
    private static final String MENU = "Ingrese la opción que desea \n 1. Solicitar consumo \n 2. Ver historial de consumos \n 3. Crear Invitado \n 4. Cambio a VIP \n 9. Para cerrar sesión \n";
    
    private final ControllerInterface personController;
    private final ControllerInterface userController;
    private final ControllerInterface partnerController;

    public PartnerController(ControllerInterface personController, ControllerInterface userController, ControllerInterface partnerController) {
        this.personController = personController;
        this.userController = userController;
        this.partnerController = partnerController;
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
            case "9":
                System.out.println("Se ha cerrado sesión");
                return false;
            default:
                System.out.println("Ingrese una opción válida");
                return true;
        }
    }
}
