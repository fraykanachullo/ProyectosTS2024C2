package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.services.ActividadService;

@RestController
@RequestMapping("/asis/actividad")
public class ActividadController {

    private static final Logger logger = LoggerFactory.getLogger(ActividadController.class);

    @Autowired
    private ActividadService actividadService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Actividad>> listActividad() {
        List<Actividad> actDto = actividadService.findAll();

        logger.info("Ver Aquí: {}", actDto);

        return ResponseEntity.ok(actDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Actividad> createActividad(@RequestBody Actividad actividad) {
        Actividad data = actividadService.save(actividad);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Actividad> getActividadById(@PathVariable Long id) {
        Actividad actividad = actividadService.getActividadById(id);
        return ResponseEntity.ok(actividad);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteActividad(@PathVariable Long id) {
        Actividad actividad = actividadService.getActividadById(id);
        return ResponseEntity.ok(actividadService.delete(actividad.getId()));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable Long id, @RequestBody Actividad actividadDetails) {
        Actividad updatedActividad = actividadService.update(actividadDetails, id);
        return ResponseEntity.ok(updatedActividad);
    }
}
