package com.tianshao.mycalendar.model;

public abstract class IModel {
	public long id;

	public abstract String category();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
