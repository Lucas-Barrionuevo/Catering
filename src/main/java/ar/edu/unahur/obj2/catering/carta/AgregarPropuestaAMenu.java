package ar.edu.unahur.obj2.catering.carta;

import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class AgregarPropuestaAMenu implements Modificacion {
    private final Menu menu;
    private final Propuesta propuesta;
    private boolean aplicada;

    public AgregarPropuestaAMenu(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void execute() {
        menu.agregar(propuesta);
        aplicada = true;
    }

    @Override
    public void undo() {
        if (aplicada) {
            menu.quitar(propuesta);
            aplicada = false;
        }
    }
}
