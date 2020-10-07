package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AntiqueConverter {
    public static Antique convert(AntiqueRequest antiqueRequest){
        Antique antique = new Antique();
        antique.setId(antiqueRequest.getId());
        antique.setName(antiqueRequest.getName());
        antique.setDescription(antiqueRequest.getDescription());
        antique.setPrice(antiqueRequest.getPrice());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        antique.setDeadline(LocalDateTime.parse(antiqueRequest.getDeadline(),formatter));
        //no need to convert latest bid
        return antique;

    }
}
