package com.scopic.antiqueauction.repository;

import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    List<Sale> findAllByBuyer(User user);
}
