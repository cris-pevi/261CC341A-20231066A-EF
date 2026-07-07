package com.onpe.presidenciales.dto;

public record ResultadoDTO(
        String nombre,
        String partido,
        Long votos,
        Double porcentaje
) {}