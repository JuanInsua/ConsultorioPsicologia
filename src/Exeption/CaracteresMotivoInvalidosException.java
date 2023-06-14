package Exeption;

public class CaracteresMotivoInvalidosException extends RuntimeException{
    private String message;
    public CaracteresMotivoInvalidosException(String message){
        setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
