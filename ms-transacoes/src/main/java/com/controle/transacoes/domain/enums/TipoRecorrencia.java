package com.controle.transacoes.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum TipoRecorrencia {

    MENSAL("M", "Mensal"),
    ANUAL("A", "Anual"),
    UNICA("U", "Unica"),
    PARCELADA("P","Parcelada");

    private final String value;
    private final String description;

    private static final Map<String, TipoRecorrencia> VALUE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoRecorrencia::getValue, e -> e));

    private static final Map<String, TipoRecorrencia> DESCRIPTION_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoRecorrencia::getDescription, e -> e));

    public static TipoRecorrencia fromValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + value));
    }

    public static TipoRecorrencia fromDescription(String description) {
        return Optional.ofNullable(DESCRIPTION_MAP.get(description))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + description));
    }
}
