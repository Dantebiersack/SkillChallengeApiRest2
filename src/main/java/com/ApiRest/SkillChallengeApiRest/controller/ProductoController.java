package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.ProductoNotFoundException;
import com.ApiRest.SkillChallengeApiRest.entity.Oferta;
import com.ApiRest.SkillChallengeApiRest.entity.Producto;
import com.ApiRest.SkillChallengeApiRest.service.ProductoService;
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
@RequestMapping("/productos")
@Tag(name = "Productos")
@CrossOrigin(origins="*")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Create a new producto")
    @PostMapping("/create")
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }

    @Operation(summary = "Get a producto by ID")
    @GetMapping("/{id}")
    public Producto getProducto(@Valid @PathVariable Long id) {
        if (id <= 0) {
            throw new ProductoNotFoundException("El producto no se encontró con el ID: " + id);
        }
        return productoService.getProducto(id);
    }

    @Operation(summary = "Update a producto by ID")
    @PutMapping("/{id}")
    public Producto updateProducto(@Valid @PathVariable Long id, @RequestBody Producto updateProducto) {
        return productoService.updateProducto(id, updateProducto);
    }

    @Operation(summary = "Delete a producto by ID")
    @DeleteMapping("/{id}")
    public void deleteProducto(@Valid @PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    @Operation(summary = "Get all productos")
    @GetMapping("/getAll")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @Operation(summary = "Get the ofertas de un producto")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de ofertas del producto",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Oferta.class),
                            examples = @ExampleObject(value = "[{\"id\": 1, \"contenido\": \"Contenido de oferta\", \"puntuacion\": 10, \"estatus\": 1}]")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Producto no encontrado")
                    )
            )
    })
    @GetMapping("/{id}/ofertas_producto")
    public ResponseEntity<?> getProductoOfertas(@PathVariable Long id){
        Producto producto = productoService.getProducto(id);
        if(producto != null){
            List<Oferta> ofertas = producto.getOfertas();
            return ResponseEntity.ok(ofertas);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

}
