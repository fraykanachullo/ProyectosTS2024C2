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
import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.models.Materialesx;
import pe.edu.upeu.asistencia.services.MaterialesxService;

@RestController
@RequestMapping("/asis/materialesx")
public class MaterialesxController {

    @Autowired
    private MaterialesxService materialesxService;

    @GetMapping("/list")
    public ResponseEntity<List<Materialesx>> listMaterialesx() {
        List<Materialesx> actDto = materialesxService.findAll();
        return ResponseEntity.ok(actDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Materialesx> createMaterialesx(@RequestBody MaterialesxDto.MaterialesxCrearDto materialesx) {
        Materialesx data = materialesxService.save(materialesx);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Materialesx> getMaterialesxById(@PathVariable Long id) {
        Materialesx materialesx = materialesxService.getMaterialesxById(id);
        return ResponseEntity.ok(materialesx);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMaterialesx(@PathVariable Long id) {
        Materialesx materialesx = materialesxService.getMaterialesxById(id);
        return ResponseEntity.ok(materialesxService.delete(materialesx.getId()));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Materialesx> updateMaterialesx(@PathVariable Long id,
                                                         @RequestBody MaterialesxDto.MaterialesxCrearDto materialesxDetails) {
        Materialesx updatedMaterialesx = materialesxService.update(materialesxDetails, id);
        return ResponseEntity.ok(updatedMaterialesx);
    }
}
