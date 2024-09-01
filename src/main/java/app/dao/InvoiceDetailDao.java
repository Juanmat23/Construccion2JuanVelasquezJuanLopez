package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.InvoiceDetailDaoInterface;

import app.dto.InvoiceDto;
import app.dto.InvoiceDetailDto;
import app.model.InvoiceDetail;

import app.helper.Helper;
import java.util.ArrayList;


public class InvoiceDetailDao implements InvoiceDetailDaoInterface {

    @Override
    public void createInvoiceDetail( InvoiceDetailDto invoiceDetailDto ) throws Exception {
        String query = "INSERT INTO INVOICEDETAIL ( INVOICEID, ITEM, DESCRIPTION, AMOUNT ) VALUES ( ?, ?, ?, ? )";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDetailDto.getInvoiceId() );
        preparedStatement.setLong( 2, invoiceDetailDto.getItemNumber() );
        preparedStatement.setString( 3, invoiceDetailDto.getDescription() );
        preparedStatement.setDouble( 4, invoiceDetailDto.getItemValue() );
        
        preparedStatement.execute();
        preparedStatement.close();                
    }
    
    @Override
    public void deleteInvoiceDetail( InvoiceDto invoiceDto ) throws Exception {
        String query = "DELETE FROM INVOICEDETAIL WHERE INVOICEID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );

        preparedStatement.execute();
        preparedStatement.close();                
    }

    @Override
    public InvoiceDetailDto lastInvoiceDetails( InvoiceDetailDto invoiceDetailDto ) throws Exception {
        String query = "SELECT ID, INVOICEID, ITEM, DESCRIPTION, AMOUNT FROM INVOICEDETAIL WHERE INVOICEID = ? AND ITEM > ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDetailDto.getInvoiceId() );
        preparedStatement.setInt( 2, invoiceDetailDto.getItemNumber() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setId( resulSet.getLong( "ID" ) );
            invoiceDetail.setInvoiceId( resulSet.getLong( "INVOICEID" ) );
            invoiceDetail.setItemNumber( resulSet.getInt( "ITEM" ) );
            invoiceDetail.setDescription( resulSet.getString( "DESCRIPTION" ) );
            invoiceDetail.setItemValue( resulSet.getDouble( "AMOUNT" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoiceDetail);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public double totalInvoiceDetails( InvoiceDto invoiceDto ) throws Exception {
        String query = "SELECT SUM( AMOUNT ) AS AMOUNT FROM INVOICEDETAIL WHERE INVOICEID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            double amount = resulSet.getDouble( "AMOUNT" );

            resulSet.close();
            preparedStatement.close();
            return amount;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

    @Override
    public int countInvoiceDetails( InvoiceDto invoiceDto ) throws Exception {
        String query = "SELECT COUNT( ID ) AS COUNT FROM INVOICEDETAIL WHERE INVOICEID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            int count = resulSet.getInt( "COUNT" );

            resulSet.close();
            preparedStatement.close();
            return count + 1;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 1;
    }

    @Override
    public ArrayList<InvoiceDetailDto> listInvoiceDetails( InvoiceDto invoiceDto ) throws Exception {
        ArrayList<InvoiceDetailDto> listInvoiceDetails = new ArrayList<InvoiceDetailDto>();
        String query = "SELECT ID, INVOICEID, ITEM, DESCRIPTION, AMOUNT FROM INVOICEDETAIL WHERE INVOICEID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();
        while ( resulSet.next() ) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setId( resulSet.getLong( "ID" ) );
            invoiceDetail.setInvoiceId( resulSet.getLong( "INVOICEID" ) );
            invoiceDetail.setItemNumber( resulSet.getInt( "ITEM" ) );
            invoiceDetail.setDescription( resulSet.getString( "DESCRIPTION" ) );
            invoiceDetail.setItemValue( resulSet.getDouble( "AMOUNT" ) );
            
            listInvoiceDetails.add( Helper.parse( invoiceDetail ) );
        }
        resulSet.close();
        preparedStatement.close();
        
        return listInvoiceDetails;
    }
    
}

