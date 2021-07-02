package com.auth.server.entity.positionscategory;

import com.auth.server.entity.base.BaseEntity;
import com.auth.server.entity.position.Positions;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions_category")
public class PositionsCategories extends BaseEntity {
    @JsonManagedReference
    @OneToMany(mappedBy="positionsCategories")
    private List<Positions> positions;

    private String positionCategoryName;
}
