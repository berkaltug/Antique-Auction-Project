package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class AntiqueResponseConverter {
    public static AntiqueResponse convert(final Antique antique , final List<BigDecimal> bids, List<String> imagePaths){
        final AntiqueResponse antiqueResponse=new AntiqueResponse();
        antiqueResponse.setId(antique.getId());
        antiqueResponse.setName(antique.getName());
        antiqueResponse.setDescription(antique.getDescription());
        antiqueResponse.setPrice(antique.getPrice());
        antiqueResponse.setLatestBid(antique.getLatestBid());
        antiqueResponse.setPastBids(bids);
        antiqueResponse.setImagePath(imagePaths);
        antiqueResponse.setDeadline(antique.getDeadline().toString());
        return antiqueResponse;
    }
}
