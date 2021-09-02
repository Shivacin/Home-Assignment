package com.shiva.ebay.assessment.dto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {

	private long id;

	@NotBlank(message = "Please enter the title.")
	private String title;

	@Min(value = 1, message = "Condition ID must be between 1 and 7.")
	@Max(value = 7, message = "Condition ID must be between 1 and 7.")
	@JsonProperty(access = Access.WRITE_ONLY)
	private int conditionId;

	@JsonProperty(access = Access.READ_ONLY)
	private String condition;

	@JsonProperty(access = Access.READ_ONLY)
	private String condDesc;

	@Digits(integer=9, fraction=2, message = "Price out of bounds (<9 digits>.<2 digits> expected)")
	private double price;

	@Min(value = 1, message = "Quantity cannot be less than 1.")
	@Max(value = 1000, message = "Quantity cannot be greater than 1000.")
	private int quantity;

	private String description;

	private Map<String, String> specs;

	private List<@URL(message = "Image URL is not valid.") String> imageUrls;
}
