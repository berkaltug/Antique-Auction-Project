package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.domain.converter.SaleResponseConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.domain.response.SaleResponse;
import com.scopic.antiqueauction.repository.AntiqueRepository;
import com.scopic.antiqueauction.repository.SaleRepository;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.SaleService;
import com.scopic.antiqueauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private UserService userService;
    private AntiqueRepository antiqueRepository;
    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, UserService userService, AntiqueRepository antiqueRepository) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.antiqueRepository = antiqueRepository;
    }

    @Override
    public void insertSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public List<SaleResponse> getUserSales() {

        List<Sale> sales=saleRepository.findAllByBuyer(userService.findLoggedInUser());
        return sales.stream().map(SaleResponseConverter::convert).collect(Collectors.toList());
    }
    @Override
    public SaleResponse getSaleForAntique(Integer id){
        Optional<Antique> antique = antiqueRepository.findById(id);
        Sale sale = saleRepository.findByAntique(antique.get());
        return SaleResponseConverter.convert(sale);
    }
}
