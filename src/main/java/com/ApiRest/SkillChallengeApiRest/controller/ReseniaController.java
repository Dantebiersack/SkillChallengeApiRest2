package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.OfertaException;
import com.ApiRest.SkillChallengeApiRest.Exception.ReseniaException;
import com.ApiRest.SkillChallengeApiRest.entity.Resenia;
import com.ApiRest.SkillChallengeApiRest.service.ReseniaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenias")
@Tag(name = "Resenias")
@CrossOrigin(origins="*")
public class ReseniaController {
    @Autowired
    private ReseniaService reseniaService;

    @Operation(summary = "Create a new resenia")
    @PostMapping("/create")
    public Resenia createResenia(@Valid @RequestBody Resenia resenia) {
        return reseniaService.createResenia(resenia);
    }

    @Operation(summary = "Get a resenia by ID")
    @GetMapping("/{id}")
    public Resenia getResenia(@PathVariable Long id) {
        if (id <= 0) {
            throw new ReseniaException("la Resenia no se encontró con el ID: " + id);
        }
        return reseniaService.getResenia(id);
    }

    @Operation(summary = "Update a resenia by ID")
    @PutMapping("/{id}")
    public Resenia updateResenia(@PathVariable Long id, @RequestBody Resenia updateResenia) {
        return reseniaService.updateResenia(id, updateResenia);
    }

    @Operation(summary = "Delete a resenia by ID")
    @DeleteMapping("/{id}")
    public void deleteResenia(@PathVariable Long id) {
        reseniaService.deleteResenia(id);
    }

    @Operation(summary = "Get all resenias")
    @GetMapping("/getAll")
    public List<Resenia> getAllResenias() {
        return reseniaService.getAllResenias();
    }
}
