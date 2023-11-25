package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.CarritoException;
import com.ApiRest.SkillChallengeApiRest.Exception.ProductoNotFoundException;
import com.ApiRest.SkillChallengeApiRest.entity.Carrito;
import com.ApiRest.SkillChallengeApiRest.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
@Tag(name = "Carritos")
@CrossOrigin(origins="*")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @Operation(summary = "Create a new carrito")
    @PostMapping("/create")
    public Carrito createCarrito( @RequestBody Carrito carrito) {
        return carritoService.createCarrito(carrito);
    }

    @Operation(summary = "Get a carrito by ID")
    @GetMapping("/{id}")
    public Carrito getCarrito(@Valid @PathVariable Long id) {
        if (id <= 0) {
            throw new CarritoException("El Carrito no se encontró con el ID: " + id);
        }
        return carritoService.getCarrito(id);
    }

    @Operation(summary = "Update a carrito by ID")
    @PutMapping("/{id}")
    public Carrito updateCarrito(@PathVariable Long id,@Valid @RequestBody Carrito updateCarrito) {
        return carritoService.updateCarrito(id, updateCarrito);
    }

    @Operation(summary = "Delete a carrito by ID")
    @DeleteMapping("/{id}")
    public void deleteCarrito(@PathVariable Long id) {
        carritoService.deleteCarrito(id);
    }

    @Operation(summary = "Get all carritos")
    @GetMapping("/getAll")
    public List<Carrito> getAllCarritos() {
        return carritoService.getAllCarritos();
    }

}
