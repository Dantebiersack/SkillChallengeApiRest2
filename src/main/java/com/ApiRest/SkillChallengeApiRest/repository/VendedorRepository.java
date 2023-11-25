package com.ApiRest.SkillChallengeApiRest.repository;

import com.ApiRest.SkillChallengeApiRest.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Vendedor findByEmail(String email);
}
