package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.Transaccion;
import com.ApiRest.SkillChallengeApiRest.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion createTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public Transaccion getTransaccion(Long id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    public Transaccion updateTransaccion(Long id, Transaccion updateTransaccion) {
        Transaccion existingTransaccion = transaccionRepository.findById(id).orElse(null);
        if (existingTransaccion != null) {
            existingTransaccion.setCalleEnvio(updateTransaccion.getCalleEnvio());
            existingTransaccion.setColoniaEnvio(updateTransaccion.getColoniaEnvio());
            existingTransaccion.setEstadoEnvio(updateTransaccion.getEstadoEnvio());
            existingTransaccion.setNumEnvio(updateTransaccion.getNumEnvio());
            existingTransaccion.setMontoTotal(updateTransaccion.getMontoTotal());
            existingTransaccion.setEstatus(updateTransaccion.getEstatus());
            existingTransaccion.setPaisEnvio(updateTransaccion.getPaisEnvio());
            existingTransaccion.setNoTarjeta(updateTransaccion.getNoTarjeta());
            existingTransaccion.setMetodoPago(updateTransaccion.getMetodoPago());
            existingTransaccion.setFechaEnvio(updateTransaccion.getFechaEnvio());
            return transaccionRepository.save(existingTransaccion);
        }
        return null;
    }

    public List<Transaccion> getAllTransacciones() {
        return transaccionRepository.findAll();
    }

    public void deleteTransaccion(Long id) {
        transaccionRepository.deleteById(id);
    }


}

