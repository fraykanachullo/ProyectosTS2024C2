package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Inscrito;
import pe.edu.upeu.asistencia.repositories.InscritoRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class InscritoServiceImp implements InscritoService {

    private static final String ACTIVIDAD_NOT_FOUND = "Actividad no existe con id :";

    @Autowired
    private InscritoRepository inscritoRepo;

    @Override
    public Inscrito save(Inscrito inscrito) {
        return inscritoRepo.save(inscrito);
    }

    @Override
    public List<Inscrito> findAll() {
        return inscritoRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTIVIDAD_NOT_FOUND + id));
        inscritoRepo.delete(inscritox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @Override
    public Inscrito getEntidadById(Long id) {
        return inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTIVIDAD_NOT_FOUND + id));
    }

    @Override
    public Inscrito update(Inscrito actividad, Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTIVIDAD_NOT_FOUND + id));
        inscritox.setCui(actividad.getCui());
        inscritox.setTipoCui(actividad.getTipoCui());        
        inscritox.setOfflinex(actividad.getOfflinex());
        return inscritoRepo.save(inscritox);         
    }
}
