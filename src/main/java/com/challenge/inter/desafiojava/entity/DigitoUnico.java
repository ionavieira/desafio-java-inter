package com.challenge.inter.desafiojava.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "digito_unico")
public class DigitoUnico {

    @Column
    @NotNull
    private String n;

    @Column
    private Integer k;

    @Id
    private Integer id;
}
