package com.controle.domain.entities;

import com.controle.domain.enums.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<MetodoPagamento> metodoPagamento;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(nullable = false)
    private BigDecimal saldoAtual;

    @Column(nullable = false)
    private Boolean possuiRendimento;

    @Column(nullable = false)
    private Long numeroConta;

    @Column(nullable = false)
    private String userId;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
        if(this.saldoAtual == null){
            this.saldoAtual = BigDecimal.ZERO;
        }
        if(this.possuiRendimento == null){
            this.possuiRendimento = false;
        }
        this.createdAt = LocalDateTime.now();
    }


}
