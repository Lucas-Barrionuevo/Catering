package ar.edu.unahur.obj2.catering.carta;

import ar.edu.unahur.obj2.catering.excepciones.NadaParaRevertirException;
import java.util.ArrayList;
import java.util.List;

public class GestorCarta {
    private final List<Modificacion> historial = new ArrayList<>();

    public void aplicar(Modificacion modificacion) {
        modificacion.execute();
        historial.addAll(modificacion.modificacionesParaHistorial());
    }

    public void revertir() {
        if (historial.isEmpty()) {
            throw new NadaParaRevertirException();
        }
        Modificacion ultimaModificacion = historial.get(historial.size() - 1);
        ultimaModificacion.undo();
        historial.remove(historial.size() - 1);
    }
}
