package com.auth.server.entity.positionscategory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionsCategoriesResponse {
    private Long id;
    private String positionCategoryName;
}
