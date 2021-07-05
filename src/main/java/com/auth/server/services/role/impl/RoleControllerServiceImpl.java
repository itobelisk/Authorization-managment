package com.auth.server.services.role.impl;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.response.PositionsResponse;
import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.entity.role.response.RoleResponse;
import com.auth.server.exception.AdminCanNotBeUpdateException;
import com.auth.server.mapper.PositionsMapper;
import com.auth.server.mapper.RoleMapper;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.repository.RoleRepository;
import com.auth.server.services.role.RoleControllerService;
import com.auth.server.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleControllerServiceImpl implements RoleControllerService {
     private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_START = "ROLE_";
    private final PositionsRepository positionsRepository;
    private final PositionsMapper positionsMapper;

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


    private String getFinalNameCollection(RoleRequest roleRequest) {
        String finalRoleName = "";
        finalRoleName = roleRequest.getRoleRequestsCollection().iterator().next().getName().contains(" ") ? roleRequest.getRoleRequestsCollection().iterator().next().getName().replace(" ", "_") : roleRequest.getRoleRequestsCollection().iterator().next().getName().toUpperCase();
        log.info("finalRoleName {} ", finalRoleName);
        return ROLE_START + finalRoleName.toUpperCase();
    }

    public BaseResponse<?> manageCollection(RoleRequest roleRequest) {
        RoleResponse response = null;
        RoleResponse roleResponse = null;
        for (int i = 0; i < roleRequest.getRoleRequestsCollection().size(); i++) {
            if (roleRequest.getRoleRequestsCollection().iterator().next().getName().toLowerCase().contains("admin"))
                throw new AdminCanNotBeUpdateException();
            String roleName = getFinalNameCollection(roleRequest);

            try {
                if (roleRequest.getIsSelected()) {
                    if (roleRepository.existsByName(roleName) == null) {
                        Role role = roleMapper.toRoleEntity(roleName);
                        response = roleMapper.toResponse(roleRepository.save(role));
                    } else {
                        roleResponse = roleMapper.toSingleRole(roleRepository.existsByName(roleName));
                        roleResponse.setId(roleResponse.getId());
                        roleResponse.setName(roleName);
                        Role role = roleMapper.getRoleEntity(roleResponse);
                        response = roleMapper.toResponse(roleRepository.save(role));
                    }
                } else if (!roleRequest.getIsSelected()) {
                    roleResponse = roleMapper.toSingleRole(roleRepository.existsByName(roleName));
                    roleRepository.deleteById(roleResponse.getId());
                    response = roleResponse;
                }
            } catch (Exception e) {
                return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }

}
