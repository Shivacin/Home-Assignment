package com.shiva.ebay.assessment.data.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shiva.ebay.assessment.data.domain.Item;

@Repository
public interface ItemRepo extends PagingAndSortingRepository<Item, Long>, JpaSpecificationExecutor<Item> {

}
