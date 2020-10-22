package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.response.PastBidResponse;

import java.time.format.DateTimeFormatter;

public class PastBidResponseConverter {
    public static PastBidResponse convert(PastBid pastBid){
        final PastBidResponse response=new PastBidResponse();
        response.setAntique(pastBid.getAntique().getName());
        response.setBid(pastBid.getBid());
        response.setUsername(pastBid.getUser().getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setTime(pastBid.getTime().format(formatter));
        response.setStatus(pastBid.getStatus());
        return response;
    }

}
