package com.ApiRest.SkillChallengeApiRest.repository;

import com.ApiRest.SkillChallengeApiRest.entity.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    Comprador findByEmail(String email);
}
