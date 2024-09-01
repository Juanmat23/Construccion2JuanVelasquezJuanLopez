package app.controllers;


import app.service.GuestService;
import app.service.LoginService;

public class GuestController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n 1. Crear invitado \n 2. Borrar invitado \n 9. Volver a menú principal  \n";
    
    private final GuestService guestService = new GuestService();

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
                this.guestService.createGuest();
                return true;
            }
            case "2": {
                this.guestService.deleteGuest();
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
