package com.shiva.ebay.assessment.data.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "ITEM")
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "condition")
	private Condition condition;

	private double price;

	private int quantity;

	private String description;

	@OneToMany(cascade = ALL, orphanRemoval = true, fetch = EAGER, mappedBy = "item")
	private List<ItemSpecs> itemSpecs;

	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "item")
	private List<ItemImageUrl> itemImageUrls;
}
