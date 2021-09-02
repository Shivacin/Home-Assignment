package com.shiva.ebay.assessment.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.shiva.ebay.assessment.data.domain.Item;
import com.shiva.ebay.assessment.data.domain.ItemImageUrl;
import com.shiva.ebay.assessment.data.domain.ItemSpecs;
import com.shiva.ebay.assessment.data.repo.ItemRepo;
import com.shiva.ebay.assessment.dto.ItemDto;
import com.shiva.ebay.assessment.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemService {

	private ItemRepo itemRepo;

	private ConditionService conditionSvc;

	@Transactional(readOnly = true)
	public ItemDto getItemByID(final long itemID) {
		return getItemDto(findById(itemID));
	}

	private Item findById(final long itemID) {
		return itemRepo.findById(itemID)
				.orElseThrow(() -> new NotFoundException("Item with id " + itemID + " not found."));
	}

	@Transactional(readOnly = true)
	public List<ItemDto> getAllItems(Pageable pageable) {
		return itemRepo.findAll(pageable)
				.map(this::getItemDto)
				.toList();
	}

	private ItemDto getItemDto(final Item item) {
		return ItemDto
				.builder()
				.id(item.getId())
				.title(item.getTitle())
				.condition(item.getCondition().getCondition())
				.condDesc(item.getCondition().getDescription())
				.description(item.getDescription())
				.quantity(item.getQuantity())
				.price(item.getPrice())
				.specs(item.getItemSpecs().stream().collect(Collectors.toMap(s -> s.getSpec(), s -> s.getSpecVal())))
				.imageUrls(item.getItemImageUrls().stream().map(u -> u.getUrl()).collect(Collectors.toList()))
				.build();
	}

	public boolean deleteItem(final long itemID) {
		try {
			itemRepo.deleteById(itemID);
			return true;
		} catch (EmptyResultDataAccessException|IllegalArgumentException e) {
			throw new NotFoundException("Item with id " + itemID + " not found.");
		}
	}

	public ItemDto save(ItemDto itemDto) {
		return getItemDto(itemRepo.save(getItem(itemDto, null)));
	}

	public ItemDto update(final ItemDto itemDto) {
		return getItemDto(itemRepo.save(getItem(itemDto, findById(itemDto.getId()))));
	}

	private Item getItem(final ItemDto dto, Item item) {
		if (item == null)
			item = Item.builder().build();
		return item.toBuilder()
				.title(dto.getTitle())
				.condition(conditionSvc.getByID(dto.getConditionId()))
				.description(dto.getDescription())
				.quantity(dto.getQuantity())
				.price(dto.getPrice())
				.itemSpecs(getItemSpecs(dto.getSpecs()))
				.itemImageUrls(getItemImageUrl(dto.getImageUrls()))
				.build();
	}

	private List<ItemSpecs> getItemSpecs(final Map<String, String> specs) {
		if (CollectionUtils.isEmpty(specs)) return Collections.emptyList();
		return specs.keySet()
			.stream()
			.map(s -> ItemSpecs.builder().spec(s).specVal(specs.get(s)).build())
			.collect(Collectors.toList());
	}

	private List<ItemImageUrl> getItemImageUrl(final List<String> urls) {
		if (CollectionUtils.isEmpty(urls)) return Collections.emptyList();
		return urls
				.stream()
				.map(u -> ItemImageUrl.builder().url(u).build()).collect(Collectors.toList());
	}

	public List<ItemDto> getItemsOddID(Pageable pageable) {
		 return itemRepo.findAll(
				(root, query, builder) -> {
					return builder.equal(builder.mod(root.get("id"), 2), 1);
				}, pageable).stream().map(this::getItemDto).collect(Collectors.toList());
	}
}
