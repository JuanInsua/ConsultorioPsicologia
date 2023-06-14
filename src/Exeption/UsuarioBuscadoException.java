package Exeption;

/**
 * Esta clase representa una excepción personalizada lanzada cuando un usuario no es encontrado.
 */
public class UsuarioBuscadoException extends RuntimeException {

    private String message;

    /**
     * Crea una nueva instancia de UsuarioBuscadoException con el mensaje especificado.
     *
     * @param message el mensaje de la excepción
     */
    public UsuarioBuscadoException(String message) {
        setMessage(message);
    }

    /**
     * Obtiene el mensaje asociado con esta excepción.
     *
     * @return el mensaje de la excepción
     */
    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    /**
     * Devuelve una representación en cadena de la excepción.
     *
     * @return una cadena que contiene el nombre de la clase y el mensaje de la excepción
     */
    @Override
    public String toString() {
        return "UsuarioBuscadoException{" +
                "message='" + message + '\'' +
                '}';
    }
}

