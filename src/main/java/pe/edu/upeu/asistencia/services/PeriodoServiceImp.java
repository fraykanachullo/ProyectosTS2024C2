package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.repositories.PeriodoRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class PeriodoServiceImp implements PeriodoService {

    private static final String PERIODO_NOT_FOUND = "Periodo no existe con id :";

    @Autowired
    private PeriodoRepository periodoRepo;

    @Override
    public Periodo save(Periodo periodo) {
        return periodoRepo.save(periodo);
    }

    @Override
    public List<Periodo> findAll() {
        return periodoRepo.findAll();
    }

    @Override
    public Periodo getPeriodoById(Long id) {
        return periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIODO_NOT_FOUND + id));
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIODO_NOT_FOUND + id));
        periodoRepo.delete(periodox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @Override
    public Periodo update(Periodo periodo, Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIODO_NOT_FOUND + id));
        periodox.setNombre(periodo.getNombre());
        periodox.setEstado(periodo.getEstado());        

        return periodoRepo.save(periodox);
    }
}
