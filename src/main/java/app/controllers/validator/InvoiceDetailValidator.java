package app.controllers.validator;

public class InvoiceDetailValidator extends CommonsValidator{
    public InvoiceDetailValidator() {
        super();
    }
    
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("el monto del detalle ", amount);
    }

    public void validDescription(String description) throws Exception {
        super.isValidString("la descripción ", description);
    }
}
