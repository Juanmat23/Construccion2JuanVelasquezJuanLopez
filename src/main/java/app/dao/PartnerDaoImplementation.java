package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.helpers.Helper;
import app.model.Partner;

public class PartnerDaoImplementation implements PartnerDao {

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {

        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER(AMOUNT,TYPE,PERSONNID) VALUES (?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, partner.getAmount());
        preparedStatement.setString(2, partner.getType());
        preparedStatement.setLong(3, partner.getUserId().getPersonId().getDocument());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "DELETE FROM PARTNER WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement =
         MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUserId().getPersonId().getDocument());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public PartnerDto getPartner(PartnerDto partnerDto) throws Exception {
        String query = "SELECT * FROM PARTNER WHERE PERSONNID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getUserId().getPersonId().getDocument());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            PartnerDto partnerResult = new PartnerDto();
            partnerResult.setAmount(resulSet.getDouble("AMOUNT"));
            partnerResult.setType(resulSet.getString("TYPE"));
            partnerResult.setUserId(partnerDto.getUserId());

            resulSet.close();
            preparedStatement.close();

            return partnerResult;
        }

        resulSet.close();
        preparedStatement.close();

        return null;
    }

}

