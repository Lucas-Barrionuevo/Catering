package ar.edu.unahur.obj2.catering.catalogo;

import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Propuesta> propuestas;

    private Catalogo(List<Propuesta> propuestas) {
        this.propuestas = List.copyOf(propuestas);
    }

    public static Catalogo inicial() {
        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80_000);
        Servicio parrillada = new Servicio("Parrillada completa", 240_000);
        Servicio mesaDulce = new Servicio("Mesa dulce", 60_000);
        Servicio barraDeTragos = new Servicio("Barra de tragos", 150_000);
        Servicio dj = new Servicio("DJ y sonido", 120_000);
        Servicio brindis = new Servicio("Brindis de bienvenida", 50_000);

        Menu menuParrillero = new Menu("Menú Parrillero", 10);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrillada);
        menuParrillero.agregar(mesaDulce);

        Menu packFiesta = new Menu("Pack Fiesta", 5);
        packFiesta.agregar(barraDeTragos);
        packFiesta.agregar(dj);

        Menu eventoCompleto = new Menu("Evento Completo", 20);
        eventoCompleto.agregar(menuParrillero);
        eventoCompleto.agregar(packFiesta);
        eventoCompleto.agregar(brindis);

        List<Propuesta> propuestas = new ArrayList<>();
        propuestas.add(mesaDeEntradas);
        propuestas.add(parrillada);
        propuestas.add(mesaDulce);
        propuestas.add(barraDeTragos);
        propuestas.add(dj);
        propuestas.add(brindis);
        propuestas.add(menuParrillero);
        propuestas.add(packFiesta);
        propuestas.add(eventoCompleto);
        return new Catalogo(propuestas);
    }

    public List<Propuesta> getPropuestas() {
        return propuestas;
    }
}
