package com.bridgelabz.lmstechstack.repository;

import com.bridgelabz.lmstechstack.model.TechStackModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackRepository extends JpaRepository<TechStackModel, Long> {
}
