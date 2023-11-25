package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.Vendedor;
import com.ApiRest.SkillChallengeApiRest.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;

    public Vendedor createVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor getVendedor(Long id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    public Vendedor findByEmail(String email) {
        return vendedorRepository.findByEmail(email);
    }

    public Vendedor updateVendedor(Long id, Vendedor updateVendedor) {
        Vendedor existingVendedor = vendedorRepository.findById(id).orElse(null);
        if (existingVendedor != null) {
            existingVendedor.setNombre(updateVendedor.getNombre());
            existingVendedor.setContrasenia(updateVendedor.getContrasenia());
            existingVendedor.setEmail(updateVendedor.getEmail());
            existingVendedor.setEstatus(updateVendedor.getEstatus());
            return vendedorRepository.save(existingVendedor);
        }
        return null;
    }

    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    public void deleteVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }

}
