package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.DetalleTransaccion;
import com.ApiRest.SkillChallengeApiRest.repository.DetalleTransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetalleTransaccionService {

    @Autowired
    private DetalleTransaccionRepository detalleTransaccionRepository;

    public DetalleTransaccion createDetalleTransaccion(DetalleTransaccion detalleTransaccion) {
        return detalleTransaccionRepository.save(detalleTransaccion);
    }

    public DetalleTransaccion getDetalleTransaccion(Long id) {
        return detalleTransaccionRepository.findById(id).orElse(null);
    }

    public DetalleTransaccion updateDetalleTransaccion(Long id, DetalleTransaccion updateDetalleTransaccion) {
        DetalleTransaccion existingDetalleTransaccion = detalleTransaccionRepository.findById(id).orElse(null);
        if (existingDetalleTransaccion != null) {
            existingDetalleTransaccion.setCantidad(updateDetalleTransaccion.getCantidad());
            return detalleTransaccionRepository.save(existingDetalleTransaccion);
        }
        return null;
    }

    public List<DetalleTransaccion> getAllDetalleTransacciones() {
        return detalleTransaccionRepository.findAll();
    }

    public void deleteDetalleTransaccion(Long id) {
        detalleTransaccionRepository.deleteById(id);
    }

}
