package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.Carrito;
import com.ApiRest.SkillChallengeApiRest.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito getCarrito(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito updateCarrito(Long id, Carrito updateCarrito) {
        Carrito existingCarrito = carritoRepository.findById(id).orElse(null);
        if (existingCarrito != null) {
            existingCarrito.setCantidad(updateCarrito.getCantidad());
            return carritoRepository.save(existingCarrito);
        }
        return null;
    }

    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    public void deleteCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}
