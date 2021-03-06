package com.scopic.antiqueauction.controller;

import com.scopic.antiqueauction.domain.converter.AntiqueConverter;
import com.scopic.antiqueauction.domain.entity.Antique;
import com.scopic.antiqueauction.domain.request.AntiqueRequest;
import com.scopic.antiqueauction.domain.request.BidRequest;
import com.scopic.antiqueauction.domain.response.AntiqueListingResponse;
import com.scopic.antiqueauction.domain.response.AntiqueResponse;
import com.scopic.antiqueauction.exceptions.FileStorageException;
import com.scopic.antiqueauction.exceptions.InvalidBidException;
import com.scopic.antiqueauction.service.AntiqueImageService;
import com.scopic.antiqueauction.service.AntiqueService;
import com.scopic.antiqueauction.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/antique")
public class AntiqueController {
    private AntiqueService antiqueService;
    @Autowired
    public AntiqueController(AntiqueService antiqueService) throws FileStorageException {
        this.antiqueService = antiqueService;
    }
    @GetMapping("/list")
    public Page<AntiqueListingResponse> getAllAntiques(@RequestParam("page") Integer page, @RequestParam("sort")Sort.Direction direction){
        return antiqueService.getAllAntiques(page, direction);
    }

    @GetMapping("/search")
    public Page<AntiqueListingResponse> searchAllAntiques(@RequestParam("page") Integer page,@RequestParam("sort")Sort.Direction direction, @RequestParam("str")String str){
        return antiqueService.getAllAntiquesLike(page,direction, str);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAntique(@PathVariable("id")Integer id){
        Optional<AntiqueResponse> optionalAntique=antiqueService.getAntiqueById(id);
        if(optionalAntique.isPresent()){
            return new ResponseEntity<>(optionalAntique.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Could not find an item for given id",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addAntique(@ModelAttribute @Valid AntiqueRequest request) throws IOException {
            Optional<Antique> optionalAntique = antiqueService.addAntique(request);
            if(optionalAntique.isPresent()){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Some Internal Server Error Occured",HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateAntique(@ModelAttribute @Valid AntiqueRequest request) throws IOException{
            Optional<Antique> optionalAntique = antiqueService.updateAntique(request);
            if(optionalAntique.isPresent()){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @PostMapping("/bid")
    public ResponseEntity<String> makeBid(@RequestBody @Valid BidRequest request){
            antiqueService.makeBid(request);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    };
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAntique(@PathVariable("id") Integer id){
        antiqueService.deleteAntiqueById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/login")
    public void login(){
    }
    @GetMapping("/admin/login")
    public void adminLogin(){
    }

}
