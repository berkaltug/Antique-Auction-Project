package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.response.PastBidResponse;

public class PastBidResponseConverter {
    public static PastBidResponse convert(PastBid pastBid){
        final PastBidResponse response=new PastBidResponse();
        response.setAntiqueId(pastBid.getAntique().getId());
        response.setBid(pastBid.getBid());
        response.setUsername(pastBid.getUser().getUsername());
        response.setTime(pastBid.getTime());
        return response;
    }

}
