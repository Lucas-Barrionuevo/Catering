package ar.edu.unahur.obj2.catering.excepciones;

public class PrecioNegativoException extends RuntimeException {
    // Excepción no verificada: quien crea el servicio debe corregir el precio negativo.
    public PrecioNegativoException(double precio) {
        super("El precio del servicio no puede ser negativo: " + precio);
    }
}
