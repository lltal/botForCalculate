package com.github.lltal.task2.security;

import jakarta.annotation.Generated;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdminRights {
    @Getter
    Set<String> admins = new HashSet<>();

    @PostConstruct
    private void postConstruct(){
        admins.add("id1517");
        admins.add("wdeath");
    }
}
