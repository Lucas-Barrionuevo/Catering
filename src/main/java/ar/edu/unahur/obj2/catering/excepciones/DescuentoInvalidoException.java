package ar.edu.unahur.obj2.catering.excepciones;

public class DescuentoInvalidoException extends RuntimeException {
    // Excepción no verificada: quien crea el menú debe corregir el descuento fuera de rango.
    public DescuentoInvalidoException(double descuento) {
        super("El descuento debe estar entre 0 y 100: " + descuento);
    }
}
