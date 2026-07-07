package com.onpe.presidenciales.repository;

import com.onpe.presidenciales.model.Ambito;
import com.onpe.presidenciales.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findByAmbito(Ambito ambito);
}