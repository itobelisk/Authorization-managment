package com.auth.server.entity.position.response;

import com.auth.server.entity.positionscategory.response.PositionsCategoriesResponse;
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
public class PositionResponse {

    private PositionsCategoriesResponse positionsCategoriesResponse;
    private Long id;
    private String positionName;
    private String positionIcon;
    private String positionDetails;

}
