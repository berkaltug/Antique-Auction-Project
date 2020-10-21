package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;

import java.util.List;

public interface SaleService {
    void insertSale(Sale sale);
    List<Sale> getUserSales(User user);
}

