package com.auth.server.repository;

import com.auth.server.entity.positionscategory.PositionsCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionCategoryRepository extends JpaRepository<PositionsCategories,Long> {
}
