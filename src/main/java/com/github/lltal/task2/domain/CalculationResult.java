package com.github.lltal.task2.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "calculation")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NamedEntityGraph(
        name = "CalculationResult",
        attributeNodes = {
                @NamedAttributeNode(value = "user")
        })
public class CalculationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr.id", updatable = false, nullable = false)
    @NonNull
    private CustomUser user;

    @Column(name = "result")
    @NonNull
    private String calculationResult;
}
