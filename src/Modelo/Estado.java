package Modelo;

/**
 * La clase Estado representa los posibles estados en los que puede encontrarse un objeto.
 * Los estados posibles son ACTIVADO, CANCELADO y ATENDIDO.
 */
public enum Estado {
    ACTIVADO,
    CANCELADO,
    ATENDIDO;
    /**
     * Constructor privado de la clase Estado.
     * Se utiliza para restringir la creación de instancias de esta clase.
     */
    private Estado() {
        // No se requiere ninguna implementación aquí.
    }

    /**
     * Devuelve una representación en forma de cadena del estado.
     *
     * @return La representación en forma de cadena del estado.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
