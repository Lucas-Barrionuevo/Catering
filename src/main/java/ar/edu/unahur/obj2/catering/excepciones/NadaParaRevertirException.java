package ar.edu.unahur.obj2.catering.excepciones;

public class NadaParaRevertirException extends RuntimeException {
    // Excepción no verificada: quien solicita la reversión debe verificar que haya historial.
    public NadaParaRevertirException() {
        super("No hay modificaciones para revertir");
    }
}
