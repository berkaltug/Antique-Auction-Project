package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.repository.SaleRepository;
import com.scopic.antiqueauction.service.SaleService;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private UserService userService;
    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, UserService userService) {
        this.saleRepository = saleRepository;
        this.userService = userService;
    }


    @Override
    public void insertSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public List<Sale> getUserSales(User user) {
        return saleRepository.findAllByBuyer(userService.findLoggedInUser());
    }
}
