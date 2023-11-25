package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.ReseniaException;
import com.ApiRest.SkillChallengeApiRest.Exception.VendedorException;
import com.ApiRest.SkillChallengeApiRest.entity.PerfilTienda;
import com.ApiRest.SkillChallengeApiRest.entity.Producto;
import com.ApiRest.SkillChallengeApiRest.entity.Vendedor;
import com.ApiRest.SkillChallengeApiRest.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vendedores")
@Tag(name = "Vendedores")
@CrossOrigin(origins="*")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @Operation(summary = "Create a new vendedor")
    @PostMapping("/create")
    public Vendedor createVendedor(@Valid @RequestBody Vendedor vendedor) {
        return vendedorService.createVendedor(vendedor);
    }

    @Operation(summary = "Get a vendedor by ID")
    @GetMapping("/{id}")
    public Vendedor getVendedor(@PathVariable Long id) {
        if (id <= 0) {
            throw new VendedorException("El Vendedor no se encontró con el ID: " + id);
        }
        return vendedorService.getVendedor(id);
    }

    @Operation(summary = "Update a vendedor by ID")
    @PutMapping("/{id}")
    public Vendedor updateVendedor(@PathVariable Long id, @RequestBody Vendedor updateVendedor) {
        return vendedorService.updateVendedor(id, updateVendedor);
    }

    @Operation(summary = "Delete a vendedor by ID")
    @DeleteMapping("/{id}")
    public void deleteVendedor(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
    }

    @Operation(summary = "Get all vendedores")
    @GetMapping("/getAll")
    public List<Vendedor> getAllVendedores() {
        return vendedorService.getAllVendedores();
    }

    @Operation(summary = "Get the productos de un vendedor")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos del vendedor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class),
                            examples = @ExampleObject(value = "[{\"id\": 1, \"contenido\": \"Contenido del producto\", \"puntuacion\": 10, \"estatus\": 1}]")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Vendedor no encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Vendedor no encontrado")
                    )
            )
    })
    @GetMapping("/{id}/productos_vendedor")
    public ResponseEntity<?> getVendedorProductos(@PathVariable Long id){
        Vendedor vendedor = vendedorService.getVendedor(id);
        if(vendedor != null){
            List<Producto> productos = vendedor.getProductos();
            return ResponseEntity.ok(productos);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado");
        }
    }

    @Operation(summary = "Get the PerfilTiendas of a Vendedor")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of PerfilTiendas of the Vendedor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PerfilTienda.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Vendedor not found",
                    content = @Content(
                            mediaType = "text/plain"
                    )
            )
    })
    @GetMapping("/{id}/perfilTiendas_vendedor")
    public ResponseEntity<?> getPerfilTiendasVendedor(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.getVendedor(id);
        if (vendedor != null) {
            List<PerfilTienda> perfilTiendas = vendedor.getPerfilTiendas();
            return ResponseEntity.ok(perfilTiendas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor not found");
        }
    }



}
