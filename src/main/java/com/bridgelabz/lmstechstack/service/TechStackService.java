package com.bridgelabz.lmstechstack.service;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.exception.TechStackNotFoundException;
import com.bridgelabz.lmstechstack.model.TechStackModel;
import com.bridgelabz.lmstechstack.repository.TechStackRepository;
import com.bridgelabz.lmstechstack.util.ResponseClass;
import com.bridgelabz.lmstechstack.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TechStackService implements ITechStackService{

    @Autowired
    TechStackRepository techStackRepository;

    @Autowired
    MailService mailService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass addTechStack(String token, TechStackDTO techStackDTO) {
        boolean isTech = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if (isTech){
        TechStackModel techStackModel = new TechStackModel(techStackDTO);
        techStackModel.setCreationTimeStamp(LocalDateTime.now());
        techStackRepository.save(techStackModel);
        String body = "TechStack added sucessfully " + techStackModel.getId();
        String subject = "Tech registration completed";
        mailService.send(techStackModel.getEmail(),body, subject);
        return new ResponseClass("Sucessfull", 200, techStackModel);
    }else{
            throw new TechStackNotFoundException(400,"token is wrong");
    }
    }


    @Override
    public ResponseClass updateTechStack(Long id, TechStackDTO techStackDTO, String token) {
        boolean isTech = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if (isTech){
        Long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent= techStackRepository.findById(userId);
        if (isTechPresent.isPresent()) {
            Optional<TechStackModel> isTechStackAvailable = techStackRepository.findById(id);
            if (isTechStackAvailable.isPresent()) {
                isTechStackAvailable.get().setImagePath(techStackDTO.getImagePath());
                isTechStackAvailable.get().setStatus(techStackDTO.getStatus());
                isTechStackAvailable.get().setTechName(techStackDTO.getTechName());
                isTechStackAvailable.get().setEmail(techStackDTO.getEmail());
                isTechStackAvailable.get().setUpdatedTimeStamp(LocalDateTime.now());
                techStackRepository.save(isTechStackAvailable.get());
                String body = "Tech stack is added successfully with techStackId " + isTechStackAvailable.get().getId();
                String subject = "Tech stack registration successfully";
                mailService.send(isTechStackAvailable.get().getEmail(), subject, body);
                return new ResponseClass("Sucessfull", 200, isTechStackAvailable.get());
            } else {
                throw new TechStackNotFoundException(400, "Tech not found");
            }
        }}
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<TechStackModel> getTechStacks(String token) {
        boolean isTech = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if(isTech){
        Long adminId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(adminId);
        if (isTechPresent.isPresent()) {
            List<TechStackModel> isTechStackAvailable = techStackRepository.findAll();
            if (isTechStackAvailable.size() > 0) {
                return isTechStackAvailable;
            } else {
                throw new TechStackNotFoundException(400, "Tech stack is not found");
            }
        }}
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass deleteTechStack(Long id, String token) {
        boolean isTech = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if(isTech){
        Long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(userId);
        if (isTechPresent.isPresent()) {
            Optional<TechStackModel> isTechStackAvailable = techStackRepository.findById(id);
            if (isTechStackAvailable.isPresent()) {
                techStackRepository.delete(isTechStackAvailable.get());
                return new ResponseClass("sucessfull", 200, isTechStackAvailable.get());
            } else {
                throw new TechStackNotFoundException(400, "Tech stack not found");
            }
        }}
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseClass getTechStack(Long id, String token) {
        boolean isTech = restTemplate.getForObject("http://localhost:8083/admin/validate" + token, Boolean.class);
        if (isTech){
        Long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(userId);
        if (isTechPresent.isPresent()) {
            Optional<TechStackModel> isTechStackAvailable = techStackRepository.findById(id);
            if (isTechStackAvailable.isPresent()) {
                return new ResponseClass("Sucessfull", 200, isTechStackAvailable.get());
            } else {
                throw new TechStackNotFoundException(400, "Tech stack not found");
            }
        }}
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

}
