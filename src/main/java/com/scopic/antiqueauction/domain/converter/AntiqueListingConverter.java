package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.response.AntiqueListingResponse;

import java.math.BigDecimal;
import java.util.List;

public class AntiqueListingConverter {
    public static AntiqueListingResponse convert(final Antique antique, final List<String> images){
        final AntiqueListingResponse response=new AntiqueListingResponse();
        response.setId(antique.getId());
        response.setName(antique.getName());
        response.setDescription(antique.getDescription());
        response.setDisplayImage(images.get(0));
        response.setPrice((antique.getLatestBid()==null) ? antique.getPrice() : antique.getLatestBid());
        response.setDeadline(antique.getDeadline().toString());
        return response;
    }
}
