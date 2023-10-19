package com.bloomberg.fxdeals.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "FxDeal")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FxDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "dealId", unique = true)
    private Integer dealId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private String dealTimeStamp;
    private Double dealAmount;
    private LocalDateTime creationDate;
    private String createdBy;


}
