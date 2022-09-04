package com.bridgelabz.lmstechstack.service;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.model.TechStackModel;

import java.util.List;

public interface ITechStackService {
    TechStackModel addTechStack(TechStackDTO techStackDTO);

    TechStackModel updateTechStack(Long id, TechStackDTO techStackDTO, String token);

    List<TechStackModel> getTechStacks(String token);

    TechStackModel deleteTechStack(Long id, String token);

    TechStackModel getTechStack(Long id, String token);
}
