package com.auth.server.mapper;

import com.auth.server.entity.positionscategory.PositionsCategories;
import com.auth.server.entity.positionscategory.response.PositionsCategoriesResponse;
import org.springframework.stereotype.Component;

@Component
public class PositionCategoryMapper {
    public PositionsCategoriesResponse toPositionCategoryResponse(PositionsCategories positionsCategories) {
        return PositionsCategoriesResponse.builder().id(positionsCategories.getId()).positionCategoryName(positionsCategories.getPositionCategoryName()).build();
    }
}
