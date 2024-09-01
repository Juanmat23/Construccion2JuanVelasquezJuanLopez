package app.controllers.validator;

public class PartnerValidator extends CommonsValidator{
    public PartnerValidator() {
        super();
    }
    
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("el monto de inversión ", amount);
    }

    public void validType(String tipe) throws Exception {
        super.isValidString("el tipo de socio ", tipe);
    }
}
