package cl.valdebenito.usuarios.ev2.repository;

import cl.valdebenito.usuarios.ev2.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    List<Cotizacion> findByEstado(Cotizacion.EstadoCotizacion estado);
}