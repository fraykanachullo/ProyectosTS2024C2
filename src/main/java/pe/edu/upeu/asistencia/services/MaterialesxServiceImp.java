package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.mappers.MaterialesxMapper;
import pe.edu.upeu.asistencia.models.Materialesx;
import pe.edu.upeu.asistencia.repositories.MaterialesxRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class MaterialesxServiceImp implements MaterialesxService {

    private static final Logger logger = LoggerFactory.getLogger(MaterialesxServiceImp.class);

    @Autowired
    private MaterialesxRepository materialesxRepo;

    @Autowired
    private ActividadService actividadService;

    private final MaterialesxMapper materialesxMapper;

    @Override
    public Materialesx save(MaterialesxDto.MaterialesxCrearDto materialesx) {
        Materialesx matEnt = materialesxMapper.materialesxCrearDtoToMaterialesx(materialesx);
        matEnt.setActividadId(actividadService.getActividadById(materialesx.actividadId()));
        logger.info("Fecha: {}", materialesx.fecha());
        logger.info("Hora Registro: {}", materialesx.horaReg());
        try {
            return materialesxRepo.save(matEnt);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Materialesx> findAll() {
        try {
            return materialesxRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Materialesx materialesxx = materialesxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materialesx not exist with id :" + id));
        materialesxRepo.delete(materialesxx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @Override
    public Materialesx getMaterialesxById(Long id) {
        return materialesxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materialesx not exist with id :" + id));
    }

    @Override
    public Materialesx update(MaterialesxDto.MaterialesxCrearDto materialesx, Long id) {
        Materialesx materialesxx = materialesxRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        logger.info("Modificación Fecha y Hora: {}", materialesx.modFh());
        materialesxx.setFecha(materialesx.fecha());
        materialesxx.setHoraReg(materialesx.horaReg());
        materialesxx.setOfflinex(materialesx.offlinex());
        return materialesxRepo.save(materialesxx);
    }
}
