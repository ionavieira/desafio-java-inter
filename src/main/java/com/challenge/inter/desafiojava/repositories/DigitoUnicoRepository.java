package com.challenge.inter.desafiojava.repositories;

import com.challenge.inter.desafiojava.entity.DigitoUnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DigitoUnicoRepository extends JpaRepository<DigitoUnico, Long> {
    Optional<DigitoUnico> findByNAndK(String n, Integer k);
}
