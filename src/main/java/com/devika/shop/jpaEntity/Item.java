package com.devika.shop.jpaEntity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.devika.shop.util.messages.error.WarningCodes;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long categoryId;
	@NotNull(message = WarningCodes.ENTER_ITEM_TITLE)
	@NotBlank(message = WarningCodes.ENTER_ITEM_TITLE)
	private String title;
	@NotNull(message = WarningCodes.ENTER_ITEM_TEXT)
	@NotBlank(message = WarningCodes.ENTER_ITEM_TEXT)
	private String text;
	@NotNull(message = WarningCodes.ENTER_ITEM_PRICE)
	@DecimalMin(value = "0", message = WarningCodes.ENTER_POSITIVE_ITEM_PRICE)
	private BigDecimal price;

	private Item() {
		super();
	}

	public Item(long categoryId, String title, String text, BigDecimal price) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.text = text;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return String.format("Item [id=%s, categoryId=%s, title=%s, text=%s, price=%s]", id, categoryId, title, text,
				price);
	}
}
