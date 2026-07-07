package com.onpe.presidenciales.service;

import com.onpe.presidenciales.dto.ResultadoDTO;
import com.onpe.presidenciales.model.Ambito;
import com.onpe.presidenciales.model.Candidato;
import com.onpe.presidenciales.model.Resultado;
import com.onpe.presidenciales.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    public List<ResultadoDTO> obtenerPorAmbito(Ambito ambito) {
        return resultadoRepository.findByAmbito(ambito).stream()
                .map(r -> new ResultadoDTO(
                        r.getCandidato().getNombre(),
                        r.getCandidato().getPartido(),
                        r.getVotos(),
                        r.getPorcentaje()
                ))
                .toList();
    }

    public List<ResultadoDTO> obtenerTotal() {
        List<Resultado> todos = resultadoRepository.findAll();

        Map<Candidato, Long> votosPorCandidato = todos.stream()
                .collect(Collectors.groupingBy(
                        Resultado::getCandidato,
                        Collectors.summingLong(Resultado::getVotos)
                ));

        long totalGeneral = votosPorCandidato.values().stream()
                .mapToLong(Long::longValue)
                .sum();

        return votosPorCandidato.entrySet().stream()
                .map(e -> new ResultadoDTO(
                        e.getKey().getNombre(),
                        e.getKey().getPartido(),
                        e.getValue(),
                        (e.getValue() * 100.0) / totalGeneral
                ))
                .toList();
    }
}