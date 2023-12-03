package com.github.lltal.task2.repositories;

import com.github.lltal.task2.domain.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CalculationResultRepository extends JpaRepository<CalculationResult, Long> {
    @Query(value = "select * from calculation c" +
            " join usr on c.usr_id = usr.id " +
            " order by usr.last_bot_usage" +
            " limit :limit", nativeQuery = true)
    Collection<CalculationResult> findLastOperations(@Param("limit") int limit);
}
