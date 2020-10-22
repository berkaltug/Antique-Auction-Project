package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.converter.PastBidResponseConverter;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.response.PastBidResponse;
import com.scopic.antiqueauction.domain.response.SaleResponse;
import com.scopic.antiqueauction.service.PastBidService;
import com.scopic.antiqueauction.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/sale")
public class SaleController {

    private SaleService saleService;
    private PastBidService pastBidService;

    public SaleController(SaleService saleService, PastBidService pastBidService) {
        this.saleService = saleService;
        this.pastBidService = pastBidService;
    }

    //for antique item page
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

    @GetMapping("/user-bids")
    public ResponseEntity<List<PastBidResponse>> getUserPastBids(){
        List<PastBid> userBids = pastBidService.getUsersLatestBidsForAntiques();
        List<PastBidResponse> responses = userBids.stream().map(PastBidResponseConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
}
