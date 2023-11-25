package com.ApiRest.SkillChallengeApiRest.GlobalExecptionHandler;

import com.ApiRest.SkillChallengeApiRest.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductoNotFoundException(ProductoNotFoundException ex){
        return new ErrorResponse("Producto no encontrado", ex.getMessage());
    }
    @ExceptionHandler(CarritoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCarritoNotFoundException(CarritoException ex){
        return new ErrorResponse("Carrito no encontrado", ex.getMessage());
    }

    @ExceptionHandler(CompradorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCompradorNotFoundException(CompradorException ex){
        return new ErrorResponse("Comprador no encontrado", ex.getMessage());
    }

    @ExceptionHandler(OfertaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleOfertaNotFoundException(OfertaException ex){
        return new ErrorResponse("Oferta no encontrado", ex.getMessage());
    }

    @ExceptionHandler(PerfilTiendaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePerfilTiendaNotFoundException(PerfilTiendaException ex){
        return new ErrorResponse("Perfil de Tienda no encontrado", ex.getMessage());
    }

    @ExceptionHandler(ReseniaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleReseniaNotFoundException(ReseniaException ex){
        return new ErrorResponse("Resenia no encontrado", ex.getMessage());
    }

    @ExceptionHandler(TransaccionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTransaccionNotFoundException(TransaccionException ex){
        return new ErrorResponse("Transaccion no encontrado", ex.getMessage());
    }

    @ExceptionHandler(VendedorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleVendedorNotFoundException(VendedorException ex){
        return new ErrorResponse("Vendedor no encontrado", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ValidationErrorResponse("Validación fallida", errorMessages);
    }
}
