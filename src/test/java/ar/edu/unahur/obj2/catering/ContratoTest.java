package ar.edu.unahur.obj2.catering;

import ar.edu.unahur.obj2.catering.contratos.Contrato;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;
import ar.edu.unahur.obj2.catering.propuestas.adicionales.MozosAdicionales;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ContratoTest {
    @Test
    void dadoUnContratoCreado_alActualizarElPrecioDeUnServicio_elImporteContratadoNoCambia() {
        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80_000);
        Servicio parrillada = new Servicio("Parrillada completa", 240_000);
        Servicio mesaDulce = new Servicio("Mesa dulce", 60_000);
        Menu menu = new Menu("Menú Parrillero", 10);
        menu.agregar(mesaDeEntradas);
        menu.agregar(parrillada);
        menu.agregar(mesaDulce);
        Propuesta propuestaContratada = new MozosAdicionales(menu);
        Contrato contrato = new Contrato("Lucía", propuestaContratada);

        assertEquals("Lucía", contrato.getNombreCliente());
        assertEquals(360_000, contrato.getImporte());
        assertEquals("Menú Parrillero + Mozos adicionales", contrato.getDescripcion());

        parrillada.actualizarPrecio(260_000);

        assertEquals(378_000, propuestaContratada.getPrecio());
        assertEquals(360_000, contrato.getImporte());
    }
}
