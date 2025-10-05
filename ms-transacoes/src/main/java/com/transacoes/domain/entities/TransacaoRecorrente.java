package com.transacoes.domain.entities;

import com.transacoes.domain.enums.TipoRecorrencia;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoRecorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private Long contaId;

    private BigDecimal valorBase;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoRecorrencia tipoRecorrencia;

    private Integer totalParcelas;

    private Integer parcelasGeradas = 0;

    private LocalDate dataInicio;

    private LocalDate proximaDataGeracao;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        if(this.uuid == null){
            this.uuid = UUID.randomUUID();
        }
        this.createdAt = LocalDateTime.now();
    }
}
