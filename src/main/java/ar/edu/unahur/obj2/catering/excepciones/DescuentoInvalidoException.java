package ar.edu.unahur.obj2.catering.excepciones;

public class DescuentoInvalidoException extends RuntimeException {
    // Es no chequeada: un descuento fuera de rango es un dato inválido al crear el menú.
    public DescuentoInvalidoException(double descuento) {
        super("El descuento debe estar entre 0 y 100: " + descuento);
    }
}
