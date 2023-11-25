package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.OfertaException;
import com.ApiRest.SkillChallengeApiRest.Exception.PerfilTiendaException;
import com.ApiRest.SkillChallengeApiRest.entity.PerfilTienda;
import com.ApiRest.SkillChallengeApiRest.service.PerfilTiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/perfilTiendas")
@Tag(name = "PerfilTiendas")
@CrossOrigin(origins="*")
public class PerfilTiendaController {
        @Autowired
        private PerfilTiendaService perfilTiendaService;

        @Operation(summary = "Create a new perfil de tienda")
        @PostMapping("/create")
        public PerfilTienda createPerfilTienda(@Valid @RequestBody PerfilTienda perfilTienda) {
            return perfilTiendaService.createPerfilTienda(perfilTienda);
        }

        @Operation(summary = "Get a perfil de tienda by ID")
        @GetMapping("/{id}")
        public PerfilTienda getPerfilTienda(@PathVariable Long id) {
                if (id <= 0) {
                        throw new PerfilTiendaException("El Perfil de tienda no se encontró con el ID: " + id);
                }
                return perfilTiendaService.getPerfilTienda(id);
        }

        @Operation(summary = "Update a perfil de tienda by ID")
        @PutMapping("/{id}")
        public PerfilTienda updatePerfilTienda(@PathVariable Long id, @RequestBody PerfilTienda updatePerfilTienda) {
            return perfilTiendaService.updatePerfilTienda(id, updatePerfilTienda);
        }

        @Operation(summary = "Delete a perfil de tienda by ID")
        @DeleteMapping("/{id}")
        public void deletePerfilTienda(@PathVariable Long id) {
            perfilTiendaService.deletePerfilTienda(id);
        }

        @Operation(summary = "Get all perfiles de tienda")
        @GetMapping("/getAll")
        public List<PerfilTienda> getAllPerfilTiendas() {
            return perfilTiendaService.getAllPerfilTiendas();
        }
}

