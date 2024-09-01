package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.PartnerDto;

import app.dto.InvoiceDto;
import java.util.ArrayList;

public interface InvoiceDaoInterface {
    public void createInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void updateInvoiceAmount( InvoiceDto invoiceDto ) throws Exception ;
    public void cancelInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void deleteInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public double amountActiveInvoices( PersonDto personDto ) throws Exception ;
    public InvoiceDto firstActiveInvoice( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto firstInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto firstInvoiceByPersonId( PersonDto personDto ) throws Exception ;
    public long lastInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception ;
    public long lastInvoiceByPersonId( PersonDto personDto ) throws Exception ;
    public ArrayList<InvoiceDto> listClubInvoices( ) throws Exception ;
    
}
