package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.Resenia;
import com.ApiRest.SkillChallengeApiRest.repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaService {
    @Autowired
    private ReseniaRepository reseniaRepository;

    public Resenia createResenia(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    public Resenia getResenia(Long id) {
        return reseniaRepository.findById(id).orElse(null);
    }

    public Resenia updateResenia(Long id, Resenia updateResenia) {
        Resenia existingResenia = reseniaRepository.findById(id).orElse(null);
        if (existingResenia != null) {
            existingResenia.setContenido(updateResenia.getContenido());
            existingResenia.setPuntuacion(updateResenia.getPuntuacion());
            existingResenia.setEstatus(updateResenia.getEstatus());
            return reseniaRepository.save(existingResenia);
        }
        return null;
    }

    public List<Resenia> getAllResenias() {
        return reseniaRepository.findAll();
    }

    public void deleteResenia(Long id) {
        reseniaRepository.deleteById(id);
    }
}
