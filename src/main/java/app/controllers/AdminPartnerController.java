package app.controllers;

import app.service.PartnerService;
import app.service.LoginService;

public class AdminPartnerController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n 1. Crear socio \n 2. Borrar socio \n 9. Volver a menú principal  \n";
    
    private final PartnerService partnerService = new PartnerService();

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
                this.partnerService.createPartner();
                return true;
            }
            case "2": {
                this.partnerService.deletePartner();
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

