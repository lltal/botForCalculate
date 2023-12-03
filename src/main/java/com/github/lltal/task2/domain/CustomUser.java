package com.github.lltal.task2.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Reference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usr", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomUser {
    @Id
    @NonNull
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String userName;
    @NonNull
    private LocalDateTime firstBotUsage;
    @NonNull
    private LocalDateTime lastBotUsage;
}
