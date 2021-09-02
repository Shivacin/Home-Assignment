package com.shiva.ebay.assessment.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.ebay.assessment.dto.ItemDto;
import com.shiva.ebay.assessment.service.ItemService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
@Tag(name = "item", description = "The Item API")
@Slf4j
public class ItemController {

	private ItemService itemService;

	@GetMapping("/{id}")
	public ItemDto getItems(@PathVariable("id") long itemID) {
		return itemService.getItemByID(itemID);
	}

	@GetMapping("/")
	public List<ItemDto> getAllItems(@RequestParam(defaultValue = "0") Integer page	, 
            							@RequestParam(defaultValue = "20") Integer size) {
		return itemService.getAllItems(PageRequest.of(page, size));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void deleteItem(@PathVariable("id") long itemID) {
		itemService.deleteItem(itemID);
	}

	@PostMapping("/create")
	@ResponseStatus(CREATED)
	public ItemDto createItem(@Valid @RequestBody ItemDto itemDto) {
		log.debug("ItemDto {}", itemDto);
		return itemService.save(itemDto);
	}

	@PutMapping("/update")
	public ItemDto updateItem(@Valid @RequestBody ItemDto itemDto) {
		log.debug("ItemDto {}", itemDto);
		return itemService.update(itemDto);
	}

	@GetMapping("/item_id/odd")
	public List<ItemDto> getItemsOddItemID(@RequestParam(defaultValue = "0") Integer page, 
										@RequestParam(defaultValue = "20") Integer size) {
		return itemService.getItemsOddID(PageRequest.of(page, size));
	}
}
