package com.github.lltal.task2.services;

import com.github.lltal.task2.domain.CustomUser;
import com.github.lltal.task2.repositories.UserRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public CustomUser getById(Long id){
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public CustomUser save(User user){
        LocalDateTime botUsageTime = LocalDateTime.now();
        Optional<CustomUser> optionalUserFromDb = userRepository.findById(user.getId());
        CustomUser customUser = null;

        if (optionalUserFromDb.isPresent()){
            customUser = optionalUserFromDb.get();
            customUser.setLastBotUsage(botUsageTime);
        } else {
            customUser = new CustomUser(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    botUsageTime,
                    botUsageTime);
        }
        return userRepository.save(customUser);
    }

    public Collection<CustomUser> getLastUsage(int limit){
        return userRepository.findByLastUsageTime(limit);
    }
}