package com.auth.server.services.positonsCategory.impl;

import com.auth.server.entity.position.Positions;
import com.auth.server.entity.positionscategory.PositionsCategories;
import com.auth.server.repository.PositionCategoryRepository;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.services.positonsCategory.PositionsCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionCategoryServiceImpl implements PositionsCategoryService {
    private final PositionCategoryRepository positionCategoryRepository;
    //Categories  full pack
    private final String[] positionCategories = {"Administration", "Management", "Financials", "Staff"};


    @Override
    public void fillData() {
        List<PositionsCategories> allCategories = positionCategoryRepository.findAll();
        if (allCategories.isEmpty()) {
            for (String positionCategory : positionCategories) {
                PositionsCategories positionsCategories = new PositionsCategories();
                positionsCategories.setPositionCategoryName(positionCategory);
                positionCategoryRepository.save(positionsCategories);
            }
        }
    }
}