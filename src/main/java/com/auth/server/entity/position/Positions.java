package com.auth.server.entity.position;

import com.auth.server.entity.base.BaseEntity;
import com.auth.server.entity.positionscategory.PositionsCategories;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions")
public class Positions extends BaseEntity {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private PositionsCategories positionsCategories;
    private String positionName;
    private String positionIcon;
    @Lob
    private String positionDetails;
}
