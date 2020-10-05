package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/antique")
public class AntiqueController {

    private AntiqueService antiqueService;
    private FileStorageService fileStorageService;
    @Autowired
    public AntiqueController(AntiqueService antiqueService, FileStorageService fileStorageService) {
        this.antiqueService = antiqueService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/list")
    public Page<Antique> getAllAntiques(@RequestParam("page") Integer page, @RequestParam("sort")Sort.Direction direction){
        return antiqueService.getAllAntiques(page, direction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antique> getAntique(@PathVariable("id")Integer id){
        Optional<Antique> optionalAntique=antiqueService.getAntiqueById(id);
        if(optionalAntique.isPresent()){
            return new ResponseEntity<>(optionalAntique.get(),HttpStatus.OK);
        }
        return null;
    }
}
