package com.shiva.ebay.assessment.data.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shiva.ebay.assessment.data.domain.Condition;

@Repository
public interface ConditionRepo extends CrudRepository<Condition, Long> {

}
