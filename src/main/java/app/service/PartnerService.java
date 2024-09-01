package app.service;

import app.service.interfaces.PartnerServiceInterface;
import app.dao.PersonDao;
import app.dao.PartnerDao;
import app.dao.InvoiceDao;
import app.dao.InvoiceDetailDao;
import app.dao.UserDao;
import app.dto.InvoiceDto;
import app.dto.PersonDto;
import app.dto.PartnerDto;
import app.dto.UserDto;


public class PartnerService implements PartnerServiceInterface {
    private final PersonDao personDao = new PersonDao();
    private final UserDao userDao = new UserDao();
    private final PartnerDao partnerDao = new PartnerDao();
    private final InvoiceDao invoiceDao = new InvoiceDao();
    private final InvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();

    @Override
    public void createPartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        if ( partnerDto != null ){
            throw new Exception( personDtoLocale.getName() + " ya es SOCIO del club");
        }
        
        partnerDto.setUserId( userDtoLocate.getId() );
        
        partnerDto.getPartnerTypeDto();
        if ( partnerDto.getType().equals( "VIP" ) ){
            long numberVIP = this.partnerDao.numberPertnersVIP();
            if ( numberVIP >= 5 ){
                throw new Exception("Cupo de socios VIP copado");                
            }
        }
        
        partnerDto.getPartnerAmountDto();

        this.partnerDao.createPartner( partnerDto );
    }
    
    @Override
    public void updateAmountPartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario para: " + personDtoLocale.getName() );            
        }
        
        PartnerDto partnerDtoLocale = this.partnerDao.findByUserId( userDtoLocate );
        if ( partnerDtoLocale == null ){
            throw new Exception( personDtoLocale.getName() +  " NO es SOCIO del club");
        }
        
        partnerDtoLocale.setUserId( userDtoLocate.getId() );
        
        partnerDtoLocale.getPartnerAmountIncraseDto();

        PartnerDto partnerDtoDao = this.partnerDao.findByUserId( userDtoLocate );
        
        partnerDtoLocale.setAmount( partnerDtoLocale.getAmount() + partnerDtoDao.getAmount() );
        
        this.partnerDao.updateAmountPartner( partnerDtoLocale );
        
        InvoiceDto invoiceDto = this.invoiceDao.firstActiveInvoice( partnerDtoDao );
        while ( invoiceDto != null){
            partnerDtoDao = this.partnerDao.findByUserId( userDtoLocate );
            if ( partnerDtoDao.getAmount() >= invoiceDto.getAmount() ){
                this.invoiceDao.cancelInvoice( invoiceDto );

                partnerDtoDao.setAmount( partnerDtoDao.getAmount() - invoiceDto.getAmount() );
                this.partnerDao.updateAmountPartner( partnerDtoDao );

                invoiceDto = this.invoiceDao.firstActiveInvoice( partnerDtoDao );
            }
            else {
                invoiceDto = null;
            }
        }
    }

    @Override
    public void deletePartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }
        
        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        
        if ( partnerDto == null ){
            throw new Exception( "No existe el socio");                            
        }
        
        InvoiceDto invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        while ( invoiceDto != null ){
            this.invoiceDetailDao.deleteInvoiceDetail( invoiceDto );
            this.invoiceDao.deleteInvoice( invoiceDto );
            invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        }

        this.partnerDao.deletePartner( partnerDto );
    }    

    @Override
    public void deletePartner( UserDto userDto ) throws Exception {
        PersonDto personDtoLocale = this.personDao.findByUserId( userDto );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        
        if ( partnerDto == null ){
            throw new Exception("No existe el socio");                            
        }
        
        InvoiceDto invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        while ( invoiceDto != null ){
            this.invoiceDetailDao.deleteInvoiceDetail( invoiceDto );
            this.invoiceDao.deleteInvoice( invoiceDto );
            invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        }
        
        this.partnerDao.deletePartner( partnerDto );
    }    

    @Override
    public void updateTypePartner( PartnerDto partnerDto ) throws Exception {
        partnerDto.setType( "VIP" );
        this.partnerDao.updateTypePartner( partnerDto );
    }
}

