package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.Exception.CompradorException;
import com.ApiRest.SkillChallengeApiRest.entity.Comprador;
import com.ApiRest.SkillChallengeApiRest.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorService {
    @Autowired
    private CompradorRepository compradorRepository;

    public Comprador createComprador(Comprador comprador) throws CompradorException {
        if (this.validarEmail(comprador.getEmail())){
            return compradorRepository.save(comprador);
        }else{
            throw new CompradorException("Correo repetido");
        }
    }

    public Boolean validarEmail(String email){
        System.out.println("\n"+this.findByEmail(email)+"\n");
        if (this.findByEmail(email)!=null){
            return false;
        }
        return true;
    }

    public Comprador getComprador(Long id) {
        return compradorRepository.findById(id).orElse(null);
    }
    public Comprador findByEmail(String email) {
        return compradorRepository.findByEmail(email);
        }


    public Comprador updateComprador(Long id, Comprador updateComprador) {
        Comprador existingComprador = compradorRepository.findById(id).orElse(null);
        if (existingComprador!= null) {
            existingComprador.setNombre(updateComprador.getNombre());
            existingComprador.setAppPaterno(updateComprador.getAppPaterno());
            existingComprador.setAppMaterno(updateComprador.getAppMaterno());
            existingComprador.setEstatus(updateComprador.getEstatus());
            existingComprador.setTelefono(updateComprador.getTelefono());
            existingComprador.setEmail(updateComprador.getEmail());
            existingComprador.setContrasenia(updateComprador.getContrasenia());
            return compradorRepository.save(existingComprador);
        }
        return null;
    }

    public List<Comprador> getAllCompradores() {
        return compradorRepository.findAll();
    }

    public void deleteComprador(Long id) {
        compradorRepository.deleteById(id);
    }
}
