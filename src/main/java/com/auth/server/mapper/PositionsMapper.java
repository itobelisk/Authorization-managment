package com.auth.server.mapper;

import com.auth.server.entity.position.Positions;
import com.auth.server.entity.position.response.PositionsResponse;
import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.request.RoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PositionsMapper {
    private final PositionCategoryMapper positionCategoryMapper;
    private static final String ROLE_START = "ROLE_";

    public List<PositionsResponse> toResponse(List<Positions> all) {
        return all.stream()
                .map(e -> new PositionsResponse(
                        positionCategoryMapper.toPositionCategoryResponse(e.getPositionsCategories()),
                        e.getId(),
                        e.getPositionName(),
                        e.getPositionIcon(),
                        e.getPositionDetails(),
                        false
                ))
                .collect(Collectors.toList());
    }

    public List<PositionsResponse> getRolesPositions(List<Positions> all, List<Role> roleList) {
        Set<PositionsResponse> setResponse = new LinkedHashSet<>();
        boolean isSelectedRole =false;
            for (Positions value : all) {
                isSelectedRole = checkSelectedRoles(roleList,value);
                PositionsResponse positions = new PositionsResponse();
                positions.setId(value.getId());
                positions.setPositionIcon(value.getPositionIcon());
                positions.setPositionName(value.getPositionName());
                positions.setPositionDetails(value.getPositionDetails());
                positions.setPositionsCategoriesResponse(positionCategoryMapper.toPositionCategoryResponse(value.getPositionsCategories()));
                positions.setSelected(isSelectedRole);
                assert false;
                setResponse.add(positions);

        }
        return new ArrayList<>(setResponse);
    }

    private boolean checkSelectedRoles(List<Role>  role, Positions value) {
        return role.stream().anyMatch(item -> getFinalName(value.getPositionName()).contains(item.getName()));
    }

    private String getFinalName(String value) {
        String finalRoleName = "";
        finalRoleName = value.contains(" ") ? value.replace(" ", "_") : value.toUpperCase();
        return ROLE_START + finalRoleName.toUpperCase();
    }

}
