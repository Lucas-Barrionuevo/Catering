package ar.edu.unahur.obj2.catering.carta;

import java.util.List;

public interface Modificacion {
    void execute();

    void undo();

    default List<Modificacion> modificacionesParaHistorial() {
        return List.of(this);
    }
}
