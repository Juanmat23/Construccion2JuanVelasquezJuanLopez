package app.helpers;

public class Roles {
    private static final String ADMIN = "admin";
    private static final String PARTNER = "partner";
    private static final String GUEST = "guest";

    public static String getADMIN() {
        return ADMIN;
    }

    public static String getPARTNER() {
        return PARTNER;
    }

    public static String getGUEST() {
        return GUEST;
    }
}
