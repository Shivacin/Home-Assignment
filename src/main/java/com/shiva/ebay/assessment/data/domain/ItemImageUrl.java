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
@Table(name = "ITEM_IMAGE_URL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemImageUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
	private Item item;
}
