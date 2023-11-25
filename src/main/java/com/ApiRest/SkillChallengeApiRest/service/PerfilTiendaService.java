package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.PerfilTienda;
import com.ApiRest.SkillChallengeApiRest.repository.PerfilTiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PerfilTiendaService {
    @Autowired
    private PerfilTiendaRepository perfilTiendaRepository;

    public PerfilTienda createPerfilTienda(PerfilTienda perfilTienda) {
        return perfilTiendaRepository.save(perfilTienda);
    }

    public PerfilTienda getPerfilTienda(Long id) {
        return perfilTiendaRepository.findById(id).orElse(null);
    }

    public PerfilTienda updatePerfilTienda(Long id, PerfilTienda updatePerfilTienda) {
        PerfilTienda existingPerfilTienda = perfilTiendaRepository.findById(id).orElse(null);
        if (existingPerfilTienda != null) {
            existingPerfilTienda.setNombreTienda(updatePerfilTienda.getNombreTienda());
            existingPerfilTienda.setDescripcion(updatePerfilTienda.getDescripcion());
            existingPerfilTienda.setEstatus(updatePerfilTienda.getEstatus());
            existingPerfilTienda.setTelefono(updatePerfilTienda.getTelefono());
            return perfilTiendaRepository.save(existingPerfilTienda);
        }
        return null;
    }

    public List<PerfilTienda> getAllPerfilTiendas() {
        return perfilTiendaRepository.findAll();
    }

    public void deletePerfilTienda(Long id) {
        perfilTiendaRepository.deleteById(id);
    }

}
