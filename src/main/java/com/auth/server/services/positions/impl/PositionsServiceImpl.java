package com.auth.server.services.positions.impl;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.Positions;
import com.auth.server.entity.position.response.PositionResponse;
import com.auth.server.mapper.PositionsMapper;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.services.positions.PositionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionsServiceImpl implements PositionsService {
    private final PositionsRepository positionsRepository;

    @Override
    public Map<String, List<Positions>> all() {
        LinkedHashMap<String, List<Positions>> reverseSortedMap = new LinkedHashMap<>();
        List<Positions> response = positionsRepository.findAll();

        Map<String, List<Positions>> responseMap = response.stream()
                .collect(Collectors.groupingBy(e -> e.getPositionsCategories().getPositionCategoryName()));

        responseMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }
}
