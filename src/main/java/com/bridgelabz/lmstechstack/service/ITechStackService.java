package com.bridgelabz.lmstechstack.service;

import com.bridgelabz.lmstechstack.dto.TechStackDTO;
import com.bridgelabz.lmstechstack.model.TechStackModel;
import com.bridgelabz.lmstechstack.util.ResponseClass;

import java.util.List;

public interface ITechStackService {
   ResponseClass addTechStack(String token, TechStackDTO techStackDTO);

    ResponseClass updateTechStack(Long id, TechStackDTO techStackDTO, String token);

    List<TechStackModel> getTechStacks(String token);

    ResponseClass deleteTechStack(Long id, String token);

    ResponseClass getTechStack(Long id, String token);
}
