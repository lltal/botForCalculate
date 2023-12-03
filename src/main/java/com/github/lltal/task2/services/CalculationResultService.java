package com.github.lltal.task2.services;

import com.github.lltal.task2.domain.CalculationResult;
import com.github.lltal.task2.repositories.CalculationResultRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CalculationResultService {
    @Autowired
    private CalculationResultRepository calculationResultRepo;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void save(CalculationResult result){
        calculationResultRepo.save(result);
    }

    public Collection<CalculationResult> getLastOperation(int limit){
        return calculationResultRepo.findLastOperations(limit);
    }
}
