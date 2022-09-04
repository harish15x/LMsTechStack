package com.bridgelabz.lmstechstack.controller;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.model.TechStackModel;
import com.bridgelabz.lmstechstack.service.ITechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/techstack")
public class TechStackController {

    @Autowired
    ITechStackService techStackService;

    @PostMapping("/addtechstack")
    public TechStackModel addTechStack(@Valid @RequestBody TechStackDTO techStackDTO){
        return techStackService.addTechStack(techStackDTO);
    }

    @PutMapping("/updatetechstack/{id}")
    public TechStackModel updateTechStack(@PathVariable Long id, @Valid @RequestBody TechStackDTO techStackDTO, @RequestHeader String token) {
        return techStackService.updateTechStack(id, techStackDTO, token);
    }

    @GetMapping("/gettechstacks")
    public List<TechStackModel> getTechStacks(@RequestHeader String token) {
        return techStackService.getTechStacks(token);
    }

    @DeleteMapping("deletetechstack/{id}")
    public TechStackModel deleteTechStack(@PathVariable Long id, @RequestHeader String token) {
        return techStackService.deleteTechStack(id, token);
    }


    @GetMapping("gettechstack/{id}")
    public TechStackModel getTechStack(@PathVariable Long id, @RequestHeader String token) {
        return techStackService.getTechStack(id, token);
    }




}
