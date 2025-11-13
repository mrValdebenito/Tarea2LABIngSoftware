package cl.valdebenito.usuarios.ev2.service;

import cl.valdebenito.usuarios.ev2.*;
import cl.valdebenito.usuarios.ev2.Cotizacion.EstadoCotizacion;
import cl.valdebenito.usuarios.ev2.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CotizacionServiceTest {

    @InjectMocks
    private CotizacionService cotizacionService;

    @Mock
    private MuebleRepository muebleRepository;
    @Mock
    private CotizacionRepository cotizacionRepository;
    @Mock
    private DetalleCotizacionRepository detalleCotizacionRepository;
    @Mock
    private VarianteRepository varianteRepository;

    private Mueble silla;
    private Variante barnizPremium;
    private Variante cojinSeda;

    @BeforeEach
    void setUp() {
        silla = new Mueble("Silla Roble", "Silla", new BigDecimal("100.00"), 10, Mueble.EstadoMueble.activo, Mueble.TamañoMueble.Mediano, "Roble");
        silla.setIdMueble(1);

        barnizPremium = new Variante("Barniz Premium", new BigDecimal("50.00"));
        cojinSeda = new Variante("Cojín de Seda", new BigDecimal("20.00"));
    }

    @Test
    void calcularPrecioFinal_SinVariantes_DebeSerPrecioBase() {
        List<Variante> variantes = null;
        BigDecimal precioFinal = cotizacionService.calcularPrecioFinal(silla, variantes);
        assertEquals(new BigDecimal("100.00"), precioFinal, "El precio sin variantes debe ser igual al precio base.");
    }

    @Test
    void calcularPrecioFinal_ConUnaVariante_DebeSumarAdicional() {
        List<Variante> variantes = Arrays.asList(barnizPremium);
        BigDecimal precioFinal = cotizacionService.calcularPrecioFinal(silla, variantes);
        assertEquals(new BigDecimal("150.00"), precioFinal, "El precio con una variante debe sumar el adicional.");
    }

    @Test
    void calcularPrecioFinal_ConVariasVariantes_DebeSumarTodosAdicionales() {
        List<Variante> variantes = Arrays.asList(barnizPremium, cojinSeda);
        BigDecimal precioFinal = cotizacionService.calcularPrecioFinal(silla, variantes);
        assertEquals(new BigDecimal("170.00"), precioFinal, "El precio con varias variantes debe sumar todos los adicionales.");
    }

    @Test
    void confirmarVenta_StockSuficiente_DebeDecrementarStockYAprobarVenta() throws StockInsuficienteException {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setIdCotizacion(1);
        cotizacion.setEstado(EstadoCotizacion.Cotizado);

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setMueble(silla);
        detalle.setCantidad(3);
        cotizacion.setDetalles(new HashSet<>(Arrays.asList(detalle)));

        when(cotizacionRepository.findById(1)).thenReturn(Optional.of(cotizacion));
        when(muebleRepository.save(any(Mueble.class))).thenReturn(silla);
        when(cotizacionRepository.save(any(Cotizacion.class))).thenReturn(cotizacion);

        Cotizacion resultado = cotizacionService.confirmarVenta(1);

        assertEquals(EstadoCotizacion.Vendido, resultado.getEstado(), "El estado debe ser Vendido.");
        verify(muebleRepository, times(1)).save(argThat(m -> m.getStock() == 7));
        verify(cotizacionRepository, times(1)).save(any(Cotizacion.class));
    }

    @Test
    void confirmarVenta_StockInsuficiente_DebeLanzarExcepcionYNoDecrementarStock() {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setIdCotizacion(2);

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setMueble(silla);
        detalle.setCantidad(15);
        cotizacion.setDetalles(new HashSet<>(Arrays.asList(detalle)));

        when(cotizacionRepository.findById(2)).thenReturn(Optional.of(cotizacion));

        Exception excepcion = assertThrows(StockInsuficienteException.class, () -> {
            cotizacionService.confirmarVenta(2);
        });

        assertTrue(excepcion.getMessage().contains("stock insuficiente"), "El mensaje de error debe indicar stock insuficiente.");

        verify(muebleRepository, never()).save(any(Mueble.class));
        verify(cotizacionRepository, never()).save(any(Cotizacion.class));
    }
}