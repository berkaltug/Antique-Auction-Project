package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.domain.response.SaleResponse;

import java.util.List;

public interface SaleService {
    void insertSale(Sale sale);
    List<SaleResponse> getUserSales();
    SaleResponse getSaleForAntique(Integer id);
}

