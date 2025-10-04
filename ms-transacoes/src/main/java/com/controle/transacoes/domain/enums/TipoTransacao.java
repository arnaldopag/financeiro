package com.controle.transacoes.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum TipoTransacao {
    RECEITA("R", "Receita"),
    DESPESA("D", "Despesa"),
    INVESTIMENTO("I", "Investimento");

    private final String value;
    private final String description;

    private static final Map<String, TipoTransacao> VALUE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoTransacao::getValue, e -> e));

    private static final Map<String, TipoTransacao> DESCRIPTION_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(TipoTransacao::getDescription, e -> e));

    public static TipoTransacao fromValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + value));
    }

    public static TipoTransacao fromDescription(String description) {
        return Optional.ofNullable(DESCRIPTION_MAP.get(description))
                .orElseThrow(() -> new IllegalArgumentException("Tipo Transacao Invalido " + description));
    }

}
