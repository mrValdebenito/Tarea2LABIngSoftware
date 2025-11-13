package cl.valdebenito.usuarios.ev2.controller;

import cl.valdebenito.usuarios.ev2.Mueble;
import cl.valdebenito.usuarios.ev2.service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @PostMapping
    public ResponseEntity<Mueble> crearMueble(@RequestBody Mueble mueble) {
        Mueble nuevoMueble = muebleService.guardarMueble(mueble);
        return new ResponseEntity<>(nuevoMueble, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Mueble> listarMuebles() {
        return muebleService.listarTodosLosMuebles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mueble> obtenerMueblePorId(@PathVariable int id) {
        Optional<Mueble> mueble = muebleService.buscarMueblePorId(id);

        return mueble.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> actualizarMueble(@PathVariable int id, @RequestBody Mueble muebleDetalles) {
        Optional<Mueble> muebleOpt = muebleService.buscarMueblePorId(id);

        if (muebleOpt.isPresent()) {
            Mueble muebleExistente = muebleOpt.get();

            muebleExistente.setNombreMueble(muebleDetalles.getNombreMueble());
            muebleExistente.setTipo(muebleDetalles.getTipo());
            muebleExistente.setPrecioBase(muebleDetalles.getPrecioBase());
            muebleExistente.setStock(muebleDetalles.getStock());
            muebleExistente.setMaterial(muebleDetalles.getMaterial());
            muebleExistente.setTamaño(muebleDetalles.getTamaño());

            Mueble muebleActualizado = muebleService.guardarMueble(muebleExistente);
            return new ResponseEntity<>(muebleActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivarMueble(@PathVariable int id) {
        boolean desactivado = muebleService.desactivarMueble(id);

        if (desactivado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}