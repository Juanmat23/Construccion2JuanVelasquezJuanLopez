package app.dto;

import app.dto.interfaces.InvoiceDetailDtoInterface;

import app.controllers.Utils;
import app.controllers.validator.InvoiceDetailValidator;


public class InvoiceDetailDto implements InvoiceDetailDtoInterface{
    private final InvoiceDetailValidator invoiceDetailValidator = new InvoiceDetailValidator();

    private long id;
    private long invoiceId;
    private int itemNumber;
    private String description;
    private double itemValue;

    @Override
    public void getInvoiceDetailDescriptionDto() throws Exception {
        System.out.println("Ingrese la descrpción del detalle");
        String invoiceDetailDesctiptionDto = Utils.getReader().nextLine();
        this.invoiceDetailValidator.validDescription( invoiceDetailDesctiptionDto );
        this.description = invoiceDetailDesctiptionDto ;
    }

    @Override
    public void getInvoiceDetailAmountDto() throws Exception {
        System.out.println("Ingrese el monto del detalle");
        String invoiceDetailAmountDto = Utils.getReader().nextLine();
        this.itemValue = this.invoiceDetailValidator.validAmount( invoiceDetailAmountDto ) ;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getItemValue() {
        return itemValue;
    }

    public void setItemValue(double itemValue) {
        this.itemValue = itemValue;
    }

    

}
