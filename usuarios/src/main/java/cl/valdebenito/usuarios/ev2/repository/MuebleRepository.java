package cl.valdebenito.usuarios.ev2.repository;

import cl.valdebenito.usuarios.ev2.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Integer> {
    List<Mueble> findByEstado(Mueble.EstadoMueble estado);
}