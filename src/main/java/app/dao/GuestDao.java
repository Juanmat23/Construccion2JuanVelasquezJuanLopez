package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.helper.Helper;
import app.dao.interfaces.GuestDaoInterface;

import app.dto.UserDto;
import app.dto.GuestDto;
import app.model.Guest;

public class GuestDao implements GuestDaoInterface{

    @Override
    public boolean existsByUserId(UserDto userDto) throws Exception {
        String query = "SELECT ID, USERID, PARTNERID, STATUS FROM GUEST WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( userDto.getId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        String query = "INSERT INTO GUEST( USERID, PARTNERID, STATUS ) VALUES ( ?, ?, ? ) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, guestDto.getUserId() );
        preparedStatement.setLong( 2, guestDto.getPartnerId() );
        preparedStatement.setString( 3, guestDto.getStatus() );

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateGuestStatus( GuestDto guestDto ) throws Exception {
        String query = "UPDATE GUEST SET STATUS = ? WHERE ID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, guestDto.getStatus() );
        preparedStatement.setLong( 2, guestDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteGuest( GuestDto guestDto ) throws Exception {
        String query = "DELETE FROM GUEST WHERE ID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, guestDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public GuestDto findByUserId( UserDto userDto ) throws Exception {        
        String query = "SELECT ID, USERID, PARTNERID, STATUS FROM GUEST WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( userDto.getId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Guest guest = new Guest();
            guest.setId( resulSet.getLong("ID") );
            guest.setUserId( resulSet.getLong("USERID") );
            guest.setPartnerId( resulSet.getLong( "PARTNERID") );
            guest.setStatus( resulSet.getString( "STATUS") );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(guest);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }
}
