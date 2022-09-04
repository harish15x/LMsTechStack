package com.bridgelabz.lmstechstack.service;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.exception.TechStackNotFoundException;
import com.bridgelabz.lmstechstack.model.TechStackModel;
import com.bridgelabz.lmstechstack.repository.TechStackRepository;
import com.bridgelabz.lmstechstack.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public TechStackModel addTechStack(TechStackDTO techStackDTO) {
        TechStackModel techStackModel = new TechStackModel(techStackDTO);
        techStackModel.setCreationTimeStamp(LocalDateTime.now());
        techStackRepository.save(techStackModel);
        String body = "Candidate added sucessfully " + techStackModel.getId();
        String subject = "CandidateS registration completed";
        mailService.send(techStackModel.getEmail(),body, subject);
        return techStackModel;
    }

    @Override
    public TechStackModel updateTechStack(Long id, TechStackDTO techStackDTO, String token) {
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
                return isTechStackAvailable.get();
            } else {
                throw new TechStackNotFoundException(400, "Tech not found");
            }
        }
        throw new TechStackNotFoundException(400, "Token is wrong");

    }

    @Override
    public List<TechStackModel> getTechStacks(String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(adminId);
        if (isTechPresent.isPresent()) {
            List<TechStackModel> isTechStackAvailable = techStackRepository.findAll();
            if (isTechStackAvailable.size() > 0) {
                return isTechStackAvailable;
            } else {
                throw new TechStackNotFoundException(400, "Tech stack is not found");
            }
        }
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

    @Override
    public TechStackModel deleteTechStack(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(userId);
        if (isTechPresent.isPresent()) {
            Optional<TechStackModel> isTechStackAvailable= techStackRepository.findById(id);
            if (isTechStackAvailable.isPresent()) {
                techStackRepository.delete(isTechStackAvailable.get());
                return isTechStackAvailable.get();
            } else {
                throw new TechStackNotFoundException(400, "Tech stack not found");
            }
        }
        throw new TechStackNotFoundException(400, "Token is wrong");
    }

    @Override
    public TechStackModel getTechStack(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<TechStackModel> isTechPresent = techStackRepository.findById(userId);
        if (isTechPresent.isPresent()) {
            Optional<TechStackModel> isTechStackAvailable = techStackRepository.findById(id);
            if (isTechStackAvailable.isPresent()) {
                return isTechStackAvailable.get();
            } else {
                throw new TechStackNotFoundException(400, "Tech stack not found");
            }
        }
        throw new TechStackNotFoundException(400, "Token is wrong");

    }


}
