package com.shiva.ebay.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.shiva.ebay.assessment.data.domain.Condition;
import com.shiva.ebay.assessment.data.domain.Item;
import com.shiva.ebay.assessment.data.repo.ItemRepo;
import com.shiva.ebay.assessment.dto.ItemDto;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@Mock
	private ItemRepo itemRepo;

	@Mock
	private ConditionService conditionService;

	@InjectMocks
	private ItemService service;

	private Item item = null;

	private ItemDto itemDto = null;

	@BeforeEach
	public void setUp() {
		item = Item
				.builder()
				.id(1)
				.price(19.99)
				.quantity(10)
				.itemImageUrls(new ArrayList<>())
				.itemSpecs(new ArrayList<>())
				.condition(Condition.builder().id(1).build())
				.build();
		itemDto = ItemDto
				.builder()
				.price(10.99)
				.quantity(5)
				.conditionId(3)
				.imageUrls(List.of("http://www.google.com"))
				.specs(Map.of(
					    "UPC", "1234567890",
					    "COLOR", "BLACK"
					))
				.build();
	}

	@Test
	void testGetItemByID() {
		when(itemRepo.findById(anyLong())).thenReturn(Optional.ofNullable(item));
		assertThat(service.getItemByID(1)).isNotNull();
	}

	@Test
	void testGetAllItems() {
		List<Item> items = Collections.singletonList(item);
		when(itemRepo.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(items));
		assertThat(service.getAllItems(PageRequest.of(0, 10))).isNotNull();
	}

	@Test
	void testDeleteItem() {
		assertTrue(service.deleteItem(1l));
		verify(itemRepo, times(1)).deleteById(1l);
	}

	@Test
	void testSave() {
		when(itemRepo.save(any(Item.class))).thenReturn(item);
		when(conditionService.getByID(anyLong())).thenReturn(Condition.builder().id(1).build());
		assertThat(service.save(itemDto)).isNotNull();
	}

	@Test
	void testUpdate() {
		when(itemRepo.findById(anyLong())).thenReturn(Optional.ofNullable(item));
		when(itemRepo.save(any(Item.class))).thenReturn(item);
		assertThat(service.update(itemDto.toBuilder().id(1).build())).isNotNull();		
	}
}
