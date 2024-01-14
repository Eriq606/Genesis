public class InvalidDatabaseException extends Exception{
    private int sgbdId;
    
    public InvalidDatabaseException(int sgbdId) {
        this.sgbdId = sgbdId;
    }
    public int getSgbdId() {
        return sgbdId;
    }
    public void setSgbdId(int sgbdId) {
        this.sgbdId = sgbdId;
    }
    @Override
    public String getMessage() {
        String message="Le SGBD d'ID = %s n'existe pas";
        message=String.format(message, getSgbdId());
        return message;
    }
    
}
