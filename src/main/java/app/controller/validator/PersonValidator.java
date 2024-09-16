package app.controller.validator;

public class PersonValidator extends CommonsValidator {

    public PersonValidator() {
        super();
    }

    public void validName(String name) throws Exception {
        super.isValidString("el nombre de la persona ", name);
    }

    public long validDocument(String document) throws Exception {
        return super.isValidLong("la cedula de la persona ", document);
    }

    public long validCellPhone(String cellPhone) throws Exception {
        return super.isValidLong("el numero de la persona", cellPhone);
    }

}
