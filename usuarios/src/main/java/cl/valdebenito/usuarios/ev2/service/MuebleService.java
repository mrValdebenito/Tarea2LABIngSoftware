package cl.valdebenito.usuarios.ev2.service;

import cl.valdebenito.usuarios.ev2.Mueble;
import cl.valdebenito.usuarios.ev2.Mueble.EstadoMueble;
import cl.valdebenito.usuarios.ev2.repository.MuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepository;

    public Mueble guardarMueble(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    public List<Mueble> listarTodosLosMuebles() {
        return muebleRepository.findAll();
    }

    public Optional<Mueble> buscarMueblePorId(int id) {
        return muebleRepository.findById(id);
    }

    public boolean desactivarMueble(int id) {
        Optional<Mueble> muebleOpt = muebleRepository.findById(id);

        if (muebleOpt.isPresent()) {
            Mueble mueble = muebleOpt.get();
            mueble.setEstado(EstadoMueble.inactivo);
            muebleRepository.save(mueble);
            return true;
        }
        return false;
    }
}