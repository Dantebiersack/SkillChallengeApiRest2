package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.OfertaException;
import com.ApiRest.SkillChallengeApiRest.Exception.ProductoNotFoundException;
import com.ApiRest.SkillChallengeApiRest.entity.Oferta;
import com.ApiRest.SkillChallengeApiRest.service.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ofertas")
@Tag(name = "Ofertas")
@CrossOrigin(origins="*")
public class OfertaController {
        @Autowired
        private OfertaService ofertaService;

        @Operation(summary = "Create a new oferta")
        @PostMapping("/create")
        public Oferta createOferta(@Valid @RequestBody Oferta oferta) {
            return ofertaService.createOferta(oferta);
        }

        @Operation(summary = "Get an oferta by ID")
        @GetMapping("/{id}")
        public Oferta getOferta(@PathVariable Long id) {
                if (id <= 0) {
                        throw new OfertaException("La oferta no se encontró con el ID: " + id);
                }
                return ofertaService.getOferta(id);
        }

        @Operation(summary = "Update an oferta by ID")
        @PutMapping("/{id}")
        public Oferta updateOferta(@PathVariable Long id, @Valid @RequestBody Oferta updateOferta) {
            return ofertaService.updateOferta(id, updateOferta);
        }

        @Operation(summary = "Delete an oferta by ID")
        @DeleteMapping("/{id}")
        public void deleteOferta(@PathVariable Long id) {
            ofertaService.deleteOferta(id);
        }

        @Operation(summary = "Get all ofertas")
        @GetMapping("/getAll")
        public List<Oferta> getAllOfertas() {
            return ofertaService.getAllOfertas();
        }
}
