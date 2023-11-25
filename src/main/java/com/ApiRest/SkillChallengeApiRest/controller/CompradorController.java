package com.ApiRest.SkillChallengeApiRest.controller;

import com.ApiRest.SkillChallengeApiRest.Exception.CompradorException;
import com.ApiRest.SkillChallengeApiRest.Exception.OfertaException;
import com.ApiRest.SkillChallengeApiRest.entity.Carrito;
import com.ApiRest.SkillChallengeApiRest.entity.Comprador;
import com.ApiRest.SkillChallengeApiRest.entity.Resenia;
import com.ApiRest.SkillChallengeApiRest.entity.Transaccion;
import com.ApiRest.SkillChallengeApiRest.service.CompradorService;
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
@RequestMapping("/compradores")
@Tag(name = "Compradores")
@CrossOrigin(origins="*")
public class CompradorController {
    @Autowired
    private CompradorService compradorService;

    @Operation(summary = "Create a new comprador")
    @PostMapping("/create")
    public Comprador createComprador(@Valid @RequestBody Comprador comprador) {
        return compradorService.createComprador(comprador);
    }

    @Operation(summary = "Get a comprador by ID")
    @GetMapping("/{id}")
    public Comprador getComprador(@PathVariable Long id) {
        return compradorService.getComprador(id);
    }

    @Operation(summary = "Update a comprador by ID")
    @PutMapping("/{id}")
    public Comprador updateComprador(@PathVariable Long id,@Valid @RequestBody Comprador updateComprador) {
        return compradorService.updateComprador(id, updateComprador);
    }

    @Operation(summary = "Delete a comprador by ID")
    @DeleteMapping("/{id}")
    public void deleteComprador(@PathVariable Long id) {
        compradorService.deleteComprador(id);
    }

    @Operation(summary = "Get all compradores")
    @GetMapping("/getAll")
    public List<Comprador> getAllcompradores() {
        return compradorService.getAllCompradores();
    }

    @Operation(summary = "Get the resenias de un comprador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de resenias del comprador",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resenia.class),
                            examples = @ExampleObject(value = "[{\"id\": 1, \"contenido\": \"Contenido de resenia\", \"puntuacion\": 10, \"estatus\": 1}]")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comprador no encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Comprador no encontrado")
                    )
            )
    })
    @GetMapping("/{id}/resenias_comprador")
    public ResponseEntity<?> getCompradorResenias(@PathVariable Long id){
        Comprador comprador = compradorService.getComprador(id);
        if(comprador != null){
            List<Resenia> resenias = comprador.getResenias();
            return ResponseEntity.ok(resenias);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comprador no encontrado");
        }
    }

    @Operation(summary = "Get Carrito de un comprador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrito del comprador",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Carrito.class),
                            examples = @ExampleObject(value = "[{\"id\": 1, \"cantidad\": 1, \"prodcuto_id\": 1, \"comprador_id\": 1}]")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comprador no encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Comprador no encontrado")
                    )
            )
    })
    @GetMapping("/{id}/carritos_comprador")
    public ResponseEntity<?> getCompradorCarritos(@PathVariable Long id) {
        Comprador comprador = compradorService.getComprador(id);
        if (comprador != null) {
            List<Carrito> carritos = comprador.getCarritos();
            return ResponseEntity.ok(carritos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comprador no encontrado");
        }
    }

    @Operation(summary = "Get the Transacciones de un comprador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de transacciones del comprador",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Transaccion.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comprador no encontrado",
                    content = @Content(
                            mediaType = "text/plain"
                    )
            )
    })
    @GetMapping("/{id}/transacciones_comprador")
    public ResponseEntity<?> getTransaccionesComprador(@PathVariable Long id){
        Comprador comprador = compradorService.getComprador(id);
        if(comprador != null){
            List<Transaccion> transacciones = comprador.getTransacciones();
            return ResponseEntity.ok(transacciones);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comprador no encontrado");
        }
    }

}
