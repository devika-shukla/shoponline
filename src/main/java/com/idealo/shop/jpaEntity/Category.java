package com.idealo.shop.jpaEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.idealo.shop.util.messages.error.WarningCodes;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull(message = WarningCodes.ENTER_CATEGORY_TITLE)
	@NotBlank(message = WarningCodes.ENTER_CATEGORY_TITLE)
	private String title;

	private Category() {
		super();
	}

	public Category(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Category [id=%s, title=%s, items=%s]", id, title);
	}

}
