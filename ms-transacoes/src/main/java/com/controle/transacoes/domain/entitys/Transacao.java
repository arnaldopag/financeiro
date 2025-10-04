package com.controle.transacoes.domain.entitys;

import com.controle.transacoes.domain.enums.TipoRecorrencia;
import com.controle.transacoes.domain.enums.TipoTransacao;
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
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private Long contaId;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    private BigDecimal valor;

    private LocalDate dataTransacao;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoRecorrencia tipoRecorrencia;

    private Integer numeroParcelas;

    private Long metodoPagamentoId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        if(this.uuid == null){
            this.uuid = UUID.randomUUID();
        }
        this.createdAt = LocalDateTime.now();
    }

}
