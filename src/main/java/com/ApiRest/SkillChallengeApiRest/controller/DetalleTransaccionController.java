package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.ProductoNotFoundException;
import com.ApiRest.SkillChallengeApiRest.entity.DetalleTransaccion;
import com.ApiRest.SkillChallengeApiRest.service.DetalleTransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalleTransacciones")
@Tag(name = "DetalleTransacciones")
@CrossOrigin(origins="*")
public class DetalleTransaccionController {
    @Autowired
    private DetalleTransaccionService detalleTransaccionService;

    @Operation(summary = "Create a new detalleTransaccion")
    @PostMapping("/create")
    public DetalleTransaccion createDetalleTransaccion(@Valid @RequestBody DetalleTransaccion detalleTransaccion) {
        return detalleTransaccionService.createDetalleTransaccion(detalleTransaccion);
    }

    @Operation(summary = "Get a detalleTransaccion by ID")
    @GetMapping("/{id}")
    public DetalleTransaccion getDetalleTransaccion(@PathVariable Long id) {
        return detalleTransaccionService.getDetalleTransaccion(id);
    }

    @Operation(summary = "Update a detalleTransaccion by ID")
    @PutMapping("/{id}")
    public DetalleTransaccion updateDetalleTransaccion(@PathVariable Long id,@Valid  @RequestBody DetalleTransaccion updateDetalleTransaccion) {
        return detalleTransaccionService.updateDetalleTransaccion(id, updateDetalleTransaccion);
    }

    @Operation(summary = "Delete a detalleTransaccion by ID")
    @DeleteMapping("/{id}")
    public void deleteDetalleTransaccion(@PathVariable Long id) {
        detalleTransaccionService.deleteDetalleTransaccion(id);
    }
}

