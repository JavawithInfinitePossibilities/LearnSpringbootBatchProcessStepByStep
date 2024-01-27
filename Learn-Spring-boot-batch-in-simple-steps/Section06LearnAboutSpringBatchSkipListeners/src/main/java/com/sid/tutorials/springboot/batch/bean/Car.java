package com.sid.tutorials.springboot.batch.bean;

import java.util.Objects;

public class Car {
	private final Integer id;
	private final String make;
	private final String model;
	private final String color;
	private final Integer year;
	private final Double price;

	public Car(Integer id, String make, String model, String color, Integer year, Double price) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public Integer getYear() {
		return year;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", color=" + color + ", year=" + year
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, id, make, model, price, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(color, other.color) && Objects.equals(id, other.id) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && Objects.equals(price, other.price)
				&& Objects.equals(year, other.year);
	}

}
