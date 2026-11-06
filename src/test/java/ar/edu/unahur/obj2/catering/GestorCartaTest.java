package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.carta.ActualizarPrecioServicio;
import ar.edu.unahur.obj2.catering.carta.AgregarPropuestaAMenu;
import ar.edu.unahur.obj2.catering.carta.GestorCarta;
import ar.edu.unahur.obj2.catering.carta.LoteModificaciones;
import ar.edu.unahur.obj2.catering.carta.Modificacion;
import ar.edu.unahur.obj2.catering.carta.QuitarPropuestaDeMenu;
import ar.edu.unahur.obj2.catering.excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.excepciones.PropuestaNoIncluidaException;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class GestorCartaTest {
    @Test
    void dadaUnaSecuenciaDeModificaciones_alRevertirEnOrdenInverso_restauranLaCarta() {
        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80_000);
        Servicio parrillada = new Servicio("Parrillada completa", 240_000);
        Servicio mesaDulce = new Servicio("Mesa dulce", 60_000);
        Servicio fotografo = new Servicio("Fotógrafo", 100_000);
        Menu menu = new Menu("Menú Parrillero", 10);
        menu.agregar(mesaDeEntradas);
        menu.agregar(parrillada);
        menu.agregar(mesaDulce);
        GestorCarta gestor = new GestorCarta();

        gestor.aplicar(new AgregarPropuestaAMenu(menu, fotografo));
        assertEquals(432_000, menu.getPrecio());
        gestor.aplicar(new ActualizarPrecioServicio(parrillada, 260_000));
        assertEquals(450_000, menu.getPrecio());
        gestor.aplicar(new QuitarPropuestaDeMenu(menu, mesaDulce));
        assertEquals(396_000, menu.getPrecio());

        gestor.revertir();
        assertEquals(450_000, menu.getPrecio());
        gestor.revertir();
        assertEquals(432_000, menu.getPrecio());
        gestor.revertir();
        assertEquals(342_000, menu.getPrecio());
        assertThrows(NadaParaRevertirException.class, gestor::revertir);
    }

    @Test
    void dadasDosActualizacionesDelMismoServicio_alRevertirCadaUna_restauranElPrecioAnterior() {
        Servicio servicio = new Servicio("Parrillada completa", 240_000);
        GestorCarta gestor = new GestorCarta();
        Modificacion primera = new ActualizarPrecioServicio(servicio, 260_000);
        Modificacion segunda = new ActualizarPrecioServicio(servicio, 300_000);

        gestor.aplicar(primera);
        gestor.aplicar(segunda);
        assertEquals(300_000, servicio.getPrecio());

        gestor.revertir();
        assertEquals(260_000, servicio.getPrecio());
        gestor.revertir();
        assertEquals(240_000, servicio.getPrecio());
    }

    @Test
    void dadoUnLoteValido_alAplicarloYRevertirlo_registraCadaModificacionEnOrden() {
        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80_000);
        Servicio parrillada = new Servicio("Parrillada completa", 240_000);
        Servicio mesaDulce = new Servicio("Mesa dulce", 60_000);
        Servicio fotografo = new Servicio("Fotógrafo", 100_000);
        Menu menu = new Menu("Menú Parrillero", 10);
        menu.agregar(mesaDeEntradas);
        menu.agregar(parrillada);
        menu.agregar(mesaDulce);
        GestorCarta gestor = new GestorCarta();
        LoteModificaciones lote = new LoteModificaciones(List.of(
                new AgregarPropuestaAMenu(menu, fotografo),
                new ActualizarPrecioServicio(parrillada, 260_000),
                new QuitarPropuestaDeMenu(menu, mesaDulce)));

        gestor.aplicar(lote);
        assertEquals(396_000, menu.getPrecio());

        gestor.revertir();
        assertEquals(450_000, menu.getPrecio());
        gestor.revertir();
        assertEquals(432_000, menu.getPrecio());
        gestor.revertir();
        assertEquals(342_000, menu.getPrecio());
        assertThrows(NadaParaRevertirException.class, gestor::revertir);
    }

    @Test
    void dadoUnLoteConUnaModificacionInvalida_alAplicarloDejaLaCartaComoEstaba() {
        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80_000);
        Servicio parrillada = new Servicio("Parrillada completa", 240_000);
        Servicio mesaDulce = new Servicio("Mesa dulce", 60_000);
        Servicio fotografo = new Servicio("Fotógrafo", 100_000);
        Servicio barra = new Servicio("Barra de tragos", 150_000);
        Menu menu = new Menu("Menú Parrillero", 10);
        menu.agregar(mesaDeEntradas);
        menu.agregar(parrillada);
        menu.agregar(mesaDulce);
        GestorCarta gestor = new GestorCarta();
        gestor.aplicar(new AgregarPropuestaAMenu(menu, fotografo));
        LoteModificaciones lote = new LoteModificaciones(List.of(
                new ActualizarPrecioServicio(parrillada, 260_000),
                new QuitarPropuestaDeMenu(menu, barra)));

        assertThrows(PropuestaNoIncluidaException.class, () -> gestor.aplicar(lote));
        assertEquals(432_000, menu.getPrecio());
        assertEquals(240_000, parrillada.getPrecio());

        gestor.revertir();
        assertEquals(342_000, menu.getPrecio());
        assertThrows(NadaParaRevertirException.class, gestor::revertir);
    }

    @Test
    void dadoUnPrecioNegativo_alActualizarlo_noCambiaElPrecioNiSeRegistra() {
        Servicio servicio = new Servicio("Parrillada completa", 240_000);
        GestorCarta gestor = new GestorCarta();

        assertThrows(PrecioNegativoException.class,
                () -> gestor.aplicar(new ActualizarPrecioServicio(servicio, -1)));
        assertEquals(240_000, servicio.getPrecio());
        assertThrows(NadaParaRevertirException.class, gestor::revertir);
    }

    @Test
    void dadoUnMenu_alQuitarUnaPropuestaNoIncluida_noLoModifica() {
        Menu menu = new Menu("Menú", 0);
        Servicio propuesta = new Servicio("Mesa dulce", 60_000);
        GestorCarta gestor = new GestorCarta();

        assertThrows(PropuestaNoIncluidaException.class,
                () -> gestor.aplicar(new QuitarPropuestaDeMenu(menu, propuesta)));
        assertEquals(0, menu.getCantidadServicios());
        assertThrows(NadaParaRevertirException.class, gestor::revertir);
    }
}
