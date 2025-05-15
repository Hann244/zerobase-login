package com.example.zerobaselogin.logs.service;

import com.example.zerobaselogin.logs.entity.Logs;
import com.example.zerobaselogin.logs.repository.LogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private final LogsRepository logsRepository;

    @Override
    public void add(String text) {

        logsRepository.save(Logs.builder()
                .text(text)
                .regDate(LocalDateTime.now())
                .build());

    }
}
