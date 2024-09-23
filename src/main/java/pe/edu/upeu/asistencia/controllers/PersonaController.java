package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.asistencia.models.Persona;
import pe.edu.upeu.asistencia.services.PersonaService;

@RestController
@RequestMapping("/asis/persona")
public class PersonaController {

    @Autowired
    private PersonaService entidadService;

    @GetMapping("/list")
    public ResponseEntity<List<Persona>> listEntidad() {
        List<Persona> actDto = entidadService.findAll();
        return ResponseEntity.ok().body(actDto);
    }
}
