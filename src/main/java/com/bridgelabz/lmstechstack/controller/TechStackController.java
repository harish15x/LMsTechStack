package com.bridgelabz.lmstechstack.controller;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.model.TechStackModel;
import com.bridgelabz.lmstechstack.service.ITechStackService;
import com.bridgelabz.lmstechstack.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/techstack")
public class TechStackController {

    @Autowired
    ITechStackService techStackService;

    @PostMapping("/addtechstack")
    public ResponseEntity<ResponseClass> addTechStack(String token, @Valid @RequestBody TechStackDTO techStackDTO){
        ResponseClass responseClass = techStackService.addTechStack(token, techStackDTO);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
//        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("/updatetechstack/{id}")
    public ResponseEntity<ResponseClass> updateTechStack(@PathVariable Long id, @Valid @RequestBody TechStackDTO techStackDTO, @RequestHeader String token) {
        ResponseClass responseClass = techStackService.updateTechStack(id, techStackDTO, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @GetMapping("/gettechstacks")
    public ResponseEntity <List<?>> getTechStacks(@RequestHeader String token) {
        List<TechStackModel> responseclass = techStackService.getTechStacks(token);
        return new ResponseEntity<>(responseclass, HttpStatus.OK);

    }

    @DeleteMapping("deletetechstack/{id}")
    public ResponseEntity<ResponseClass> deleteTechStack(@PathVariable Long id, @RequestHeader String token) {
        ResponseClass responseClass = techStackService.deleteTechStack(id, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }


    @GetMapping("gettechstack/{id}")
    public ResponseEntity<ResponseClass> getTechStack(@PathVariable Long id, @RequestHeader String token) {
        ResponseClass responseClass = techStackService.getTechStack(id, token);
        return  new ResponseEntity<>(responseClass, HttpStatus.OK);
    }




}
