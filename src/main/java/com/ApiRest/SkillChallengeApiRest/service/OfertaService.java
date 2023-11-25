package com.ApiRest.SkillChallengeApiRest.service;

import com.ApiRest.SkillChallengeApiRest.entity.Oferta;
import com.ApiRest.SkillChallengeApiRest.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {
        @Autowired
        private OfertaRepository ofertaRepository;

        public Oferta createOferta(Oferta oferta) {
            return ofertaRepository.save(oferta);
        }

        public Oferta getOferta(Long id) {
            return ofertaRepository.findById(id).orElse(null);
        }

        public Oferta updateOferta(Long id, Oferta updateOferta) {
            Oferta existingOferta = ofertaRepository.findById(id).orElse(null);
            if (existingOferta != null) {
                existingOferta.setDescuento(updateOferta.getDescuento());
                existingOferta.setFechaValidez(updateOferta.getFechaValidez());
                return ofertaRepository.save(existingOferta);
            }
            return null;
        }

        public List<Oferta> getAllOfertas() {
            return ofertaRepository.findAll();
        }

        public void deleteOferta(Long id) {
            ofertaRepository.deleteById(id);
        }
}
