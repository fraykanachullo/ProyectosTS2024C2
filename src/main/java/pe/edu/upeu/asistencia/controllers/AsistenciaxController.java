/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upeu.asistencia.dtos.AsistenciaxDto;
import pe.edu.upeu.asistencia.models.Asistenciax;
import pe.edu.upeu.asistencia.services.AsistenciaxService;

/**
 * Controller para la gestión de Asistenciax.
 */
@RestController
@RequestMapping("/asis/asistenciax")
public class AsistenciaxController {

    @Autowired
    private AsistenciaxService asistenciaxService;   

    @GetMapping(value = "/list")
    public ResponseEntity<List<Asistenciax>> listMaterialesx() {
        List<Asistenciax> actDto = asistenciaxService.findAll();
        return ResponseEntity.ok(actDto);
    }  
    
    @PostMapping("/crear")
    public ResponseEntity<Asistenciax> createMaterialesx(@RequestBody AsistenciaxDto.AsistenciaxCrearDto entidadx) {        
        Asistenciax data = asistenciaxService.save(entidadx);
        return ResponseEntity.ok(data);
    }    
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Asistenciax> getMaterialesxById(@PathVariable Long id) {
        Asistenciax entidadx = asistenciaxService.getEntidadById(id);
        return ResponseEntity.ok(entidadx);
    }    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMaterialesx(@PathVariable Long id) {
        Asistenciax entidadx = asistenciaxService.getEntidadById(id);
        return ResponseEntity.ok(asistenciaxService.delete(entidadx.getId()));
    }   
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Asistenciax> updateMaterialesx(@PathVariable Long id,
            @RequestBody AsistenciaxDto.AsistenciaxCrearDto entidadxDetails) {
        Asistenciax updatedEntidadx = asistenciaxService.update(entidadxDetails, id);
        return ResponseEntity.ok(updatedEntidadx);
    }    
}
