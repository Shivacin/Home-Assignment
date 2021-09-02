package com.shiva.ebay.assessment.data.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ITEM_SPECS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSpecs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String spec;

	private String specVal;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
	private Item item;
}
