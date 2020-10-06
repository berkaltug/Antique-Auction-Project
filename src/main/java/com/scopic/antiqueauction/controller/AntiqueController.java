package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.converter.AntiqueConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.service.AntiqueImageService;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/antique")
public class AntiqueController {

    private AntiqueService antiqueService;


    @Autowired
    public AntiqueController(AntiqueService antiqueService) {
        this.antiqueService = antiqueService;
    }

    @GetMapping("/list")
    public Page<Antique> getAllAntiques(@RequestParam("page") Integer page, @RequestParam("sort")Sort.Direction direction){
        return antiqueService.getAllAntiques(page, direction);
    }

    @GetMapping("/see/{id}")
    public ResponseEntity<?> getAntique(@PathVariable("id")Integer id){
        Optional<AntiqueResponse> optionalAntique=antiqueService.getAntiqueById(id);
        if(optionalAntique.isPresent()){
            return new ResponseEntity<>(optionalAntique.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Could not find an item for given id",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAntique(AntiqueRequest request){
        try{
            Optional<Antique> optionalAntique = antiqueService.addAntique(request);
            if(optionalAntique.isPresent()){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
