package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.Exception.ProductoException;
import com.ApiRest.SkillChallengeApiRest.entity.Producto;
import com.ApiRest.SkillChallengeApiRest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto createProducto(Producto producto) {
        if(producto.getNombre().isEmpty()){
            throw new ProductoException("Producto no valido");
        }
        return productoRepository.save(producto);
    }

    public Producto getProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto updateProducto(Long id, Producto updateProducto) {
        Producto existingProducto = productoRepository.findById(id).orElse(null);
        if (existingProducto != null) {
            existingProducto.setNombre(updateProducto.getNombre());
            existingProducto.setCategoria(updateProducto.getCategoria());
            existingProducto.setDescripcion(updateProducto.getDescripcion());
            existingProducto.setEstatus(updateProducto.getEstatus());
            existingProducto.setPrecioUnitario(updateProducto.getPrecioUnitario());
            existingProducto.setDetallesEspecificos(updateProducto.getDetallesEspecificos());
            return productoRepository.save(existingProducto);
        }
        return null;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
