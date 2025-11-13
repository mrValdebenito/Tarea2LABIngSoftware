package cl.valdebenito.usuarios.ev2.controller;

import cl.valdebenito.usuarios.ev2.Cotizacion;
import cl.valdebenito.usuarios.ev2.service.CotizacionService;
import cl.valdebenito.usuarios.ev2.service.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping
    public ResponseEntity<Cotizacion> crearCotizacion(@RequestBody Cotizacion cotizacion) {
        try {
            Cotizacion nuevaCotizacion = cotizacionService.crearCotizacion(cotizacion);
            return new ResponseEntity<>(nuevaCotizacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/confirmar/{id}")
    public ResponseEntity<Object> confirmarVenta(@PathVariable int id) {
        try {
            Cotizacion ventaConfirmada = cotizacionService.confirmarVenta(id);
            return new ResponseEntity<>(ventaConfirmada, HttpStatus.OK);

        } catch (StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (RuntimeException e) {
            return new ResponseEntity<>("Cotización no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}