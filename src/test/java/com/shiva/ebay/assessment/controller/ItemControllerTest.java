package com.shiva.ebay.assessment.controller;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.shiva.ebay.assessment.dto.ItemDto;
import com.shiva.ebay.assessment.service.ItemService;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

	@Mock
	private ItemService itemService;

	@InjectMocks
	private ItemController controller;

	private ItemDto itemDto = null;

	@BeforeEach
	public void setUp() {
		itemDto = ItemDto
				.builder()
				.id(1)
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
	void testGetItems() {
		when(itemService.getItemByID(anyLong())).thenReturn(itemDto);
		assertThat(controller.getItems(1)).isNotNull();
	}

	@Test
	void testGetAllItems() {
		when(itemService.getAllItems(any(Pageable.class))).thenReturn(List.of(itemDto));
		assertThat(controller.getAllItems(0, 10)).isNotNull();
	}

	@Test
	void testDeleteItem() {
		when(itemService.deleteItem(anyLong())).thenReturn(TRUE);
		controller.deleteItem(1);
		verify(itemService, times(1)).deleteItem(1);
	}

	@Test
	void testCreateItem() {
		when(itemService.save(itemDto)).thenReturn(itemDto);
		assertThat(controller.createItem(itemDto)).isNotNull();
	}

	@Test
	void testUpdateItem() {
		when(itemService.update(itemDto)).thenReturn(itemDto);
		assertThat(controller.updateItem(itemDto)).isNotNull();
	}

	@Test
	void testGetItemsOddItemID() {
		when(itemService.getItemsOddID(any(Pageable.class))).thenReturn(List.of(itemDto));
		assertThat(controller.getItemsOddItemID(0, 10)).isNotNull();
	}
}
