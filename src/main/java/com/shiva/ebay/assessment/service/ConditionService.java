package com.shiva.ebay.assessment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiva.ebay.assessment.data.domain.Condition;
import com.shiva.ebay.assessment.data.repo.ConditionRepo;
import com.shiva.ebay.assessment.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ConditionService {

	private ConditionRepo conditionRepo;

	@Transactional(readOnly = true)
	public Condition getByID(long id) {
		return conditionRepo.findById(id).orElseThrow(() -> new NotFoundException("Condition with id " + id + " not found."));
	}
}
