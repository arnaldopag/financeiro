package com.controle.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum TipoConta {
    CORRENTE("C", "Corrente"),
    POUPANCA("P", "Poupanca"),
    INVESTIMENTO("I", "Investimento"),
    CARTEIRA("CA", "Carteira");

    private static final Map<String, TipoConta> VALUE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoConta::getValue, e -> e));
    private static final Map<String, TipoConta> DESCRIPTION_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoConta::getDescription, e -> e));
    private final String value;
    private final String description;

    public static TipoConta fromValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + value));
    }

    public static TipoConta fromDescription(String description) {
        return Optional.ofNullable(DESCRIPTION_MAP.get(description))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + description));
    }
}
