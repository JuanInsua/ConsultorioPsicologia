package Exeption;

public class DniExeption extends RuntimeException{
    private String message;
    public DniExeption(String message){
        setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
