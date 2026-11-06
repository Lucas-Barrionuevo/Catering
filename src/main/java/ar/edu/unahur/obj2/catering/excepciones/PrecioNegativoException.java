package ar.edu.unahur.obj2.catering.excepciones;

public class PrecioNegativoException extends RuntimeException {
    // Es no chequeada: un precio negativo es un dato inválido al crear el servicio.
    public PrecioNegativoException(double precio) {
        super("El precio del servicio no puede ser negativo: " + precio);
    }
}
