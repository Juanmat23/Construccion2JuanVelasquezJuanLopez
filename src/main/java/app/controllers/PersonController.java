package app.controllers;

import app.service.LoginService;
import app.service.PersonService;

public class PersonController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n 1. Crear persona \n 2. Actualizar persona \n 3. Borrar persona \n 9. Volver a menú principal  \n";
    
    private PersonService personService = new PersonService();

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
                this.personService.createPerson();
                return true;
            }
            case "2": {
                this.personService.updatePerson();
                return true;
            }
            case "3": {
                this.personService.deletePerson();
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

