package com.shiva.ebay.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shiva.ebay.assessment.data.domain.Condition;
import com.shiva.ebay.assessment.data.repo.ConditionRepo;

@ExtendWith(MockitoExtension.class)
class ConditionServiceTest {

	@Mock
	private ConditionRepo conditionRepo;

	@InjectMocks
	private ConditionService service;

	@Test
	void testGetByID() {
		when(conditionRepo.findById(anyLong())).thenReturn(Optional.ofNullable(Condition.builder().id(1).build()));
		assertThat(service.getByID(anyLong())).isNotNull();
	}

}
