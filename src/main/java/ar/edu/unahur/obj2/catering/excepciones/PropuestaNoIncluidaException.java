package ar.edu.unahur.obj2.catering.excepciones;

public class PropuestaNoIncluidaException extends RuntimeException {
    // Excepción no verificada: quien modifica el menú debe corregir la propuesta que intenta quitar.
    public PropuestaNoIncluidaException() {
        super("La propuesta no es un componente directo del menú");
    }
}
