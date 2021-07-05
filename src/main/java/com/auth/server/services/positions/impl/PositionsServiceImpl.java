package com.auth.server.services.positions.impl;

import com.auth.server.entity.position.Positions;
import com.auth.server.entity.position.response.PositionsResponse;
import com.auth.server.entity.role.Role;
import com.auth.server.mapper.PositionsMapper;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.repository.RoleRepository;
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
    private final PositionsMapper positionsMapper;
    private final RoleRepository roleRepository;

    @Override
    public Map<String, List<PositionsResponse>> all() {
        LinkedHashMap<String, List<PositionsResponse>> reverseSortedMap = new LinkedHashMap<>();
        List<Role> roleList = roleRepository.findAll();
        List<PositionsResponse> response = positionsMapper.getRolesPositions(positionsRepository.findAll(),roleList);

        Map<String, List<PositionsResponse>> responseMap = response.stream()
                .collect(Collectors.groupingBy(e -> e.getPositionsCategoriesResponse().getPositionCategoryName()));

        responseMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }
}
