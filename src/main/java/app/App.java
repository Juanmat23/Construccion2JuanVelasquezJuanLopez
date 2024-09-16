package app;

import app.controller.LoginController;
import app.controller.Utils;

public class App {
    public static void main(String[] args) {
        LoginController clubController = new LoginController();
        try {
            clubController.session(null);
        } catch (Exception e) {
            Utils.log("Error: " + e.getMessage());
        }
    }
}
