package cl.valdebenito.usuarios.ev2.service;

import cl.valdebenito.usuarios.ev2.*;
import cl.valdebenito.usuarios.ev2.Cotizacion.EstadoCotizacion;
import cl.valdebenito.usuarios.ev2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;
    @Autowired
    private MuebleRepository muebleRepository;
    @Autowired
    private DetalleCotizacionRepository detalleCotizacionRepository;
    @Autowired
    private VarianteRepository varianteRepository;

    public BigDecimal calcularPrecioFinal(Mueble mueble, List<Variante> variantes) {
        BigDecimal precioFinal = mueble.getPrecioBase();

        if (variantes != null) {
            for (Variante variante : variantes) {
                precioFinal = precioFinal.add(variante.getPrecioAdicional());
            }
        }
        return precioFinal;
    }

    @Transactional
    public Cotizacion crearCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    @Transactional(rollbackFor = StockInsuficienteException.class)
    public Cotizacion confirmarVenta(int idCotizacion) throws StockInsuficienteException {
        Optional<Cotizacion> cotizacionOpt = cotizacionRepository.findById(idCotizacion);
        if (cotizacionOpt.isEmpty()) {
            throw new RuntimeException("Cotización no encontrada.");
        }

        Cotizacion cotizacion = cotizacionOpt.get();

        if (cotizacion.getEstado() == EstadoCotizacion.Vendido) {
            return cotizacion;
        }

        Set<DetalleCotizacion> detalles = cotizacion.getDetalles();
        for (DetalleCotizacion detalle : detalles) {
            Mueble mueble = detalle.getMueble();
            int stockRequerido = detalle.getCantidad();

            if (mueble.getStock() < stockRequerido) {
                throw new StockInsuficienteException("stock insuficiente para el mueble: " + mueble.getNombreMueble());
            }
        }

        for (DetalleCotizacion detalle : detalles) {
            Mueble mueble = detalle.getMueble();
            int stockRequerido = detalle.getCantidad();

            mueble.setStock(mueble.getStock() - stockRequerido);
            muebleRepository.save(mueble);
        }

        cotizacion.setEstado(EstadoCotizacion.Vendido);
        return cotizacionRepository.save(cotizacion);
    }
}