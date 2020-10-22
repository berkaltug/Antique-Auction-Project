package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.Sale;
import com.scopic.antiqueauction.domain.response.SaleResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleResponseConverter {
    public static SaleResponse convert(Sale sale){
        final SaleResponse response=new SaleResponse();
        response.setAntique(sale.getAntique().getName());
        response.setBuyer(sale.getBuyer().getUsername());
        response.setPrice(sale.getPrice());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setDate(sale.getDate().format(formatter));
        return response;
    }
}
