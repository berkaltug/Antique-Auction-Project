package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;
import com.scopic.antiqueauction.domain.request.BidRequest;
import com.scopic.antiqueauction.domain.response.AntiqueListingResponse;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AntiqueService {
    Page<AntiqueListingResponse> getAllAntiques(int pageNo, Sort.Direction direction);
    Page<AntiqueListingResponse> getAllAntiquesLike(int pageNo, Sort.Direction direction, String str);
    Optional<AntiqueResponse> getAntiqueById(Integer id);
    Optional<Antique> addAntique(AntiqueRequest request) throws IOException;
    Optional<Antique> updateAntique(AntiqueRequest request) throws IOException;
    void deleteAntiqueById(Integer id);
    void makeBid(BidRequest request);
}
