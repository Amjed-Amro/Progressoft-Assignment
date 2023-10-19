package com.bloomberg.fxdeals.repository;

import com.bloomberg.fxdeals.entities.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FxDealsRepository extends JpaRepository<FxDeal, Long> {

    Optional<FxDeal> getFxDealByDealId(Integer id);

}
