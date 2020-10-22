package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.response.SaleResponse;
import com.scopic.antiqueauction.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/antique/{antiqueid}")
    public ResponseEntity<SaleResponse> getSaleInfo(@PathVariable("antiqueid") Integer antiqueid){
        SaleResponse response=saleService.getSaleForAntique(antiqueid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user-sales")
    public ResponseEntity<List<SaleResponse>> getUserSales(){
        List<SaleResponse> response=saleService.getUserSales();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
