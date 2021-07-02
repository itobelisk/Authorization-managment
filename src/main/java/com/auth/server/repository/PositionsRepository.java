package com.auth.server.repository;

import com.auth.server.entity.position.Positions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Positions,Long> {

}
