package com.github.lltal.task2.repositories;

import com.github.lltal.task2.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query(value = "select * from usr order by last_bot_usage limit :limit", nativeQuery = true)
    Collection<CustomUser> findByLastUsageTime(@Param("limit") int limit);
}
