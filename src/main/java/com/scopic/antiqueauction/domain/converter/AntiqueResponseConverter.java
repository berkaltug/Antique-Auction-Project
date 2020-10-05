package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.entity.PastBid;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;

import java.math.BigInteger;
import java.util.List;

public class AntiqueResponseConverter {
    public static AntiqueResponse convert(final Antique antique ,final List<BigInteger> bids){
        final AntiqueResponse antiqueResponse=new AntiqueResponse();
        antiqueResponse.setId(antique.getId());
        antiqueResponse.setName(antique.getName());
        antiqueResponse.setDescription(antique.getDescription());
        antiqueResponse.setPrice(antique.getPrice());
        antiqueResponse.setImagePath(antique.getImagePath());
        antiqueResponse.setLatestBid(antique.getLatestBid());
        antiqueResponse.setPastBids(bids);
        return antiqueResponse;
    }
}
