package cl.valdebenito.usuarios.ev2.repository;

import cl.valdebenito.usuarios.ev2.DetalleCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Integer> {
    List<DetalleCotizacion> findByCotizacionIdCotizacion(int idCotizacion);
    List<DetalleCotizacion> findByMuebleIdMueble(int idMueble);
}