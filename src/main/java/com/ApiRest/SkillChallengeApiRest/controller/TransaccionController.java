package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.ReseniaException;
import com.ApiRest.SkillChallengeApiRest.Exception.TransaccionException;
import com.ApiRest.SkillChallengeApiRest.entity.Transaccion;
import com.ApiRest.SkillChallengeApiRest.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/transacciones")
@Tag(name = "Transacciones")
@CrossOrigin(origins="*")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @Operation(summary = "Create a new transaccion")
    @PostMapping("/create")
    public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.createTransaccion(transaccion);
    }

    @Operation(summary = "Get a transaccion by ID")
    @GetMapping("/{id}")
    public Transaccion getTransaccion(@PathVariable Long id) {
        if (id <= 0) {
            throw new TransaccionException("la Transaccion no se encontró con el ID: " + id);
        }
        return transaccionService.getTransaccion(id);
    }

    @Operation(summary = "Update a transaccion by ID")
    @PutMapping("/{id}")
    public Transaccion updateTransaccion(@PathVariable Long id, @RequestBody Transaccion updateTransaccion) {
        return transaccionService.updateTransaccion(id, updateTransaccion);
    }

    @Operation(summary = "Delete a transaccion by ID")
    @DeleteMapping("/{id}")
    public void deleteTransaccion(@PathVariable Long id) {
        transaccionService.deleteTransaccion(id);
    }

    @Operation(summary = "Get all transacciones")
    @GetMapping("/getAll")
    public List<Transaccion> getAllTransacciones() {
        return transaccionService.getAllTransacciones();
    }

}
